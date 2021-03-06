package org.docksidestage.compatible10x.dbflute.whitebox.runtime;

import java.util.ArrayList;
import java.util.List;

import org.dbflute.bhv.core.BehaviorCommandMeta;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.exception.SQLFailureException;
import org.dbflute.hook.CallbackContext;
import org.dbflute.hook.SqlLogHandler;
import org.dbflute.hook.SqlLogInfo;
import org.dbflute.hook.SqlStringFilter;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.MemberChangedToWithdrawalForcedlyPmb;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.SimpleMemberPmb;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.customize.SimpleMember;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.9.6 (2012/07/06 Friday)
 */
public class WxSqlStringFilterBasicTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                          After Care
    //                                                                          ==========
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        clearBehaviorCommandHook();
    }

    protected void clearBehaviorCommandHook() {
        CallbackContext.clearSqlStringFilterOnThread();
        assertFalse(CallbackContext.isExistCallbackContextOnThread());
        assertFalse(CallbackContext.isExistBehaviorCommandHookOnThread());
        assertFalse(CallbackContext.isExistSqlFireHookOnThread());
        assertFalse(CallbackContext.isExistSqlLogHandlerOnThread());
        assertFalse(CallbackContext.isExistSqlResultHandlerOnThread());
        assertFalse(CallbackContext.isExistSqlStringFilterOnThread());
    }

    // ===================================================================================
    //                                                                       ConditionBean
    //                                                                       =============
    public void test_ConditionBean_basic() {
        // ## Arrange ##
        final List<String> markList = new ArrayList<String>();
        CallbackContext.setSqlStringFilterOnThread(new SqlStringFilter() {
            public String filterSelectCB(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterSelectCB");
                return "/*foo*/" + ln() + executedSql;
            }

            public String filterEntityUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterEntityUpdate");
                return null;
            }

            public String filterQueryUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterQueryUpdate");
                return null;
            }

            public String filterOutsideSql(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterOutsideSql");
                return null;
            }

            public String filterProcedure(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterProcedure");
                return null;
            }
        });
        final List<SqlLogInfo> sqlLogInfoList = newArrayList();
        CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
            public void handle(SqlLogInfo info) {
                sqlLogInfoList.add(info);
            }
        });

        try {
            {
                // ## Act ##
                MemberCB cb = new MemberCB();
                cb.query().setMemberName_PrefixSearch("S");
                ListResultBean<Member> memberList = memberBhv.selectList(cb);

                // ## Assert ##
                assertFalse(memberList.isEmpty());
                assertEquals(1, markList.size());
                assertEquals("filterSelectCB", markList.get(0));
                assertEquals(1, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(0).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                // ## Act ##
                MemberCB cb = new MemberCB();
                cb.query().setMemberId_Equal(3);
                memberBhv.selectEntityWithDeletedCheck(cb);

                // ## Assert ##
                assertEquals(2, markList.size());
                assertEquals("filterSelectCB", markList.get(1));
                assertEquals(2, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(1).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                // ## Act ##
                MemberCB cb = new MemberCB();
                cb.paging(4, 2);
                ListResultBean<Member> memberList = memberBhv.selectPage(cb);

                // ## Assert ##
                assertFalse(memberList.isEmpty());
                assertEquals(4, markList.size());
                assertEquals("filterSelectCB", markList.get(2));
                assertEquals(4, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(3).getDisplaySql().startsWith("/*foo*/"));
            }
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    public void test_EntityUpdate_basic() {
        // ## Arrange ##
        final List<String> markList = new ArrayList<String>();
        CallbackContext.setSqlStringFilterOnThread(new SqlStringFilter() {
            public String filterSelectCB(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterSelectCB");
                return null;
            }

            public String filterEntityUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterEntityUpdate");
                return "/*foo*/" + ln() + executedSql;
            }

            public String filterQueryUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterQueryUpdate");
                return null;
            }

            public String filterOutsideSql(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterOutsideSql");
                return null;
            }

            public String filterProcedure(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterProcedure");
                return null;
            }
        });
        final List<SqlLogInfo> sqlLogInfoList = newArrayList();
        CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
            public void handle(SqlLogInfo info) {
                sqlLogInfoList.add(info);
            }
        });

        try {
            {
                Member member = new Member();
                member.setMemberName("insert");
                member.setMemberAccount("filterEntity");
                member.setMemberStatusCode_Formalized();
                memberBhv.insert(member);
                Integer memberId = member.getMemberId();
                assertEquals("insert", memberBhv.selectByPKValueWithDeletedCheck(memberId).getMemberName());
                assertEquals(2, markList.size());
                assertEquals("filterEntityUpdate", markList.get(0));
                assertEquals(3, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(0).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                Member member = new Member();
                member.setMemberId(3);
                member.setMemberName("update");
                memberBhv.updateNonstrict(member);
                assertEquals("update", memberBhv.selectByPKValueWithDeletedCheck(3).getMemberName());
                assertEquals(4, markList.size());
                assertEquals("filterEntityUpdate", markList.get(2));
                assertEquals(5, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(3).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                Member member = new Member();
                member.setMemberId(3);
                try {
                    memberBhv.deleteNonstrict(member);
                    fail();
                } catch (SQLFailureException ignored) {
                }
                assertEquals(5, markList.size());
                assertEquals("filterEntityUpdate", markList.get(4));
                assertEquals(6, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(5).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                List<Member> memberList = newArrayList();
                {
                    Member member = new Member();
                    member.setMemberId(3);
                    member.setMemberName("update1");
                    memberList.add(member);
                }
                {
                    Member member = new Member();
                    member.setMemberId(5);
                    member.setMemberName("update2");
                    memberList.add(member);
                }
                {
                    Member member = new Member();
                    member.setMemberId(9);
                    member.setMemberName("update3");
                    memberList.add(member);
                }
                memberBhv.batchUpdateNonstrict(memberList, new SpecifyQuery<MemberCB>() {
                    public void specify(MemberCB cb) {
                        cb.specify().columnMemberName();
                    }
                });
                assertEquals("update1", memberBhv.selectByPKValueWithDeletedCheck(3).getMemberName());
                assertEquals("update2", memberBhv.selectByPKValueWithDeletedCheck(5).getMemberName());
                assertEquals("update3", memberBhv.selectByPKValueWithDeletedCheck(9).getMemberName());
                assertEquals(9, markList.size());
                assertEquals("filterEntityUpdate", markList.get(5));
                assertEquals(12, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(6).getDisplaySql().startsWith("/*foo*/"));
                assertTrue(sqlLogInfoList.get(7).getDisplaySql().startsWith("/*foo*/"));
                assertTrue(sqlLogInfoList.get(8).getDisplaySql().startsWith("/*foo*/"));
                assertFalse(sqlLogInfoList.get(9).getDisplaySql().startsWith("/*foo*/"));
            }
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    // ===================================================================================
    //                                                                        Query Update
    //                                                                        ============
    public void test_QueryUpdate_basic() {
        // ## Arrange ##
        final List<String> markList = new ArrayList<String>();
        CallbackContext.setSqlStringFilterOnThread(new SqlStringFilter() {
            public String filterSelectCB(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterSelectCB");
                return null;
            }

            public String filterEntityUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterEntityUpdate");
                return null;
            }

            public String filterQueryUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterQueryUpdate");
                return "/*foo*/" + ln() + executedSql;
            }

            public String filterOutsideSql(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterOutsideSql");
                return null;
            }

            public String filterProcedure(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterProcedure");
                return null;
            }
        });
        final List<SqlLogInfo> sqlLogInfoList = newArrayList();
        CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
            public void handle(SqlLogInfo info) {
                sqlLogInfoList.add(info);
            }
        });

        try {
            {
                Member member = new Member();
                member.setMemberName("queryUpdate");
                MemberCB cb = new MemberCB();
                cb.query().setMemberId_Equal(3);
                memberBhv.queryUpdate(member, cb);
                assertEquals("queryUpdate", memberBhv.selectByPKValueWithDeletedCheck(3).getMemberName());
                assertEquals(2, markList.size());
                assertEquals("filterQueryUpdate", markList.get(0));
                assertEquals(2, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(0).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                MemberCB cb = new MemberCB();
                cb.query().setMemberId_Equal(3);
                try {
                    memberBhv.queryDelete(cb);
                    fail();
                } catch (SQLFailureException ignored) {
                }
                assertEquals(3, markList.size());
                assertEquals("filterQueryUpdate", markList.get(2));
                assertEquals(3, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(2).getDisplaySql().startsWith("/*foo*/"));
            }
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    // ===================================================================================
    //                                                                          OutsideSql
    //                                                                          ==========
    public void test_OutsideSql_basic() {
        // ## Arrange ##
        final List<String> markList = new ArrayList<String>();
        CallbackContext.setSqlStringFilterOnThread(new SqlStringFilter() {
            public String filterSelectCB(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterSelectCB");
                return null;
            }

            public String filterEntityUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterEntityUpdate");
                return null;
            }

            public String filterQueryUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterQueryUpdate");
                return null;
            }

            public String filterOutsideSql(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterOutsideSql");
                return "/*foo*/" + ln() + executedSql;
            }

            public String filterProcedure(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterProcedure");
                return null;
            }
        });
        final List<SqlLogInfo> sqlLogInfoList = newArrayList();
        CallbackContext.setSqlLogHandlerOnThread(new SqlLogHandler() {
            public void handle(SqlLogInfo info) {
                sqlLogInfoList.add(info);
            }
        });

        try {
            {
                SimpleMemberPmb pmb = new SimpleMemberPmb();
                pmb.setMemberId(3);
                SimpleMember member = memberBhv.outsideSql().entityHandling().selectEntityWithDeletedCheck(pmb);
                assertNotNull(member.getMemberName());
                assertEquals(1, markList.size());
                assertEquals("filterOutsideSql", markList.get(0));
                assertEquals(1, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(0).getDisplaySql().startsWith("/*foo*/"));
            }
            {
                MemberChangedToWithdrawalForcedlyPmb pmb = new MemberChangedToWithdrawalForcedlyPmb();
                pmb.setMemberName_PrefixSearch("S");
                memberBhv.outsideSql().execute(pmb);
                assertEquals(2, markList.size());
                assertEquals("filterOutsideSql", markList.get(1));
                assertEquals(2, sqlLogInfoList.size());
                assertTrue(sqlLogInfoList.get(1).getDisplaySql().startsWith("/*foo*/"));
            }
        } finally {
            CallbackContext.clearSqlLogHandlerOnThread();
        }
    }

    // ===================================================================================
    //                                                                           No Filter
    //                                                                           =========
    public void test_NoFilter() {
        // ## Arrange ##
        final List<String> markList = new ArrayList<String>();
        CallbackContext.setSqlStringFilterOnThread(new SqlStringFilter() {
            public String filterSelectCB(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterSelectCB");
                return null;
            }

            public String filterEntityUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterEntityUpdate");
                return null;
            }

            public String filterQueryUpdate(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterQueryUpdate");
                return null;
            }

            public String filterOutsideSql(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterOutsideSql");
                return null;
            }

            public String filterProcedure(BehaviorCommandMeta meta, String executedSql) {
                markList.add("filterProcedure");
                return null;
            }
        });

        // ## Act & Assert ##
        {
            MemberCB cb = new MemberCB();
            cb.query().setMemberName_PrefixSearch("S");
            assertFalse(memberBhv.selectList(cb).isEmpty());
            assertEquals(1, markList.size());
            assertEquals("filterSelectCB", markList.get(0));
        }
        {
            Member member = new Member();
            member.setMemberId(3);
            member.setMemberName("filterEntity");
            memberBhv.updateNonstrict(member);
            assertEquals("filterEntity", memberBhv.selectByPKValueWithDeletedCheck(3).getMemberName());
            assertEquals(3, markList.size());
            assertEquals("filterEntityUpdate", markList.get(1));
        }
        {
            Member member = new Member();
            member.setMemberName("filterQuery");
            MemberCB cb = new MemberCB();
            cb.query().setMemberId_Equal(3);
            memberBhv.queryUpdate(member, cb);
            assertEquals("filterQuery", memberBhv.selectByPKValueWithDeletedCheck(3).getMemberName());
            assertEquals(5, markList.size());
            assertEquals("filterQueryUpdate", markList.get(3));
        }
        {
            SimpleMemberPmb pmb = new SimpleMemberPmb();
            pmb.setMemberId(3);
            SimpleMember member = memberBhv.outsideSql().entityHandling().selectEntityWithDeletedCheck(pmb);
            assertEquals("filterQuery", member.getMemberName());
            assertEquals(6, markList.size());
            assertEquals("filterOutsideSql", markList.get(5));
        }
    }
}
