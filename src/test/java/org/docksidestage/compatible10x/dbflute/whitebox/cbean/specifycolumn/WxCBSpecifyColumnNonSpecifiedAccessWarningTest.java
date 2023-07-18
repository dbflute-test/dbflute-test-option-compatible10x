package org.docksidestage.compatible10x.dbflute.whitebox.cbean.specifycolumn;

import org.dbflute.FunCustodial;
import org.dbflute.cbean.result.ListResultBean;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.2.7 (2023/07/17 Monday at ropppongi japanese)
 */
public class WxCBSpecifyColumnNonSpecifiedAccessWarningTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    private Boolean originalNonSpecifiedAccessWarningOnly;

    // ===================================================================================
    //                                                                            Settings
    //                                                                            ========
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // this project default settings are:
    // ; isNonSpecifiedColumnAccessAllowed = false
    // ; isNonSpecifiedColumnAccessWarningOnly = true
    // _/_/_/_/_/_/_/_/_/_/
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        if (originalNonSpecifiedAccessWarningOnly != null) { // needs to revert
            FunCustodial.unlock();
            FunCustodial.setNonSpecifiedColumnAccessWarningOnly(originalNonSpecifiedAccessWarningOnly);
            originalNonSpecifiedAccessWarningOnly = null;
        }
    }

    // ===================================================================================
    //                                                                      BasePoint Only
    //                                                                      ==============
    public void test_NonSpecifiedAccess_basePointOnly_exception() {
        // ## Arrange ##
        enableException();
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        cb.specify().columnMemberAccount();

        /* ## Act ## */
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            assertNotNull(member.getMemberId());
            assertNotNull(member.getMemberAccount());
            assertNotNull(member.getMemberStatusCode());

            // expect no exception
            member.isMemberStatusCodeFormalized();
            member.isMemberStatusCode_ServiceAvailable();

            assertNonSpecifiedAccess(() -> member.getMemberName());
            assertNonSpecifiedAccess(() -> member.getBirthdate());
            assertNonSpecifiedAccess(() -> member.getFormalizedDatetime());
            assertNonSpecifiedAccess(() -> member.getRegisterDatetime());
            assertNonSpecifiedAccess(() -> member.getRegisterUser());
            assertNonSpecifiedAccess(() -> member.getUpdateDatetime());
            assertNonSpecifiedAccess(() -> member.getUpdateUser());
            assertNonSpecifiedAccess(() -> member.getVersionNo());

            assertNull(member.xznocheckGetMemberName());
            assertNull(member.xznocheckGetBirthdate());
            assertNull(member.xznocheckGetFormalizedDatetime());
            assertNull(member.xznocheckGetRegisterDatetime());
            assertNull(member.xznocheckGetRegisterUser());
            assertNull(member.xznocheckGetUpdateDatetime());
            assertNull(member.xznocheckGetUpdateUser());
            assertNull(member.xznocheckGetVersionNo());
            assertEquals(3, member.myspecifiedProperties().size()); // PK and account and setupSelect

            log(member.toString()); // expected no exception
            log(member.asDBMeta().extractAllColumnMap(member)); // expected no exception
        }
    }

    public void test_NonSpecifiedAccess_basePointOnly_warningOnly() {
        // ## Arrange ##
        enableWarning();
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        cb.specify().columnMemberAccount();

        /* ## Act ## */
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            assertNotNull(member.getMemberId());
            assertNotNull(member.getMemberAccount());
            assertNotNull(member.getMemberStatusCode());

            // expect no exception, warning only
            member.isMemberStatusCodeFormalized();
            member.isMemberStatusCode_ServiceAvailable();
            member.getMemberName();
            member.getBirthdate();
            member.getFormalizedDatetime();
            member.getRegisterDatetime();
            member.getRegisterUser();
            member.getUpdateDatetime();
            member.getUpdateUser();
            member.getVersionNo();

            assertNull(member.xznocheckGetMemberName());
            assertNull(member.xznocheckGetBirthdate());
            assertNull(member.xznocheckGetFormalizedDatetime());
            assertNull(member.xznocheckGetRegisterDatetime());
            assertNull(member.xznocheckGetRegisterUser());
            assertNull(member.xznocheckGetUpdateDatetime());
            assertNull(member.xznocheckGetUpdateUser());
            assertNull(member.xznocheckGetVersionNo());
            assertEquals(3, member.myspecifiedProperties().size()); // PK and account and setupSelect

            log(member.toString()); // expect no exception
            log(member.asDBMeta().extractAllColumnMap(member)); // expect no exception
        }
    }

    public void test_NonSpecifiedAccess_basePointOnly_noSpecify() {
        // ## Arrange ##
        enableException();
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();

        /* ## Act ## */
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            assertNotNull(member.getMemberId());
            assertNotNull(member.getMemberAccount());
            assertNotNull(member.getMemberStatusCode());

            // expect no exception
            member.isMemberStatusCodeFormalized();
            member.isMemberStatusCode_ServiceAvailable();

            // expect no exception
            member.getMemberName();
            member.getBirthdate();
            member.getFormalizedDatetime();
            member.getRegisterDatetime();
            member.getRegisterUser();
            member.getUpdateDatetime();
            member.getUpdateUser();
            member.getVersionNo();

            assertNotNull(member.xznocheckGetMemberName());
            assertNotNull(member.xznocheckGetRegisterDatetime());
            assertNotNull(member.xznocheckGetRegisterUser());
            assertNotNull(member.xznocheckGetUpdateDatetime());
            assertNotNull(member.xznocheckGetUpdateUser());
            assertNotNull(member.xznocheckGetVersionNo());
            assertEquals(0, member.myspecifiedProperties().size());

            log(member.toString()); // expect no exception
            log(member.asDBMeta().extractAllColumnMap(member)); // expect no exception
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    protected void enableException() {
        if (originalNonSpecifiedAccessWarningOnly == null) {
            originalNonSpecifiedAccessWarningOnly = FunCustodial.isNonSpecifiedColumnAccessWarningOnly();
        }
        FunCustodial.unlock();
        FunCustodial.setNonSpecifiedColumnAccessWarningOnly(false);
    }

    protected void enableWarning() {
        // default is warning so do nothing
    }
}
