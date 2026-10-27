package org.docksidestage.compatible10x.dbflute.whitebox.compatible;

import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxCBCompatibleWarningTest extends UnitContainerTestCase {

    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                  Invoker Expression
    //                                                                  ==================
    public void test_invoker_basic() {
        // ## Arrange ##
        SeaController controller = new SeaController(memberBhv);

        // ## Act ##
        // ## Assert ##
        controller.index(); // see log
    }

    public void test_invoker_noInvoker() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        // ## Assert ##
        cb.query().setMemberName_Equal(null); // see log
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    public static class SeaController {

        private final MemberBhv memberBhv;

        public SeaController(MemberBhv memberBhv) {
            this.memberBhv = memberBhv;
        }

        public void index() {
            new HangarLogic(memberBhv).find();
        }
    }

    public static class HangarLogic {

        private final MemberBhv memberBhv;

        public HangarLogic(MemberBhv memberBhv) {
            this.memberBhv = memberBhv;
        }

        public void find() {
            createHangarRepository().select();
        }

        private HangarRepository createHangarRepository() {
            return new HangarRepositoryImpl(memberBhv);
        }
    }

    public static interface HangarRepository {

        void select();
    }

    public static class HangarRepositoryImpl implements HangarRepository {

        private final MemberBhv memberBhv;

        public HangarRepositoryImpl(MemberBhv memberBhv) {
            this.memberBhv = memberBhv;
        }

        public void select() {
            simulateCBMakingWarning();
            simulateNonSpecifiedAccess();
        }

        private void simulateCBMakingWarning() {
            MemberCB cb = new MemberCB();

            // isInvalidQueryAllowedWarning
            cb.query().setMemberName_Equal(null);
            cb.query().setMemberId_Equal(3);
            cb.query().setMemberId_Equal(4);

            // isOrScopeQueryPurposeCheckWarningOnly
            cb.orScopeQuery(orCB -> {
                orCB.setupSelect_MemberStatus();
                orCB.specify().columnBirthdate();
                orCB.query().addOrderBy_Birthdate_Desc();
            });

            // isThatsBadTimingWarningOnly
            cb.query().existsPurchaseList(purchaseCB -> {
                cb.query().setBirthdate_IsNotNull();
            });

            // これは元々OUTなケースなので関係ない
            //cb.query().existsPurchaseList(purchaseCB -> {
            //    purchaseCB.setupSelect_Product();
            //});
        }

        private void simulateNonSpecifiedAccess() {
            Member member = memberBhv.selectEntity(cb -> {
                cb.specify().columnMemberName();
                cb.query().setMemberId_Equal(1);
            }).orElseThrow();

            // isNonSpecifiedColumnAccessWarningOnly
            member.getBirthdate();
        }
    }
}
