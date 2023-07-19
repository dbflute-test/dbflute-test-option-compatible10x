package org.docksidestage.compatible10x.dbflute.whitebox.cbean.internal;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.exception.OrderByIllegalPurposeException;
import org.dbflute.exception.SetupSelectIllegalPurposeException;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxCBPurposeTypeTest extends UnitContainerTestCase {

    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                     Illegal Purpose
    //                                                                     ===============
    public void test_illegalPurpose_setupSelect() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        try {
            // ## Act ##
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    // ## Act ##
                    subCB.setupSelect_Member();
                }
            });
            // ## Assert ##
            fail();
        } catch (SetupSelectIllegalPurposeException e) {
            log(e.getMessage());
        }
    }

    public void test_illegalPurpose_orderBy() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        try {
            // ## Act ##
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    // ## Act ##
                    subCB.query().addOrderBy_MemberId_Asc();
                }
            });
            // ## Assert ##
            fail();
        } catch (OrderByIllegalPurposeException e) {
            log(e.getMessage());
        }
    }

    // ===================================================================================
    //                                                                        OrScopeQuery
    //                                                                        ============
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // isCompatibleOrScopeQueryPurposeNoCheck=true here
    // _/_/_/_/_/_/_/_/_/_/
    public void test_illegalPurpose_OrScopeQuery_setupSelect() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.orScopeQuery(orCB -> {
            // ## Act ##
            orCB.setupSelect_MemberStatus();
        });
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            log(member);
            assertNotNull(member.getMemberStatus());
        }
    }

    public void test_illegalPurpose_OrScopeQuery_specify() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.orScopeQuery(orCB -> {
            // ## Act ##
            orCB.specify().columnMemberName();
        });
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            log(member);
            assertNotNull(member.getMemberName());
            assertNull(member.getMemberAccount());
        }
    }

    public void test_illegalPurpose_OrScopeQuery_orderBy() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.orScopeQuery(orCB -> {
            // ## Act ##
            orCB.query().addOrderBy_MemberId_Desc();
        });
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            log(member);
        }
        assertOrder(memberList, orderBy -> {
            orderBy.desc(mb -> mb.getMemberId());
        });
    }
}
