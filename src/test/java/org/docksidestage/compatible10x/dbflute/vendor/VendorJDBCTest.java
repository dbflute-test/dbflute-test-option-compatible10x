package org.docksidestage.compatible10x.dbflute.vendor;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dbflute.bhv.core.BehaviorCommandMeta;
import org.dbflute.bhv.proposal.callback.SimpleTraceableSqlStringFilter;
import org.dbflute.bhv.proposal.callback.TraceableSqlAdditionalInfoProvider;
import org.dbflute.exception.EntityAlreadyUpdatedException;
import org.dbflute.hook.CallbackContext;
import org.dbflute.hook.SqlLogHandler;
import org.dbflute.hook.SqlLogInfo;
import org.dbflute.utflute.core.cannonball.CannonballCar;
import org.dbflute.utflute.core.cannonball.CannonballOption;
import org.dbflute.utflute.core.cannonball.CannonballRun;
import org.dbflute.util.DfCollectionUtil;
import org.docksidestage.compatible10x.dbflute.bsentity.dbmeta.MemberDbm;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.PurchaseMaxPriceMemberPmb;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.0.4D (2013/06/13 Thursday)
 */
public class VendorJDBCTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;
    private PurchaseBhv purchaseBhv;

    // ===================================================================================
    //                                                                       Repeat Select
    //                                                                       =============
    public void test_ThreadSafe_update_sameExecution() { // uses original transactions
        final int memberId = 3;
        final Member before = memberBhv.selectByPKValue(memberId);
        final Long versionNo = before.getVersionNo();
        final Set<String> markSet = DfCollectionUtil.newHashSet();

        cannonball(new CannonballRun() {
            public void drive(CannonballCar car) {
                Member member = new Member();
                member.setMemberId(memberId);
                member.setVersionNo(versionNo);
                memberBhv.update(member);
                final long threadId = Thread.currentThread().getId();
                for (int i = 0; i < 30; i++) {
                    Purchase purchase = new Purchase();
                    purchase.setMemberId(3);
                    long currentMillis = currentTimestamp().getTime();
                    long keyMillis = currentMillis - (threadId * 10000) - (i * 10000);
                    purchase.setPurchaseDatetime(new Timestamp(keyMillis));
                    purchase.setPurchaseCount(1234 + i);
                    purchase.setPurchasePrice(1234 + i);
                    purchase.setPaymentCompleteFlg_True();
                    purchase.setProductId(3);
                    purchaseBhv.insert(purchase);
                }
                markSet.add("success: " + threadId);
            }
        }, new CannonballOption().commitTx().expectExceptionAny(EntityAlreadyUpdatedException.class));
        log(markSet);
    }

    // ===================================================================================
    //                                                                       Query Timeout
    //                                                                       =============
    public void test_QueryTimeout_insert() throws Exception {
        cannonball(new CannonballRun() {
            public void drive(CannonballCar car) {
                final long threadId = Thread.currentThread().getId();
                if (threadId % 2 == 0) {
                    Member member = new Member();
                    member.setMemberName("lock");
                    member.setMemberAccount("same");
                    member.setMemberStatusCode_Formalized();
                    memberBhv.insert(member);
                    sleep(4000);
                } else {
                    Member member = new Member();
                    member.setMemberName("wait");
                    member.setMemberAccount("same"); // same value to wait for lock
                    member.setMemberStatusCode_Formalized();
                    sleep(1000);
                    memberBhv.varyingInsert(member, op -> op.configure(conf -> conf.queryTimeout(1)));
                }
            }
        }, new CannonballOption().threadCount(2).expectExceptionAny("timed out"));
    }

    // ===================================================================================
    //                                                                  First Line Comment 
    //                                                                  ==================
    public void test_FirstLineComment_all_front() throws Exception {
        doTest_FirstLineComment_all(true);
    }

    public void test_FirstLineComment_all_rear() throws Exception {
        doTest_FirstLineComment_all(false);
    }

    protected void doTest_FirstLineComment_all(boolean front) throws Exception {
        // ## Arrange ##
        final List<SqlLogInfo> infoList = new ArrayList<SqlLogInfo>();
        CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
            public void handle(SqlLogInfo info) {
                infoList.add(info);
            }
        });
        try {
            Method actionMethod = MemberDbm.getInstance().columnBirthdate().getWriteMethod();
            SimpleTraceableSqlStringFilter filter =
                    new SimpleTraceableSqlStringFilter(actionMethod, new TraceableSqlAdditionalInfoProvider() {
                        public String provide() {
                            return "marks:{?*@;+()[]'&%$#\"!\\>=<_^~-|.,}1234567890";
                        }
                    }) {

                        public String filterOutsideSql(BehaviorCommandMeta meta, String executedSql) {
                            return markingSql(executedSql);
                        }

                        public String filterProcedure(BehaviorCommandMeta meta, String executedSql) {
                            return markingSql(executedSql);
                        }
                    };
            if (front) {
                filter.markingAtFront();
            }
            CallbackContext.setSqlStringFilterOnThread(filter);

            try {
                // ## Act ##
                {
                    MemberCB cb = new MemberCB();
                    memberBhv.selectList(cb);
                }
                {
                    Member member = new Member();
                    member.setMemberId(3);
                    member.setBirthdate(currentUtilDate());
                    memberBhv.updateNonstrict(member);
                }
                {
                    MemberCB cb = new MemberCB();
                    cb.query().setMemberStatusCode_Equal_Provisional();
                    Member member = new Member();
                    memberBhv.queryUpdate(member, cb);
                }
                {
                    PurchaseMaxPriceMemberPmb pmb = new PurchaseMaxPriceMemberPmb();
                    pmb.paging(3, 2);
                    memberBhv.outsideSql().manualPaging().selectPage(pmb);
                }
                // no procedure generate here
                //{
                //    SpNoParameterPmb spPmb = new SpInOutParameterPmb();
                //    spPmb.setVInVarchar("foo");
                //    spPmb.setVInoutVarchar("bar");
                //    memberBhv.outsideSql().call(spPmb);
                //}
                // ## Assert ##
            } finally {
                CallbackContext.clearSqlStringFilterOnThread();
            }
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }
}
