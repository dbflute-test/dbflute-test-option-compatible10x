package org.docksidestage.compatible10x.dbflute.whitebox.dfprop;

import java.util.List;
import java.util.Map;

import org.dbflute.bhv.referrer.ConditionBeanSetupper;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.sqlclause.SqlClauseH2;
import org.dbflute.cbean.sqlclause.join.FixedConditionResolver;
import org.dbflute.dbmeta.info.ForeignInfo;
import org.dbflute.dbmeta.name.ColumnRealName;
import org.docksidestage.compatible10x.dbflute.allcommon.DBFluteConfig;
import org.docksidestage.compatible10x.dbflute.allcommon.ImplementedSqlClauseCreator;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberLoginCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberLogin;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxBizOneToOneH2DerivedTest extends UnitContainerTestCase {

    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                          After Care
    //                                                                          ==========
    @Override
    public void tearDown() throws Exception {
        DBFluteConfig.getInstance().unlock();
        DBFluteConfig.getInstance().setSqlClauseCreator(null);
        DBFluteConfig.getInstance().lock();
        super.tearDown();
    }

    // ===================================================================================
    //                                                                    Basic (OnClause)
    //                                                                    ================
    public void test_DerivedOneToOne_basic() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberLoginAsLatest();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        memberBhv.loadMemberLoginList(memberList, new ConditionBeanSetupper<MemberLoginCB>() {
            public void setup(MemberLoginCB cb) {
                cb.query().addOrderBy_LoginDatetime_Desc();
            }
        });
        StringBuilder sb = new StringBuilder();
        boolean existsNull = false;
        for (Member member : memberList) {
            MemberLogin actualLogin = member.getMemberLoginAsLatest();
            if (actualLogin == null) {
                existsNull = true;
            }
            List<MemberLogin> loginList = member.getMemberLoginList();
            MemberLogin expectedLogin = !loginList.isEmpty() ? loginList.get(0) : null;
            assertEquals(expectedLogin, actualLogin);
            sb.append(ln());
            sb.append(" ").append(member.getMemberName());
            sb.append(", ").append(actualLogin);
        }
        log(sb.toString());
        assertTrue(existsNull);
    }

    // ===================================================================================
    //                                                                          InlineView
    //                                                                          ==========
    public void test_DerivedOneToOne_inline() throws Exception {
        // ## Arrange ##
        moveFixedConditionToInlineView();
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberLoginAsLatest();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        memberBhv.loadMemberLoginList(memberList, new ConditionBeanSetupper<MemberLoginCB>() {
            public void setup(MemberLoginCB cb) {
                cb.query().addOrderBy_LoginDatetime_Desc();
            }
        });
        StringBuilder sb = new StringBuilder();
        boolean existsNull = false;
        for (Member member : memberList) {
            MemberLogin actualLogin = member.getMemberLoginAsLatest();
            if (actualLogin == null) {
                existsNull = true;
            }
            List<MemberLogin> loginList = member.getMemberLoginList();
            MemberLogin expectedLogin = !loginList.isEmpty() ? loginList.get(0) : null;
            assertEquals(expectedLogin, actualLogin);
            sb.append(ln());
            sb.append(" ").append(member.getMemberName());
            sb.append(", ").append(actualLogin);
        }
        log(sb.toString());
        assertTrue(existsNull);
    }

    protected void moveFixedConditionToInlineView() {
        DBFluteConfig.getInstance().unlock();
        DBFluteConfig.getInstance().setSqlClauseCreator(new ImplementedSqlClauseCreator() {
            @Override
            protected SqlClauseH2 createSqlClauseH2(String tableDbName) {
                return new SqlClauseH2(tableDbName) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void registerOuterJoin(String foreignAliasName, String foreignTableDbName,
                            String localAliasName, String localTableDbName,
                            Map<ColumnRealName, ColumnRealName> joinOnMap, String relationPath,
                            ForeignInfo foreignInfo, String fixedCondition,
                            FixedConditionResolver fixedConditionResolver) {
                        registerOuterJoinFixedInline(foreignAliasName, foreignTableDbName, localAliasName,
                                localTableDbName, joinOnMap, relationPath, foreignInfo, fixedCondition,
                                fixedConditionResolver);
                    }
                };
            }
        });
    }
}