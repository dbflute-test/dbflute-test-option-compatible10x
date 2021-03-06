package org.docksidestage.compatible10x.dbflute.whitebox.bhv;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.dbflute.exception.NonQueryUpdateNotAllowedException;
import org.dbflute.helper.HandyDate;
import org.dbflute.hook.CallbackContext;
import org.dbflute.hook.SqlLogHandler;
import org.dbflute.hook.SqlLogInfo;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchasePaymentCB;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchasePaymentBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.7.2 (2010/06/20 Sunday)
 */
public class WxBhvVaryingQueryUpdateTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private PurchaseBhv purchaseBhv;
    private PurchasePaymentBhv purchasePaymentBhv;

    // ===================================================================================
    //                                                                         Calculation
    //                                                                         ===========
    public void test_varyingQueryUpdate_plus() throws Exception {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(99999);

        PurchaseCB cb = new PurchaseCB();
        cb.query().setPaymentCompleteFlg_Equal_True();
        cb.query().addOrderBy_PurchaseId_Asc();
        ListResultBean<Purchase> beforeList = purchaseBhv.selectList(cb);

        // ## Act ##
        purchaseBhv.varyingQueryUpdate(purchase, cb, op -> op.self(colCB -> {
            colCB.specify().columnPurchaseCount();
        }).plus(1));

        // ## Assert ##
        ListResultBean<Purchase> actualList = purchaseBhv.selectList(cb);
        assertNotSame(0, actualList.size());
        assertEquals(beforeList.size(), actualList.size());
        int index = 0;
        for (Purchase actual : actualList) {
            Purchase before = beforeList.get(index);
            Integer beforeCount = before.getPurchaseCount();
            Integer actualAcount = actual.getPurchaseCount();
            log(actual.getPurchaseId() + " : " + beforeCount + " -> " + actualAcount);
            assertEquals(Integer.valueOf(beforeCount + 1), actualAcount);
            assertEquals(Integer.valueOf(99999), actual.getPurchasePrice());
            assertEquals(before.getRegisterDatetime(), actual.getRegisterDatetime());
            assertNotSame(before.getUpdateDatetime(), actual.getUpdateDatetime());
            ++index;
        }
    }

    // ===================================================================================
    //                                                                             Convert
    //                                                                             =======
    public void test_varyingQueryUpdate_convert() throws Exception {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(99999);

        PurchaseCB cb = new PurchaseCB();
        cb.query().setPaymentCompleteFlg_Equal_True();
        cb.query().addOrderBy_PurchaseId_Asc();
        ListResultBean<Purchase> beforeList = purchaseBhv.selectList(cb);

        // ## Act ##
        purchaseBhv.varyingQueryUpdate(purchase, cb, upOp -> upOp.self(colCB -> {
            colCB.specify().columnPurchaseDatetime();
        }).convert(op -> op.addDay(3)));

        // ## Assert ##
        ListResultBean<Purchase> actualList = purchaseBhv.selectList(cb);
        assertNotSame(0, actualList.size());
        assertEquals(beforeList.size(), actualList.size());
        int index = 0;
        for (Purchase actual : actualList) {
            Purchase before = beforeList.get(index);
            Timestamp beforeDatetime = before.getPurchaseDatetime();
            Timestamp actualDatetime = actual.getPurchaseDatetime();
            log(actual.getPurchaseId() + " : " + beforeDatetime + " -> " + actualDatetime);
            Date expectedDate = new HandyDate(beforeDatetime).addDay(3).getDate();
            assertEquals(expectedDate.getTime(), actualDatetime.getTime());
            ++index;
        }
    }

    // ===================================================================================
    //                                                                      NonQueryUpdate
    //                                                                      ==============
    public void test_varyingQueryUpdate_NonQueryUpdate_default() throws Exception {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(99999);
        PurchaseCB cb = new PurchaseCB();

        // ## Act ##
        try {
            int updated = purchaseBhv.varyingQueryUpdate(purchase, cb, op -> {});

            // ## Assert ##
            fail("updated=" + updated);
        } catch (NonQueryUpdateNotAllowedException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_varyingQueryUpdate_NonQueryUpdate_allow() throws Exception {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(99999);
        PurchaseCB cb = new PurchaseCB();

        // ## Act ##
        int updated = purchaseBhv.varyingQueryUpdate(purchase, cb, op -> op.allowNonQueryUpdate());

        // ## Assert ##
        ListResultBean<Purchase> actualList = purchaseBhv.selectList(cb);
        assertNotSame(0, actualList.size());
        assertEquals(actualList.size(), updated);
        for (Purchase actual : actualList) {
            assertEquals(purchase.getPurchasePrice(), actual.getPurchasePrice());
        }
    }

    public void test_varyingQueryDelete_NonQueryDelete_allow() throws Exception {
        // ## Arrange ##
        purchasePaymentBhv.varyingQueryDelete(new PurchasePaymentCB(), op -> op.allowNonQueryDelete());
        int countAll = purchaseBhv.selectCount(new PurchaseCB());
        PurchaseCB cb = new PurchaseCB();

        // ## Act ##
        int deleted = purchaseBhv.varyingQueryDelete(cb, op -> op.allowNonQueryDelete());

        // ## Assert ##
        ListResultBean<Purchase> actualList = purchaseBhv.selectList(cb);
        assertEquals(0, actualList.size());
        assertEquals(countAll, deleted);
    }

    // ===================================================================================
    //                                                                        ForcedDirect
    //                                                                        ============
    public void test_varyingQueryUpdate_ForcedDirect_basic() throws Exception {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(99999);

        PurchaseCB cb = new PurchaseCB();
        cb.query().setPaymentCompleteFlg_Equal_True();
        cb.query().addOrderBy_PurchaseId_Asc();
        ListResultBean<Purchase> beforeList = purchaseBhv.selectList(cb);

        final List<String> displaySqlList = newArrayList();
        CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
            public void handle(SqlLogInfo info) {
                displaySqlList.add(info.getDisplaySql());
            }
        });
        try {
            // ## Act ##
            purchaseBhv.varyingQueryUpdate(purchase, cb, op -> op.allowQueryUpdateForcedDirect());

            // ## Assert ##
            String queryUpdateSql = displaySqlList.get(0);
            assertTrue(queryUpdateSql.contains("where PAYMENT_COMPLETE_FLG = 1"));
            assertFalse(queryUpdateSql.contains("in ("));
            ListResultBean<Purchase> actualList = purchaseBhv.selectList(cb);
            assertNotSame(0, actualList.size());
            assertEquals(beforeList.size(), actualList.size());
            int index = 0;
            for (Purchase actual : actualList) {
                Purchase before = beforeList.get(index);
                assertEquals(Integer.valueOf(99999), actual.getPurchasePrice());
                assertEquals(before.getRegisterDatetime(), actual.getRegisterDatetime());
                assertNotSame(before.getUpdateDatetime(), actual.getUpdateDatetime());
                ++index;
            }
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    public void test_varyingQueryUpdate_ForcedDirect_relation() throws Exception {
        // ## Arrange ##
        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(99999);

        PurchaseCB cb = new PurchaseCB();
        cb.query().queryMember().setMemberStatusCode_Equal_Formalized();
        cb.query().setPaymentCompleteFlg_Equal_True();
        cb.query().addOrderBy_PurchaseId_Asc();

        // ## Act ##
        try {
            purchaseBhv.varyingQueryUpdate(purchase, cb, op -> op.allowQueryUpdateForcedDirect());

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_varyingQueryDelete_ForcedDirect_basic() throws Exception {
        // ## Arrange ##
        purchasePaymentBhv.varyingQueryDelete(new PurchasePaymentCB(), op -> op.allowNonQueryDelete());

        PurchaseCB cb = new PurchaseCB();
        cb.query().setPaymentCompleteFlg_Equal_True();
        cb.query().addOrderBy_PurchaseId_Asc();

        final List<String> displaySqlList = newArrayList();
        CallbackContext.setSqlLogHandlerOnThread(info -> displaySqlList.add(info.getDisplaySql()));
        try {
            // ## Act ##
            purchaseBhv.varyingQueryDelete(cb, op -> op.allowQueryDeleteForcedDirect());

            // ## Assert ##
            String queryDeleteSql = displaySqlList.get(0);
            assertTrue(queryDeleteSql.contains("where PAYMENT_COMPLETE_FLG = 1"));
            assertFalse(queryDeleteSql.contains("in ("));
            ListResultBean<Purchase> actualList = purchaseBhv.selectList(cb);
            assertEquals(0, actualList.size());
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    public void test_varyingQueryDelete_ForcedDirect_relation() throws Exception {
        // ## Arrange ##
        PurchaseCB cb = new PurchaseCB();
        cb.query().queryMember().setMemberStatusCode_Equal_Formalized();
        cb.query().addOrderBy_PurchaseId_Asc();

        try {
            // ## Act ##
            purchaseBhv.varyingQueryDelete(cb, op -> op.allowQueryDeleteForcedDirect());

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            // OK
            log(e.getMessage());
        }
    }
}
