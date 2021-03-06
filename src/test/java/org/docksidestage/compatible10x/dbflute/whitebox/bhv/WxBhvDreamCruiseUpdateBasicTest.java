package org.docksidestage.compatible10x.dbflute.whitebox.bhv;

import java.util.ArrayList;
import java.util.List;

import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.dbflute.hook.CallbackContext;
import org.dbflute.hook.SqlLogHandler;
import org.dbflute.hook.SqlLogInfo;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.0.1 (2012/12/15 Saturday)
 */
public class WxBhvDreamCruiseUpdateBasicTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private PurchaseBhv purchaseBhv;

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    public void test_varyingUpdate_SelfCalculation_DreamCruise_plain() {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchaseId(3L);
        purchase.setPaymentCompleteFlg_True();

        try {
            final List<SqlLogInfo> infoList = new ArrayList<SqlLogInfo>();
            CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
                public void handle(SqlLogInfo info) {
                    infoList.add(info);
                }
            });

            // ## Act ##
            PurchaseCB cb = new PurchaseCB();
            PurchaseCB dreamCruiseCB = cb.dreamCruiseCB();
            purchaseBhv.varyingUpdateNonstrict(purchase, op -> op.self(colCB -> {
                colCB.specify().columnPurchasePrice();
            }).multiply(dreamCruiseCB.specify().columnPurchaseCount()));

            // ## Assert ##
            assertHasOnlyOneElement(infoList);
            SqlLogInfo info = infoList.get(0);
            String sql = info.getDisplaySql();
            assertTrue(sql.contains("set PURCHASE_PRICE = PURCHASE_PRICE * PURCHASE_COUNT"));
            assertTrue(sql.contains(", VERSION_NO = VERSION_NO + 1"));
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    // ===================================================================================
    //                                                                        Query Update
    //                                                                        ============
    public void test_varyingQueryUpdate_SelfCalculation_DreamCruise_plain() {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPaymentCompleteFlg_True();

        PurchaseCB cb = new PurchaseCB();
        cb.query().setPaymentCompleteFlg_Equal_True();

        try {
            final List<SqlLogInfo> infoList = new ArrayList<SqlLogInfo>();
            CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
                public void handle(SqlLogInfo info) {
                    infoList.add(info);
                }
            });
            // ## Act ##
            PurchaseCB dreamCruiseCB = cb.dreamCruiseCB();
            int updatedCount = purchaseBhv.varyingQueryUpdate(purchase, cb, op -> op.self(colCB -> {
                colCB.specify().columnPurchasePrice();
            }).multiply(dreamCruiseCB.specify().columnPurchaseCount()));

            // ## Assert ##
            assertNotSame(0, updatedCount);
            assertHasOnlyOneElement(infoList);
            SqlLogInfo info = infoList.get(0);
            String sql = info.getDisplaySql();
            assertTrue(sql.contains("set PURCHASE_PRICE = PURCHASE_PRICE * PURCHASE_COUNT"));
            assertTrue(sql.contains(", VERSION_NO = VERSION_NO + 1"));
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    public void test_varyingQueryUpdate_SelfCalculation_DreamCruise_joined() {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPaymentCompleteFlg_True();

        PurchaseCB cb = new PurchaseCB();
        cb.query().queryMember().setMemberStatusCode_Equal_Formalized();
        cb.query().setPaymentCompleteFlg_Equal_True();

        try {
            // ## Act ##
            PurchaseCB dreamCruiseCB = cb.dreamCruiseCB();
            purchaseBhv.varyingQueryUpdate(purchase, cb, op -> op.self(colCB -> {
                colCB.specify().columnPurchasePrice();
            }).multiply(dreamCruiseCB.specify().columnPurchaseCount()).divide(dreamCruiseCB.specify().specifyMember().columnMemberId()));

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }
}
