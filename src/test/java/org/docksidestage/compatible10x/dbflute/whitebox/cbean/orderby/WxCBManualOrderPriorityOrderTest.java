package org.docksidestage.compatible10x.dbflute.whitebox.cbean.orderby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.dbflute.cbean.ordering.ManualOrderOption;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.UnionQuery;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.compatible10x.dbflute.allcommon.CDef;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.7.6 (2010/11/06 Saturday)
 */
public class WxCBManualOrderPriorityOrderTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                   And/Or Connection
    //                                                                   =================
    public void test_PriorityOrder_AndOrConnection_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        ManualOrderOption mob = new ManualOrderOption();
        mob.when_Equal(5).or_Equal(13);
        mob.when_GreaterEqual(3).and_LessEqual(4);
        cb.query().addOrderBy_MemberId_Asc().withManualOrder(mob);
        cb.query().addOrderBy_MemberId_Asc();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        assertEquals(Integer.valueOf(5), memberList.get(0).getMemberId());
        assertEquals(Integer.valueOf(13), memberList.get(1).getMemberId());
        assertEquals(Integer.valueOf(3), memberList.get(2).getMemberId());
        assertEquals(Integer.valueOf(4), memberList.get(3).getMemberId());
        assertEquals(Integer.valueOf(1), memberList.get(4).getMemberId());
        assertEquals(Integer.valueOf(2), memberList.get(5).getMemberId());
        assertEquals(Integer.valueOf(6), memberList.get(6).getMemberId());
        assertEquals(Integer.valueOf(7), memberList.get(7).getMemberId());
        String displaySql = cb.toDisplaySql();
        assertTrue(displaySql.contains("dfloc.MEMBER_ID = 5 or dfloc.MEMBER_ID = 13 then 0"));
        assertTrue(displaySql.contains("dfloc.MEMBER_ID >= 3 and dfloc.MEMBER_ID <= 4 then 1"));
    }

    // ===================================================================================
    //                                                                           Date Type
    //                                                                           =========
    public void test_PriorityOrder_Date_GreaterEqual() {
        // ## Arrange ##
        Date fromDate = DfTypeUtil.toDate("1970/01/01");
        MemberCB cb = new MemberCB();
        ManualOrderOption mob = new ManualOrderOption();
        mob.when_GreaterEqual(fromDate);
        cb.query().addOrderBy_Birthdate_Asc().withManualOrder(mob);
        cb.query().addOrderBy_MemberId_Asc();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        List<Date> birthdateList = new ArrayList<Date>();
        Integer preMemberId = null;
        boolean secondOrder = false;
        boolean existsFirst = false;
        boolean existsSecond = false;
        boolean existsNullInSecond = false;
        boolean existsNotNullInSecond = false;
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            String birthDisp = DfTypeUtil.toString(birthdate, "yyyy/MM/dd");
            log(member.getMemberId() + ", " + member.getMemberName() + ", " + birthDisp);
            birthdateList.add(birthdate);
            if (birthdate != null && birthdate.after(fromDate)) {
                existsFirst = true;
                assertFalse(secondOrder);
                if (preMemberId != null) {
                    assertTrue(preMemberId < member.getMemberId());
                }
            } else {
                existsSecond = true;
                if (!secondOrder) {
                    secondOrder = true;
                    preMemberId = null;
                }
                if (preMemberId != null) {
                    assertTrue(preMemberId < member.getMemberId());
                }
                if (birthdate == null) {
                    existsNullInSecond = true;
                } else {
                    existsNotNullInSecond = true;
                }
            }
            preMemberId = member.getMemberId();
        }
        assertTrue(existsFirst);
        assertTrue(existsSecond);
        assertTrue(existsNullInSecond);
        assertTrue(existsNotNullInSecond);
    }

    public void test_PriorityOrder_Date_FromTo() {
        // ## Arrange ##
        Date fromDate = DfTypeUtil.toDate("1969/01/01");
        Date toDate = DfTypeUtil.toDate("1970/12/31");
        MemberCB cb = new MemberCB();
        ManualOrderOption mob = new ManualOrderOption();
        mob.when_FromTo(fromDate, toDate, op -> op.compareAsDate());
        cb.query().addOrderBy_Birthdate_Asc().withManualOrder(mob);
        cb.query().addOrderBy_MemberId_Asc();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        assertTrue(cb.toDisplaySql().contains("< '1971-01-01'"));
        List<Date> birthdateList = new ArrayList<Date>();
        Integer preMemberId = null;
        boolean secondOrder = false;
        boolean existsFirst = false;
        boolean existsSecond = false;
        boolean existsNullInSecond = false;
        boolean existsNotNullInSecond = false;
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            String birthDisp = DfTypeUtil.toString(birthdate, "yyyy/MM/dd");
            log(member.getMemberId() + ", " + member.getMemberName() + ", " + birthDisp);
            birthdateList.add(birthdate);
            if (birthdate != null && birthdate.after(fromDate) && birthdate.before(toDate)) {
                existsFirst = true;
                assertFalse(secondOrder);
                if (preMemberId != null) {
                    assertTrue(preMemberId < member.getMemberId());
                }
            } else {
                existsSecond = true;
                if (!secondOrder) {
                    secondOrder = true;
                    preMemberId = null;
                }
                if (preMemberId != null) {
                    assertTrue(preMemberId < member.getMemberId());
                }
                if (birthdate == null) {
                    existsNullInSecond = true;
                } else {
                    existsNotNullInSecond = true;
                }
            }
            preMemberId = member.getMemberId();
        }
        assertTrue(existsFirst);
        assertTrue(existsSecond);
        assertTrue(existsNullInSecond);
        assertTrue(existsNotNullInSecond);
    }

    // ===================================================================================
    //                                                                         Direct List
    //                                                                         ===========
    public void test_PriorityOrder_DirectList_CDef_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        List<CDef.MemberStatus> manualValueList = new ArrayList<CDef.MemberStatus>();
        manualValueList.add(CDef.MemberStatus.Withdrawal);
        manualValueList.add(CDef.MemberStatus.Formalized);
        manualValueList.add(CDef.MemberStatus.Provisional);
        cb.query().addOrderBy_MemberStatusCode_Asc().withManualOrder(manualValueList);
        cb.query().addOrderBy_Birthdate_Desc().withNullsLast();
        cb.query().addOrderBy_MemberName_Asc();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
        for (Member member : memberList) {
            String memberStatusCode = member.getMemberStatusCode();
            log(member.getMemberId() + ", " + member.getMemberName() + ", " + memberStatusCode);
            linkedHashSet.add(memberStatusCode);
        }
        List<String> list = new ArrayList<String>(linkedHashSet);
        assertEquals(CDef.MemberStatus.Withdrawal.code(), list.get(0));
        assertEquals(CDef.MemberStatus.Formalized.code(), list.get(1));
        assertEquals(CDef.MemberStatus.Provisional.code(), list.get(2));
    }

    // ===================================================================================
    //                                                                     Illegal Pattern
    //                                                                     ===============
    public void test_PriorityOrder_illegalArgument() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        try {
            cb.query().addOrderBy_Birthdate_Asc().withManualOrder((ManualOrderOption) null);

            // ## Assert ##
            fail();
        } catch (IllegalArgumentException e) {
            // OK
            log(e.getMessage());
        }

        // ## Act ##
        //try {
        cb.union(new UnionQuery<MemberCB>() {
            public void query(MemberCB unionCB) {
            }
        });
        cb.query().addOrderBy_Birthdate_Asc().withManualOrder(Arrays.asList("FOO"));

        // allowed since 0.9.9.4C so expect no exception
        // ## Assert ##
        //fail();
        //} catch (IllegalConditionBeanOperationException e) {
        //    // OK
        //    log(e.getMessage());
        //}
    }

    public void test_PriorityOrder_illegalConnection() {
        // ## Arrange ##
        ManualOrderOption mob = new ManualOrderOption();

        // ## Act ##
        try {
            mob.when_Equal(5).or_Equal(13).and_Equal(7);

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }

    // ===================================================================================
    //                                                                         Illegal Use
    //                                                                         ===========
    public void test_directUse() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        ManualOrderOption mob = new ManualOrderOption();
        mob.plus(3);
        try {
            cb.query().withManualOrder(mob);

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_duplicateUse() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        ManualOrderOption mob = new ManualOrderOption();
        mob.plus(3);
        cb.query().addOrderBy_Birthdate_Asc().withManualOrder(mob);
        try {
            cb.query().addOrderBy_MemberId_Asc().withManualOrder(mob);

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }
}
