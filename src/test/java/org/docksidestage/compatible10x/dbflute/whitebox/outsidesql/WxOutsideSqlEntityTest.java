package org.docksidestage.compatible10x.dbflute.whitebox.outsidesql;

import org.dbflute.exception.DangerousResultSizeException;
import org.dbflute.exception.EntityDuplicatedException;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.MemberNamePmb;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.SimpleMemberPmb;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.UnpaidSummaryMemberPmb;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.customize.SimpleMember;
import org.docksidestage.compatible10x.dbflute.exentity.customize.UnpaidSummaryMember;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxOutsideSqlEntityTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_outsideSql_selectEntity_typed() {
        // ## Arrange ##
        UnpaidSummaryMemberPmb pmb = new UnpaidSummaryMemberPmb();
        pmb.setMemberId(3);

        // ## Act ##
        UnpaidSummaryMember member = memberBhv.outsideSql().entityHandling().selectEntity(pmb);

        // ## Assert ##
        log("member=" + member);
        assertNotNull(member);
        assertEquals(3, member.getUnpaidManId().intValue());
    }

    public void test_outsideSql_selectEntity_freeStyle() {
        // ## Arrange ##
        String path = MemberBhv.PATH_selectUnpaidSummaryMember;
        UnpaidSummaryMemberPmb pmb = new UnpaidSummaryMemberPmb();
        pmb.setMemberId(3);
        Class<UnpaidSummaryMember> entityType = UnpaidSummaryMember.class;

        // ## Act ##
        UnpaidSummaryMember member = memberBhv.outsideSql().entityHandling().selectEntity(path, pmb, entityType);

        // ## Assert ##
        log("member=" + member);
        assertNotNull(member);
        assertEquals(3, member.getUnpaidManId().intValue());
    }

    // ===================================================================================
    //                                                                              Scalar
    //                                                                              ======
    public void test_outsideSql_selectEntity_scalar_typed() {
        // ## Arrange ##
        Member member = memberBhv.selectByPKValueWithDeletedCheck(3);
        MemberNamePmb pmb = new MemberNamePmb();
        pmb.setMemberId(3);

        // ## Act ##
        String name = memberBhv.outsideSql().entityHandling().selectEntity(pmb);

        // ## Assert ##
        assertNotNull(name);
        assertEquals(member.getMemberName(), name);
    }

    // ===================================================================================
    //                                                                             Illegal
    //                                                                             =======
    public void test_selectEntity_duplicateResult() {
        // ## Arrange ##
        String path = MemberBhv.PATH_selectSimpleMember;
        SimpleMemberPmb pmb = new SimpleMemberPmb();
        pmb.setMemberName_PrefixSearch("S");
        Class<SimpleMember> entityType = SimpleMember.class;

        // ## Act ##
        try {
            memberBhv.outsideSql().entityHandling().selectEntity(path, pmb, entityType);

            // ## Assert ##
            fail();
        } catch (EntityDuplicatedException e) {
            // OK
            log(e.getMessage());
            Throwable cause = e.getCause();
            assertEquals(cause.getClass(), DangerousResultSizeException.class);
        }
    }
}
