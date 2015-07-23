package org.docksidestage.compatible10x.dbflute.whitebox.bhv;

import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxBhvIllegalArgumentTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                       Entity Select
    //                                                                       =============
    public void test_selectEntity_null() {
        // ## Arrange ##
        // ## Act ##
        try {
            memberBhv.selectEntity((MemberCB)null);

            // ## Assert ##
            fail();
        } catch (IllegalArgumentException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_selectEntity_invalidCB() {
        // ## Arrange ##
        // ## Act ##
        try {
            memberBhv.selectEntity(new MemberCB().dreamCruiseCB());

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_selectEntityWithDeletedCheck_null() {
        // ## Arrange ##
        // ## Act ##
        try {
            memberBhv.selectEntityWithDeletedCheck((MemberCB)null);

            // ## Assert ##
            fail();
        } catch (IllegalArgumentException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_selectEntityWithDeletedCheck_invalidCB() {
        // ## Arrange ##
        // ## Act ##
        try {
            memberBhv.selectEntityWithDeletedCheck(new MemberCB().dreamCruiseCB());

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }
}
