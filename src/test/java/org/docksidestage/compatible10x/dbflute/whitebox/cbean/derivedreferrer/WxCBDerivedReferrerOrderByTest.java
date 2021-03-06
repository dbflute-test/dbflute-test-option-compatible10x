package org.docksidestage.compatible10x.dbflute.whitebox.cbean.derivedreferrer;

import java.util.Date;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberLoginCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxCBDerivedReferrerOrderByTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_sepcify_derivedReferrer_orderBy_basic() {
        // ## Arrange ##
        Date defaultLoginDate = DfTypeUtil.toDate("1000/01/01");
        MemberCB cb = new MemberCB();
        cb.specify().derivedMemberLoginList().max(new SubQuery<MemberLoginCB>() {
            public void query(MemberLoginCB subCB) {
                subCB.specify().columnLoginDatetime();
                subCB.query().setMobileLoginFlg_Equal_False();
            }
        }, Member.ALIAS_latestLoginDatetime, op -> op.coalesce(defaultLoginDate));
        cb.query().addSpecifiedDerivedOrderBy_Asc(Member.ALIAS_latestLoginDatetime);

        // ## Act ##
        {
            ListResultBean<Member> memberList = memberBhv.selectList(cb);

            // ## Assert ##
            assertFalse(memberList.isEmpty());
            Date first = memberList.get(0).getLatestLoginDatetime();
            Date last = memberList.get(memberList.size() - 1).getLatestLoginDatetime();
            assertTrue(first.before(last));
        }

        // ## Arrange ##
        cb.clearOrderBy();
        cb.query().addSpecifiedDerivedOrderBy_Desc(Member.ALIAS_latestLoginDatetime);

        // ## Act ##
        {
            ListResultBean<Member> memberList = memberBhv.selectList(cb);

            // ## Assert ##
            assertFalse(memberList.isEmpty());
            Date first = memberList.get(0).getLatestLoginDatetime();
            Date last = memberList.get(memberList.size() - 1).getLatestLoginDatetime();
            assertTrue(last.before(first));
        }
    }

    // ===================================================================================
    //                                                                            Relation
    //                                                                            ========
    public void test_sepcify_derivedReferrer_orderBy_foreign() {
        // ## Arrange ##
        Date defaultLoginDate = DfTypeUtil.toDate("1000/01/01");
        MemberCB cb = new MemberCB();
        cb.specify().specifyMemberStatus().derivedMemberLoginList().max(new SubQuery<MemberLoginCB>() {
            public void query(MemberLoginCB subCB) {
                subCB.specify().columnLoginDatetime();
                subCB.query().setMobileLoginFlg_Equal_False();
            }
        }, Member.ALIAS_latestLoginDatetime, op -> op.coalesce(defaultLoginDate));
        cb.query().addSpecifiedDerivedOrderBy_Asc(Member.ALIAS_latestLoginDatetime);

        // ## Act ##
        {
            ListResultBean<Member> memberList = memberBhv.selectList(cb);

            // ## Assert ##
            assertFalse(memberList.isEmpty());
            Date first = memberList.get(0).getLatestLoginDatetime();
            Date last = memberList.get(memberList.size() - 1).getLatestLoginDatetime();
            assertTrue(first.before(last));
        }

        // ## Arrange ##
        cb.clearOrderBy();
        cb.query().queryMemberStatus().addSpecifiedDerivedOrderBy_Desc(Member.ALIAS_latestLoginDatetime);

        // ## Act ##
        {
            ListResultBean<Member> memberList = memberBhv.selectList(cb);

            // ## Assert ##
            assertFalse(memberList.isEmpty());
            Date first = memberList.get(0).getLatestLoginDatetime();
            Date last = memberList.get(memberList.size() - 1).getLatestLoginDatetime();
            assertTrue(last.before(first));
        }
    }
}
