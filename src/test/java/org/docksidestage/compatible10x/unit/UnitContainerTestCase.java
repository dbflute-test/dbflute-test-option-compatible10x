package org.docksidestage.compatible10x.unit;

import java.util.Map;

import org.dbflute.bhv.BehaviorSelector;
import org.dbflute.bhv.BehaviorWritable;
import org.dbflute.bhv.writable.DeleteOption;
import org.dbflute.cbean.ConditionBean;
import org.dbflute.exception.NonSpecifiedColumnAccessException;
import org.dbflute.utflute.core.exception.ExceptionExaminer;
import org.dbflute.utflute.spring.ContainerTestCase;
import org.docksidestage.compatible10x.JdbcBeansJavaConfig;
import org.docksidestage.compatible10x.dbflute.allcommon.V10xDBFluteBeansJavaConfig;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberAddressBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberFollowingBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberLoginBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberSecurityBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberServiceBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberWithdrawalBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchasePaymentBhv;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

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
    //                                                                            Settings
    //                                                                            ========
    @Override
    protected ApplicationContext provideDefaultApplicationContext() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JdbcBeansJavaConfig.class, V10xDBFluteBeansJavaConfig.class);

        // #thinking jflute how to read application.properties (2023/10/11)
        StandardEnvironment env = new StandardEnvironment();
        MutablePropertySources sources = env.getPropertySources();
        Map<String, Object> configMap = newHashMap("dbflute.behavior.log.mask", true);
        sources.addFirst(new MapPropertySource("MY_MAP", configMap));
        context.setEnvironment(env);

        return context;
    }

    @Override
    protected boolean isUseTestCaseLooseBinding() {
        return true;
    }

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

    // ===================================================================================
    //                                                                       Assert Helper
    //                                                                       =============
    protected void assertNonSpecifiedAccess(ExceptionExaminer noArgInLambda) {
        assertException(NonSpecifiedColumnAccessException.class, noArgInLambda);
    }
}
