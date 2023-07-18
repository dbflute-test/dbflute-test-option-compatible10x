package org.docksidestage.compatible10x.dbflute.whitebox.cbean.java8;

import java.util.Date;

import org.dbflute.cbean.coption.FromToOption;
import org.dbflute.exception.InvalidQueryRegisteredException;
import org.dbflute.util.Srl;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.2.7 (2023/07/17 Monday at roppongi japanese)
 */
public class WxJava8FromToTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                               Plain
    //                                                                               =====
    public void test_FromTo_plain_basic() throws Exception {
        // ## Arrange ##
        Member updated = updateFormalizedDatetime("2011/11/18 12:34:56.789");
        MemberCB cb = new MemberCB();
        cb.query().setMemberId_Equal(updated.getMemberId());
        Date fromDate = toUtilDate("2011/11/17 12:34:56.789");
        Date toDate = toUtilDate("2011/11/19 02:04:06.009");
        cb.query().setFormalizedDatetime_FromTo(fromDate, toDate, new FromToOption());
        cb.query().setBirthdate_LessThan(toUtilDate("3714/08/08 12:34:56"));
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        String sql = cb.toDisplaySql();
        log(ln() + sql);
        assertTrue(Srl.contains(sql, " >= '2011-11-17 12:34:56.789'"));
        assertTrue(Srl.contains(sql, " <= '2011-11-19 02:04:06.009'"));
        assertTrue(Srl.contains(sql, " < '3714-08-08'"));
        log(member.getFormalizedDatetime());
        assertEquals(updated.getFormalizedDatetime(), member.getFormalizedDatetime());
    }

    // ===================================================================================
    //                                                                           Either-Or
    //                                                                           =========
    public void test_DateFromTo_eitherOr_from() {
        // ## Arrange ##
        {
            MemberCB cb = new MemberCB();
            cb.checkNullOrEmptyQuery();

            // no exception, because isCompatibleConditionBeanFromToOneSideAllowed=true
            cb.query().setBirthdate_FromTo(toUtilDate("2011-01-21"), null, new FromToOption().compareAsDate());
        }

        // ## Act ##
        MemberCB cb = new MemberCB();
        cb.checkNullOrEmptyQuery();
        cb.query().setBirthdate_FromTo(toUtilDate("2011-01-21"), null, new FromToOption().compareAsDate().allowOneSide());

        // ## Assert ##
        assertTrue(cb.hasWhereClauseOnBaseQuery());
        String sql = cb.toDisplaySql();
        log(ln() + sql);
        assertTrue(Srl.contains(sql, " >= '2011-01-21'"));
    }

    public void test_DateFromTo_eitherOr_to() {
        // ## Arrange ##
        {
            MemberCB cb = new MemberCB();
            cb.checkNullOrEmptyQuery();

            // no exception, because isCompatibleConditionBeanFromToOneSideAllowed=true
            cb.query().setBirthdate_FromTo(null, toUtilDate("2011-01-21"), new FromToOption().compareAsDate());
        }

        // ## Act ##
        MemberCB cb = new MemberCB();
        cb.checkNullOrEmptyQuery();
        cb.query().setBirthdate_FromTo(null, toUtilDate("2011-01-21"), new FromToOption().compareAsDate().allowOneSide());

        // ## Assert ##
        assertTrue(cb.hasWhereClauseOnBaseQuery());
        String sql = cb.toDisplaySql();
        log(ln() + sql);
        assertTrue(Srl.contains(sql, " < '2011-01-22'")); // added
    }

    // ===================================================================================
    //                                                                            No Query
    //                                                                            ========
    public void test_DateFromTo_noQuery_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        // ## Assert ##
        // no exception, because isCompatibleConditionBeanFromToOneSideAllowed=true
        cb.query().setBirthdate_FromTo(null, null, new FromToOption().compareAsDate());
        cb.query().setBirthdate_FromTo(null, null, new FromToOption().allowOneSide());
    }

    public void test_DateFromTo_noQuery_checkNullOrEmptyQuery() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.checkNullOrEmptyQuery();

        // ## Act ##
        // ## Assert ##
        // no exception, because isCompatibleConditionBeanFromToOneSideAllowed=true
        try {
            cb.query().setBirthdate_FromTo(null, null, new FromToOption().compareAsDate());
            fail();
        } catch (InvalidQueryRegisteredException e) {
            log(e.getMessage());
        }
        try {
            cb.query().setBirthdate_FromTo(null, null, new FromToOption().allowOneSide());
            fail();
        } catch (InvalidQueryRegisteredException e) {
            log(e.getMessage());
        }
    }

    // ===================================================================================
    //                                                                       Assist Helper
    //                                                                       =============
    protected Member updateFormalizedDatetime(String exp) {
        MemberCB cb = new MemberCB();
        cb.query().setMemberStatusCode_Equal_Formalized();
        cb.fetchFirst(1);
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);

        member.setFormalizedDatetime(toTimestamp(exp));
        memberBhv.updateNonstrict(member);
        return member;
    }
}
