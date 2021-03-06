package org.docksidestage.compatible10x.dbflute.whitebox.outsidesql;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.result.PagingResultBean;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.cursor.PaymentCompletePurchaseCursor;
import org.docksidestage.compatible10x.dbflute.exbhv.cursor.PaymentCompletePurchaseCursorHandler;
import org.docksidestage.compatible10x.dbflute.exbhv.cursor.PurchaseSummaryMemberCursor;
import org.docksidestage.compatible10x.dbflute.exbhv.cursor.PurchaseSummaryMemberCursorHandler;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.PaymentCompletePurchasePmb;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.PurchaseSummaryMemberPmb;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.dbflute.exentity.customize.PaymentCompletePurchase;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxOutsideSqlCursorTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;
    private PurchaseBhv purchaseBhv;

    // ===================================================================================
    //                                                                  Insert with Cursor
    //                                                                  ==================
    public void test_selectCursor_insertWithCursor_sameTable() throws Exception {
        // ## Arrange ##
        PurchaseSummaryMemberPmb pmb = new PurchaseSummaryMemberPmb();
        pmb.setMemberStatusCode_Formalized();
        final List<Integer> memberIdList = new ArrayList<Integer>();
        final PurchaseSummaryMemberCursorHandler handler = new PurchaseSummaryMemberCursorHandler() {
            public Object fetchCursor(PurchaseSummaryMemberCursor cursor) throws SQLException {
                int count = 0;
                while (cursor.next()) {
                    final String memberName = cursor.getMemberName();
                    Member member = new Member();
                    member.setMemberName(memberName + count);
                    member.setMemberAccount(memberName + count);
                    member.setMemberStatusCode_Formalized();
                    memberBhv.insert(member);
                    memberIdList.add(member.getMemberId());
                    ++count;
                }
                return null;
            }
        };

        // ## Act ##
        memberBhv.outsideSql().cursorHandling().selectCursor(pmb, handler);

        // ## Assert ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberId_InScope(memberIdList);
        assertNotSame(0, memberBhv.selectCount(cb));
    }

    public void test_selectCursor_insertWithCursor_diffTable() throws Exception {
        // ## Arrange ##
        String path = MemberBhv.PATH_selectPurchaseSummaryMember;
        PurchaseSummaryMemberPmb pmb = new PurchaseSummaryMemberPmb();
        pmb.setMemberStatusCode_Formalized();
        final List<String> codeList = new ArrayList<String>();
        final PurchaseSummaryMemberCursorHandler handler = new PurchaseSummaryMemberCursorHandler() {
            public Object fetchCursor(PurchaseSummaryMemberCursor cursor) throws SQLException {
                int count = 0;
                while (cursor.next()) {
                    final String memberName = cursor.getMemberName();
                    MemberStatus memberStatus = new MemberStatus();
                    String memberStatusCode;
                    if (count >= 100) {
                        memberStatusCode = String.valueOf(count);
                    } else if (count >= 10) {
                        memberStatusCode = "0" + count;
                    } else {
                        memberStatusCode = "00" + count;
                    }
                    memberStatus.setMemberStatusCode(memberStatusCode);
                    memberStatus.setMemberStatusName(memberName + count);
                    memberStatus.setDescription("foo");
                    memberStatus.setDisplayOrder(99999 + count);
                    memberStatusBhv.insert(memberStatus);
                    codeList.add(memberStatus.getMemberStatusCode());
                    ++count;
                }
                return null;
            }
        };

        // ## Act ##
        memberBhv.outsideSql().cursorHandling().selectCursor(path, pmb, handler);

        // ## Assert ##
        MemberStatusCB cb = new MemberStatusCB();
        cb.query().setMemberStatusCode_InScope(codeList);
        assertNotSame(0, memberStatusBhv.selectCount(cb));
    }

    // ===================================================================================
    //                                                                       Nested Cursor
    //                                                                       =============
    public void test_selectCursor_nestedCursor_basic() throws Exception {
        // ## Arrange ##
        int countAll = memberBhv.selectCount(new MemberCB());
        {
            Member member = new Member();
            member.setMemberStatusCode_Withdrawal();
            MemberCB cb = new MemberCB();
            cb.query().setMemberStatusCode_Equal_Provisional();
            memberBhv.queryUpdate(member, cb);
        }
        int withdrawalCountAll;
        {
            MemberCB cb = new MemberCB();
            cb.query().setMemberStatusCode_Equal_Withdrawal();
            withdrawalCountAll = memberBhv.selectCount(cb);
        }
        final Map<Integer, Member> memberMap = new HashMap<Integer, Member>();
        {
            MemberCB cb = new MemberCB();
            ListResultBean<Member> beforeList = memberBhv.selectList(cb);
            for (Member member : beforeList) {
                memberMap.put(member.getMemberId(), member);
            }
        }
        PurchaseSummaryMemberPmb pmbFirst = new PurchaseSummaryMemberPmb();
        pmbFirst.setMemberStatusCode_Formalized();
        final List<Member> memberList = new ArrayList<Member>();

        // ## Act ##
        memberBhv.outsideSql().cursorHandling().selectCursor(pmbFirst, new PurchaseSummaryMemberCursorHandler() {
            public Object fetchCursor(final PurchaseSummaryMemberCursor firstCursor) throws SQLException {
                PurchaseSummaryMemberPmb pmbSecond = new PurchaseSummaryMemberPmb();
                pmbSecond.setMemberStatusCode_Withdrawal();
                memberBhv.outsideSql().cursorHandling()
                        .selectCursor(pmbSecond, new PurchaseSummaryMemberCursorHandler() {
                            protected Object fetchCursor(PurchaseSummaryMemberCursor secondCursor) throws SQLException {
                                while (firstCursor.next()) {
                                    // first process
                                    memberList.add(memberMap.get(firstCursor.getMemberId()));

                                    // second process
                                    if (secondCursor.next()) {
                                        memberList.add(memberMap.get(secondCursor.getMemberId()));
                                    }
                                }
                                return null;
                            }
                        });
                return null;
            }
        });

        // ## Assert ##
        assertHasAnyElement(memberList);
        boolean first = true;
        int currentWithdrawalCount = 0;
        boolean existsWithdrawal = false;
        boolean existsOverFormalized = false;
        for (Member member : memberList) {
            log(member.getMemberId() + ", " + member.getMemberStatusCode());
            if (first) {
                assertTrue(member.isMemberStatusCodeFormalized());
                first = false;
            } else {
                ++currentWithdrawalCount;
                if (currentWithdrawalCount <= withdrawalCountAll) {
                    assertTrue(member.isMemberStatusCodeWithdrawal());
                    existsWithdrawal = true;
                } else {
                    assertTrue(member.isMemberStatusCodeFormalized());
                    existsOverFormalized = true;
                }
                first = true;
            }
        }
        assertTrue(existsWithdrawal);
        assertTrue(existsOverFormalized);
        assertEquals(countAll, memberList.size());
    }

    // ===================================================================================
    //                                                                  Paging with Cursor
    //                                                                  ==================
    public void test_selectPage_with_Cursor() throws Exception {
        // ## Arrange ##
        PaymentCompletePurchasePmb pmb = new PaymentCompletePurchasePmb(false);
        pmb.paging(3, 1);

        // ## Act ##
        PagingResultBean<PaymentCompletePurchase> page = purchaseBhv.outsideSql().manualPaging().selectPage(pmb);

        // ## Assert ##
        assertHasAnyElement(page);
        for (PaymentCompletePurchase purchase : page) {
            log(purchase);
        }
    }

    public void test_selectCursor_with_Paging() throws Exception {
        // ## Arrange ##
        PaymentCompletePurchasePmb pmb = new PaymentCompletePurchasePmb(true);

        // ## Act ##
        final Set<String> markSet = new HashSet<String>();
        purchaseBhv.outsideSql().cursorHandling().selectCursor(pmb, new PaymentCompletePurchaseCursorHandler() {
            protected Object fetchCursor(PaymentCompletePurchaseCursor cursor) throws SQLException {
                // ## Assert ##
                while (cursor.next()) {
                    String memberName = cursor.getMemberName();
                    String productName = cursor.getProductName();
                    Timestamp purchaseDatetime = cursor.getPurchaseDatetime();
                    log(memberName, productName, purchaseDatetime);
                }
                markSet.add("called");
                return null;
            }
        });
        assertFalse(markSet.isEmpty());
    }
}
