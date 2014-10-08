package org.docksidestage.compatible10x.unit;

import org.dbflute.bhv.BehaviorSelector;
import org.dbflute.bhv.BehaviorWritable;
import org.dbflute.bhv.writable.DeleteOption;
import org.dbflute.cbean.ConditionBean;
import org.dbflute.utflute.spring.ContainerTestCase;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberAddressBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberFollowingBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberLoginBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberSecurityBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberServiceBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberWithdrawalBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchasePaymentBhv;

/**
 * The test case with container for unit test.
 * @author jflute
 * @since 0.6.3 (2008/02/02 Saturday)
 */
public abstract class UnitContainerTestCase extends ContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private BehaviorSelector _behaviorSelector;

    // ===================================================================================
    //                                                                         Data Helper
    //                                                                         ===========
    protected void deleteAll(Class<? extends BehaviorWritable> clazz) {
        BehaviorWritable bhv = _behaviorSelector.select(clazz);
        ConditionBean cb = bhv.newConditionBean();
        bhv.rangeRemove(cb, new DeleteOption<ConditionBean>().allowNonQueryDelete());
    }

    protected void deleteMemberReferrer() {
        deleteAll(MemberAddressBhv.class);
        deleteAll(MemberFollowingBhv.class);
        deleteAll(MemberLoginBhv.class);
        deleteAll(MemberServiceBhv.class);
        deleteAll(MemberSecurityBhv.class);
        deleteAll(MemberWithdrawalBhv.class);
        deleteAll(PurchasePaymentBhv.class);
        deleteAll(PurchaseBhv.class);
    }
}
