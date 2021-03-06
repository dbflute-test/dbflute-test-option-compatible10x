package org.docksidestage.compatible10x.dbflute.whitebox.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dbflute.Entity;
import org.dbflute.bhv.referrer.ConditionBeanSetupper;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.compatible10x.dbflute.bsentity.customize.dbmeta.SimpleVendorCheckDbm;
import org.docksidestage.compatible10x.dbflute.bsentity.dbmeta.MemberDbm;
import org.docksidestage.compatible10x.dbflute.cbean.MemberAddressCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberLoginCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberLogin;
import org.docksidestage.compatible10x.dbflute.exentity.Product;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.dbflute.exentity.VendorCheck;
import org.docksidestage.compatible10x.dbflute.exentity.customize.SimpleVendorCheck;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.5 (2009/04/08 Wednesday)
 */
public class WxEntityBasicTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public void test_getter_emptyStringSaved() {
        // ## Arrange ##
        Member member = new Member();

        // ## Act ##
        member.setMemberName("");

        // ## Assert ##
        assertEquals("", member.getMemberName());
    }

    // ===================================================================================
    //                                                                 Modified Properties
    //                                                                 ===================
    public void test_modifiedProperties_basic() {
        // ## Arrange ##
        Member member = new Member();

        // ## Act & Assert ##
        assertFalse(member.mymodifiedProperties().contains("birthdate"));
        member.setBirthdate(currentUtilDate());
        assertTrue(member.mymodifiedProperties().contains("birthdate"));
        assertFalse(member.mymodifiedProperties().contains("memberAccount"));
        member.setMemberAccount(null);
        assertTrue(member.mymodifiedProperties().contains("memberAccount"));
        log(member.mymodifiedProperties());
        assertTrue(member.hasModification());

        member.clearModifiedInfo();
        assertFalse(member.mymodifiedProperties().contains("memberAccount"));
        assertFalse(member.hasModification());
    }

    // unsupported since 1.1, use varyingInsert()
    //// ===================================================================================
    ////                                                              Common Column Handling
    ////                                                              ======================
    //public void test_commonColumnHandling_basic() {
    //    // ## Arrange ##
    //    Member member = new Member();
    //
    //    // ## Act & Assert ##
    //    assertTrue(member.canCommonColumnAutoSetup());
    //    member.disableCommonColumnAutoSetup();
    //    assertFalse(member.canCommonColumnAutoSetup());
    //    member.enableCommonColumnAutoSetup();
    //    assertTrue(member.canCommonColumnAutoSetup());
    //}

    // ===================================================================================
    //                                                                            eqauls()
    //                                                                            ========
    public void test_equals_DomainEntity_hasPrimaryKey() {
        // ## Arrange ##
        Member member = new Member();
        Member other = new Member();

        // ## Act & Assert ##
        assertFalse(member.equals(null));
        assertFalse(member.equals(new Object()));
        assertTrue(member.equals(other));
        member.setBirthdate(currentUtilDate());
        assertTrue(member.equals(other));
        member.setMemberId(3);
        assertFalse(member.equals(other));
        other.setMemberId(4);
        assertFalse(member.equals(other));
        other.setMemberId(3);
        assertTrue(member.equals(other));
    }

    public void test_equals_CustomizeEntity_nonPrimaryKey() {
        // ## Arrange ##
        SimpleVendorCheck vendorCheck = new SimpleVendorCheck();
        SimpleVendorCheck other = new SimpleVendorCheck();

        // ## Act & Assert ##
        assertFalse(vendorCheck.equals(null));
        assertFalse(vendorCheck.equals(new Object()));
        assertTrue(vendorCheck.equals(other));
        vendorCheck.setTypeOfText("FOO");
        assertFalse(vendorCheck.equals(other));
        vendorCheck.setVendorCheckId(3L);
        assertFalse(vendorCheck.equals(other));
        other.setVendorCheckId(3L);
        assertFalse(vendorCheck.equals(other));
        other.setTypeOfText("FOO");
        assertTrue(vendorCheck.equals(other));
    }

    public void test_equals_MapKey() {
        // ## Arrange ##
        Map<Entity, String> map = new HashMap<Entity, String>();
        Member member = new Member();
        Member key = new Member();

        // ## Act & Assert ##
        member.setMemberId(1);
        key.setMemberId(1);
        map.put(member, "FOO");
        String actual = map.get(key);

        // ## Assert ##
        assertEquals("FOO", actual);
        MemberLogin other = new MemberLogin();
        other.setMemberLoginId(1L);
        assertNull(map.get(other));
    }

    // ===================================================================================
    //                                                                          hashCode()
    //                                                                          ==========
    public void test_hashCode_basic() {
        // ## Arrange ##
        Member member1 = new Member();
        Member member2 = new Member();

        // ## Act & Assert ##
        log(member1.hashCode());
        assertEquals(member1.hashCode(), member2.hashCode());
        assertEquals(member1.hashCode(), member1.hashCode());
        assertEquals(member2.hashCode(), member2.hashCode());
        member1.setMemberId(3);
        member2.setMemberId(3);
        log(member1.hashCode());
        assertEquals(member1.hashCode(), member2.hashCode());
        assertEquals(member1.hashCode(), member1.hashCode());
        assertEquals(member2.hashCode(), member2.hashCode());
        member1.setMemberId(333);
        member2.setMemberId(444);
        log(member1.hashCode());
        assertNotSame(member1.hashCode(), member2.hashCode());
        assertNotSame(member1.hashCode(), member1.hashCode());
        assertNotSame(member2.hashCode(), member2.hashCode());
        member1.setMemberId(Integer.MAX_VALUE);
        log(member1.hashCode()); // expect no exception!

        MemberLogin loing1 = new MemberLogin();
        member1.setMemberId(1);
        loing1.setMemberLoginId(1L);
        log(member1.hashCode() + " <> " + loing1.hashCode());
        assertNotSame(member1.hashCode(), loing1.hashCode());
    }

    public void test_instanceHash_unique() throws Exception {
        for (int i = 0; i < 10; i++) {
            if (doTest_instanceHash_unique()) {
                markHere("unique");
            }
        }
        assertMarked("unique");
    }

    protected boolean doTest_instanceHash_unique() throws Exception {
        // ## Arrange ##
        Set<Integer> hashSet = new HashSet<Integer>();
        int loopSize = 1000; // 1000000 can cause easily collision

        // ## Act ##
        for (int i = 0; i < loopSize; i++) {
            int hash;
            if (i % 3 == 0) {
                Member member = new Member();
                member.setMemberId(i);
                member.setMemberName("unique-" + i);
                hash = member.instanceHash();
            } else if (i % 3 == 1) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(Long.valueOf(i));
                purchase.setPaymentCompleteFlg_True();
                hash = purchase.instanceHash();
            } else if (i % 3 == 2) {
                Product product = new Product();
                product.setProductId(i);
                hash = product.instanceHash();
            } else {
                throw new IllegalStateException("no way");
            }

            // ## Assert ##
            if (hashSet.contains(hash)) {
                return false;
            }
            hashSet.add(hash);
        }
        int hashSetSize = hashSet.size();
        log("hashSet.size(): " + hashSetSize);
        assertEquals(loopSize, hashSetSize);
        return true;
    }

    // ===================================================================================
    //                                                                          toString()
    //                                                                          ==========
    public void test_toString_nonRelation() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberId_Equal(18);

        // ## Act ##
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);
        String detail = member.toString();

        // ## Assert ##
        log(detail);
        assertTrue(detail.contains(member.getClass().getSimpleName()));
        assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
    }

    public void test_toString_withManyToOne() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toString();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertTrue(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toString_withOneToOne() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberAddressAsValid(currentUtilDate());
        cb.setupSelect_MemberSecurityAsOne();
        cb.setupSelect_MemberWithdrawalAsOne();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toString();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertFalse(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            if (member.getMemberAddressAsValid() != null) {
                assertTrue(detail.contains(dbm.foreignMemberAddressAsValid().getForeignPropertyName()));
            }
            if (member.getMemberSecurityAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberSecurityAsOne().getForeignPropertyName()));
            }
            if (member.getMemberWithdrawalAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberWithdrawalAsOne().getForeignPropertyName()));
            }
            assertFalse(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toString_withOneToMany() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);
        memberBhv.loadPurchaseList(memberList, new ConditionBeanSetupper<PurchaseCB>() {
            public void setup(PurchaseCB cb) {
            }
        });
        memberBhv.loadMemberAddressList(memberList, new ConditionBeanSetupper<MemberAddressCB>() {
            public void setup(MemberAddressCB cb) {
            }
        });
        memberBhv.loadMemberLoginList(memberList, new ConditionBeanSetupper<MemberLoginCB>() {
            public void setup(MemberLoginCB cb) {
            }
        });

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toString();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertFalse(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberAddressAsValid().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberSecurityAsOne().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberWithdrawalAsOne().getForeignPropertyName()));
            if (!member.getMemberAddressList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberAddressList().getReferrerPropertyName()));
            }
            if (!member.getMemberLoginList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberLoginList().getReferrerPropertyName()));
            }
            if (!member.getPurchaseList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
            }
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toString_withAll() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        cb.setupSelect_MemberAddressAsValid(currentUtilDate());
        cb.setupSelect_MemberSecurityAsOne();
        cb.setupSelect_MemberWithdrawalAsOne().withWithdrawalReason();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);
        memberBhv.loadPurchaseList(memberList, new ConditionBeanSetupper<PurchaseCB>() {
            public void setup(PurchaseCB cb) {
                cb.setupSelect_Product();
                cb.setupSelect_SummaryProduct();
            }
        });
        memberBhv.loadMemberAddressList(memberList, new ConditionBeanSetupper<MemberAddressCB>() {
            public void setup(MemberAddressCB cb) {
            }
        });
        memberBhv.loadMemberLoginList(memberList, new ConditionBeanSetupper<MemberLoginCB>() {
            public void setup(MemberLoginCB cb) {
                cb.setupSelect_MemberStatus();
            }
        });

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toString();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertTrue(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            if (member.getMemberAddressAsValid() != null) {
                assertTrue(detail.contains(dbm.foreignMemberAddressAsValid().getForeignPropertyName()));
            }
            if (member.getMemberSecurityAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberSecurityAsOne().getForeignPropertyName()));
            }
            if (member.getMemberWithdrawalAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberWithdrawalAsOne().getForeignPropertyName()));
            }
            if (!member.getMemberAddressList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberAddressList().getReferrerPropertyName()));
            }
            if (!member.getMemberLoginList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberLoginList().getReferrerPropertyName()));
            }
            if (!member.getPurchaseList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
            }
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toString_BC_date_exists() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberId_Equal(18);

        // ## Act ##
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);
        member.setBirthdate(DfTypeUtil.toDate("BC0001/12/31 23:59:59.999"));
        String detail = member.toString();

        // ## Assert ##
        log(detail);
        assertTrue(detail.contains("BC0001"));
    }

    public void test_toString_BC_date_notExists() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberId_Equal(18);

        // ## Act ##
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);
        member.setBirthdate(DfTypeUtil.toDate("0001/01/01 00:00:00.000"));
        String detail = member.toString();

        // ## Assert ##
        log(detail);
        assertFalse(detail.contains("BC0001"));
    }

    // ===================================================================================
    //                                                              toStringWithRelation()
    //                                                              ======================
    public void test_toStringWithRelation_nonRelation() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberId_Equal(18);

        // ## Act ##
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);
        String detail = member.toStringWithRelation();

        // ## Assert ##
        log(detail);
        MemberDbm dbm = MemberDbm.getInstance();
        assertTrue(detail.contains(member.getClass().getSimpleName()));
        assertFalse(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
        assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
    }

    public void test_toStringWithRelation_withManyToOne() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toStringWithRelation();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertTrue(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            assertTrue(detail.contains(member.getMemberStatus().getMemberStatusName()));
            assertFalse(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toStringWithRelation_withOneToOne() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberAddressAsValid(currentUtilDate());
        cb.setupSelect_MemberSecurityAsOne();
        cb.setupSelect_MemberWithdrawalAsOne();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toStringWithRelation();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertFalse(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberStatus().getForeignDBMeta().getEntityType().getSimpleName()));
            if (member.getMemberAddressAsValid() != null) {
                assertTrue(detail.contains(dbm.foreignMemberAddressAsValid().getForeignPropertyName()));
                assertTrue(detail.contains(member.getMemberAddressAsValid().getAddress()));
            }
            if (member.getMemberSecurityAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberSecurityAsOne().getForeignPropertyName()));
                assertTrue(detail.contains(member.getMemberSecurityAsOne().getReminderAnswer()));
            }
            if (member.getMemberWithdrawalAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberWithdrawalAsOne().getForeignPropertyName()));
                assertTrue(detail.contains(member.getMemberWithdrawalAsOne().getWithdrawalDatetime().toString()));
            }
            assertFalse(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toStringWithRelation_withOneToMany() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);
        memberBhv.loadPurchaseList(memberList, new ConditionBeanSetupper<PurchaseCB>() {
            public void setup(PurchaseCB cb) {
            }
        });
        memberBhv.loadMemberAddressList(memberList, new ConditionBeanSetupper<MemberAddressCB>() {
            public void setup(MemberAddressCB cb) {
            }
        });
        memberBhv.loadMemberLoginList(memberList, new ConditionBeanSetupper<MemberLoginCB>() {
            public void setup(MemberLoginCB cb) {
            }
        });

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toStringWithRelation();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertFalse(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberAddressAsValid().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberSecurityAsOne().getForeignPropertyName()));
            assertFalse(detail.contains(dbm.foreignMemberWithdrawalAsOne().getForeignPropertyName()));
            if (!member.getMemberAddressList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberAddressList().getReferrerPropertyName()));
            }
            if (!member.getMemberLoginList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberLoginList().getReferrerPropertyName()));
            }
            if (!member.getPurchaseList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
                assertTrue(detail.contains(member.getPurchaseList().get(0).getPurchasePrice() + ""));
            }
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    public void test_toStringWithRelation_withAll() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        cb.setupSelect_MemberAddressAsValid(currentUtilDate());
        cb.setupSelect_MemberSecurityAsOne();
        cb.setupSelect_MemberWithdrawalAsOne().withWithdrawalReason();

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);
        memberBhv.loadPurchaseList(memberList, new ConditionBeanSetupper<PurchaseCB>() {
            public void setup(PurchaseCB cb) {
                cb.setupSelect_Product();
                cb.setupSelect_SummaryProduct();
            }
        });
        memberBhv.loadMemberAddressList(memberList, new ConditionBeanSetupper<MemberAddressCB>() {
            public void setup(MemberAddressCB cb) {
                cb.setupSelect_Member();
            }
        });
        memberBhv.loadMemberLoginList(memberList, new ConditionBeanSetupper<MemberLoginCB>() {
            public void setup(MemberLoginCB cb) {
                cb.setupSelect_Member();
                cb.setupSelect_MemberStatus();
            }
        });

        // ## Assert ##
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            String detail = member.toStringWithRelation();
            MemberDbm dbm = MemberDbm.getInstance();
            sb.append(ln()).append(detail);
            assertTrue(detail.contains(member.getClass().getSimpleName()));
            assertTrue(detail.contains(dbm.foreignMemberStatus().getForeignPropertyName()));
            assertTrue(detail.contains(member.getMemberStatus().getMemberStatusName()));
            if (member.getMemberAddressAsValid() != null) {
                assertTrue(detail.contains(dbm.foreignMemberAddressAsValid().getForeignPropertyName()));
            }
            if (member.getMemberSecurityAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberSecurityAsOne().getForeignPropertyName()));
            }
            if (member.getMemberWithdrawalAsOne() != null) {
                assertTrue(detail.contains(dbm.foreignMemberWithdrawalAsOne().getForeignPropertyName()));
            }
            if (!member.getMemberAddressList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberAddressList().getReferrerPropertyName()));
            }
            if (!member.getMemberLoginList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerMemberLoginList().getReferrerPropertyName()));
            }
            if (!member.getPurchaseList().isEmpty()) {
                assertTrue(detail.contains(dbm.referrerPurchaseList().getReferrerPropertyName()));
                assertTrue(detail.contains(member.getPurchaseList().get(0).getPurchasePrice() + ""));
            }
            assertTrue(detail.contains("@" + Integer.toHexString(member.hashCode())));
        }
        log(sb.toString());
    }

    // ===================================================================================
    //                                                                buildDisplayString()
    //                                                                ====================
    public void test_buildDisplayString_Basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        cb.query().setMemberId_Equal(18);
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);
        MemberDbm dbm = MemberDbm.getInstance();
        String memberStatusName = dbm.foreignMemberStatus().getForeignPropertyName();
        String memberAddressName = dbm.foreignMemberAddressAsValid().getForeignPropertyName();

        // ## Act ##
        String allNothing = member.buildDisplayString(null, false, false);

        // ## Assert ##
        log(allNothing);
        assertTrue(allNothing.startsWith("@"));

        String relationOnly = member.buildDisplayString(null, false, true);
        log(relationOnly);
        assertFalse(relationOnly.startsWith("@"));
        assertTrue(relationOnly.contains("@"));
        assertTrue(relationOnly.startsWith("("));
        assertTrue(relationOnly.contains(memberStatusName));
        assertFalse(relationOnly.contains(memberAddressName));

        String columnOnly = member.buildDisplayString(null, true, false);
        log(columnOnly);
        assertFalse(columnOnly.startsWith("@"));
        assertTrue(columnOnly.contains("@"));
        assertTrue(columnOnly.startsWith("{"));
        assertTrue(columnOnly.contains(member.getMemberName()));

        String nameOnly = member.buildDisplayString("DispTest", false, false);
        log(nameOnly);
        assertFalse(nameOnly.startsWith("@"));
        assertTrue(nameOnly.contains("@"));
        assertTrue(nameOnly.startsWith("DispTest@"));
        assertFalse(nameOnly.contains(member.getMemberName()));
        assertFalse(nameOnly.contains(memberStatusName));
        assertFalse(nameOnly.contains(memberAddressName));

        String nameRelation = member.buildDisplayString("DispTest", false, true);
        log(nameRelation);
        assertFalse(nameRelation.startsWith("@"));
        assertTrue(nameRelation.contains("@"));
        assertTrue(nameRelation.startsWith("DispTest:("));
        assertFalse(nameRelation.contains(member.getMemberName()));
        assertTrue(nameRelation.contains(memberStatusName));
        assertFalse(nameRelation.contains(memberAddressName));
    }

    // ===================================================================================
    //                                                                           Byte Type
    //                                                                           =========
    public void test_BLOB_toString_exists() {
        // ## Arrange ##
        VendorCheck vendorCheck = new VendorCheck();
        try {
            vendorCheck.setTypeOfBlob("ABC".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        // ## Act ##
        String actual = vendorCheck.toString();

        // ## Assert ##
        log("actual=" + actual);
        assertTrue(actual.contains("byte[3]"));
    }

    public void test_BLOB_toString_notExists() {
        // ## Arrange ##
        VendorCheck vendorCheck = new VendorCheck();

        // ## Act ##
        String actual = vendorCheck.toString();

        // ## Assert ##
        log("actual=" + actual);
        assertTrue(actual.contains("byte[null]"));
    }

    public void test_BLOB_hashcode_DomainEntity_hasPrimaryKey() {
        // ## Arrange ##
        VendorCheck vendorCheck = new VendorCheck();
        vendorCheck.setTypeOfText("FOO");
        int notExistsHashcode = vendorCheck.hashCode();
        try {
            vendorCheck.setTypeOfBlob("BAR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        // ## Act ##
        int actual = vendorCheck.hashCode(); // expect no exception

        // ## Assert ##
        log("notExistsHashcode=" + notExistsHashcode + " actual=" + actual);
        assertEquals(notExistsHashcode, actual);
    }

    public void test_BLOB_hashcode_CustomizeEntity_nonPrimaryKey() {
        // ## Arrange ##
        assertFalse(SimpleVendorCheckDbm.getInstance().hasPrimaryKey());
        SimpleVendorCheck vendorCheck = new SimpleVendorCheck();
        vendorCheck.setTypeOfText("FOO");
        int notExistsHashcode = vendorCheck.hashCode();
        try {
            vendorCheck.setTypeOfBlob("BAR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        // ## Act ##
        int actual = vendorCheck.hashCode(); // expect no exception

        // ## Assert ##
        log("notExistsHashcode=" + notExistsHashcode + " actual=" + actual);
        assertNotSame(notExistsHashcode, actual);
    }

    public void test_BLOB_equals_DomainEntity_hasPrimaryKey_differentByte() {
        // ## Arrange ##
        VendorCheck vendorCheck = new VendorCheck() {
            private static final long serialVersionUID = 1L;

            @Override
            public byte[] getTypeOfBlob() {
                fail("should not be called");
                return null;
            }
        };
        vendorCheck.setTypeOfText("FOO");
        try {
            vendorCheck.setTypeOfBlob("BAR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        VendorCheck other = new VendorCheck();
        other.setTypeOfText("FOO");
        try {
            other.setTypeOfBlob("BOR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        // ## Act ##
        boolean actual = vendorCheck.equals(other); // expect no exception

        // ## Assert ##
        log("actual=" + actual);
        assertTrue(actual); // not related to byte data
    }

    public void test_BLOB_equals_DomainEntity_hasPrimaryKey_sameByte() {
        // ## Arrange ##
        VendorCheck vendorCheck = new VendorCheck() {
            private static final long serialVersionUID = 1L;

            @Override
            public byte[] getTypeOfBlob() {
                fail("should not be called");
                return null;
            }
        };

        vendorCheck.setTypeOfText("FOO");
        try {
            vendorCheck.setTypeOfBlob("BAR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        VendorCheck other = new VendorCheck();
        other.setTypeOfText("FOO");
        try {
            other.setTypeOfBlob("BAR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        // ## Act ##
        boolean actual = vendorCheck.equals(other); // expect no exception

        // ## Assert ##
        log("actual=" + actual);
        assertTrue(actual); // not related to byte data
    }

    public void test_BLOB_equals_CustomizeEntity_nonPrimaryKey_differentByte() {
        // ## Arrange ##
        assertFalse(SimpleVendorCheckDbm.getInstance().hasPrimaryKey());
        SimpleVendorCheck vendorCheck = new SimpleVendorCheck();
        vendorCheck.setTypeOfText("FOO");
        try {
            vendorCheck.setTypeOfBlob("BAR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        SimpleVendorCheck other = new SimpleVendorCheck();
        other.setTypeOfText("FOO");
        try {
            other.setTypeOfBlob("BOR".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        // ## Act ##
        boolean actual = vendorCheck.equals(other); // expect no exception

        // ## Assert ##
        log("actual=" + actual);
        assertFalse(actual);
    }

    // ===================================================================================
    //                                                                        Serializable
    //                                                                        ============
    public void test_serializable_basic() {
        // ## Arrange ##
        Member member = new Member();
        member.setMemberName("Stojkovic");
        member.setBirthdate(currentUtilDate());
        member.setMemberStatusCode_Formalized();

        // ## Act ##
        byte[] binary = DfTypeUtil.toBinary(member);
        Serializable serializable = DfTypeUtil.toSerializable(binary);

        // ## Assert ##
        log(serializable);
        assertNotNull(serializable);
        assertEquals(member.toString(), serializable.toString());
    }

    public void test_serializable_selected() {
        // ## Arrange ##
        Member member = memberBhv.selectByPKValueWithDeletedCheck(3);
        member.setMemberName("Stojkovic");
        member.setBirthdate(currentUtilDate());
        member.setMemberStatusCode_Formalized();

        // ## Act ##
        byte[] binary = DfTypeUtil.toBinary(member);
        Serializable serializable = DfTypeUtil.toSerializable(binary);

        // ## Assert ##
        log(serializable);
        assertNotNull(serializable);
        assertEquals(member.toString(), serializable.toString());
    }
}
