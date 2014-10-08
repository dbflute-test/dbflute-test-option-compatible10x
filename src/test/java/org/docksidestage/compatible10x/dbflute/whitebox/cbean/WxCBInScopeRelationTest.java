package org.docksidestage.compatible10x.dbflute.whitebox.cbean;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SubQuery;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxCBInScopeRelationTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                  NotInScopeRelation
    //                                                                  ==================
    public void test_notInScopeRelation_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().notInScopeMemberStatus(new SubQuery<MemberStatusCB>() {
            public void query(MemberStatusCB subCB) {
                subCB.query().setMemberStatusCode_Equal_Formalized();
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        for (Member member : memberList) {
            assertFalse(member.isMemberStatusCodeFormalized());
        }
    }
}
