package org.docksidestage.compatible10x.dbflute.whitebox.cbean;

import java.util.ArrayList;
import java.util.List;

import org.dbflute.bhv.core.context.ConditionBeanContext;
import org.dbflute.bhv.core.context.ContextStack;
import org.dbflute.bhv.core.context.ResourceContext;
import org.dbflute.bhv.readable.EntityRowHandler;
import org.dbflute.hook.CallbackContext;
import org.dbflute.hook.SqlResultHandler;
import org.dbflute.hook.SqlResultInfo;
import org.dbflute.outsidesql.OutsideSqlContext;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.6.5 (2010/02/05 Friday)
 */
public class WxCBSelectCursorTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;

    // ===================================================================================
    //                                                                    Insert in Cursor
    //                                                                    ================
    public void test_insert_in_selectCursor_of_conditionBean_sameTable() throws Exception {
        // ## Arrange ##
        final List<Integer> memberIdList = new ArrayList<Integer>();
        MemberCB cb = new MemberCB();

        // ## Act ##
        memberBhv.selectCursor(cb, new EntityRowHandler<Member>() {
            int count = 0;

            public void handle(Member entity) {
                if (count == 0) {
                    assertFalse(ContextStack.isExistContextStackOnThread());
                } else {
                    assertTrue(ContextStack.isExistContextStackOnThread());
                    assertTrue(ContextStack.getContextStackOnThread().isEmpty());
                }
                assertTrue(ConditionBeanContext.isExistConditionBeanOnThread());
                String memberName = entity.getMemberName();
                Member member = new Member();
                member.setMemberName(memberName + count);
                member.setMemberAccount(memberName + count);
                member.setMemberStatusCode_Formalized();
                memberBhv.insert(member);
                memberIdList.add(member.getMemberId());
                assertTrue(ConditionBeanContext.isExistConditionBeanOnThread());
                ++count;
            }
        });

        // ## Assert ##
        assertFalse(ContextStack.isExistContextStackOnThread());
        assertFalse(ConditionBeanContext.isExistConditionBeanOnThread());
        assertFalse(OutsideSqlContext.isExistOutsideSqlContextOnThread());
        cb.query().setMemberId_InScope(memberIdList);
        assertNotSame(0, memberBhv.selectCount(cb));
    }

    public void test_insert_in_selectCursor_of_conditionBean_diffTable() throws Exception {
        // ## Arrange ##
        final List<String> codeList = new ArrayList<String>();
        MemberCB cb = new MemberCB();

        // ## Act ##
        memberBhv.selectCursor(cb, new EntityRowHandler<Member>() {
            int count = 0;

            public void handle(Member entity) {
                if (count == 0) {
                    assertFalse(ContextStack.isExistContextStackOnThread());
                } else {
                    assertTrue(ContextStack.isExistContextStackOnThread());
                    assertTrue(ContextStack.getContextStackOnThread().isEmpty());
                }
                assertTrue(ConditionBeanContext.isExistConditionBeanOnThread());
                String memberName = entity.getMemberName();
                MemberStatus memberStatus = new MemberStatus();
                String memberStatusCode;
                if (count >= 100) {
                    memberStatusCode = String.valueOf(count);
                } else if (count >= 10) {
                    memberStatusCode = "0" + count;
                } else {
                    memberStatusCode = "00" + count;
                }
                memberStatus.setMemberStatusCode(memberStatusCode);
                memberStatus.setMemberStatusName(memberName + count);
                memberStatus.setDescription("foo");
                memberStatus.setDisplayOrder(99999 + count);
                memberStatusBhv.insert(memberStatus);
                codeList.add(memberStatus.getMemberStatusCode());
                assertTrue(ConditionBeanContext.isExistConditionBeanOnThread());
                ++count;
            }
        });

        // ## Assert ##
        assertFalse(ContextStack.isExistContextStackOnThread());
        assertFalse(ConditionBeanContext.isExistConditionBeanOnThread());
        assertFalse(OutsideSqlContext.isExistOutsideSqlContextOnThread());
        MemberStatusCB statusCB = new MemberStatusCB();
        statusCB.query().setMemberStatusCode_InScope(codeList);
        assertNotSame(0, memberStatusBhv.selectCount(statusCB));
    }

    // ===================================================================================
    //                                                                      Parent Context
    //                                                                      ==============
    public void test_selectCursor_parentContext_basic() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        CallbackContext.setSqlResultHandlerOnThread(new SqlResultHandler() {
            public void handle(SqlResultInfo info) {
                ResourceContext context = ResourceContext.getResourceContextOnThread();
                ResourceContext parentContext = context.getParentContext();

                // ## Assert ##
                if (context.getBehaviorCommand().isSelectCursor()) {
                    assertNull(parentContext);
                } else if (context.getBehaviorCommand().isSelectCount()) {
                    assertNotNull(parentContext);
                    log("parentContext=" + parentContext.getBehaviorCommand().getCommandName());
                    assertTrue(parentContext.getBehaviorCommand().isSelectCursor());
                } else {
                    fail();
                }
            }
        });
        try {
            memberBhv.selectCursor(cb, new EntityRowHandler<Member>() {
                public void handle(Member entity) {
                    memberBhv.selectCount(new MemberCB());
                }
            });
        } finally {
            assertFalse(ResourceContext.isExistResourceContextOnThread());
            CallbackContext.clearSqlResultHandlerOnThread();
        }
    }
}
