package org.docksidestage.compatible10x.dbflute.whitebox.cbean.derivedreferrer;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dbflute.bhv.referrer.ConditionBeanSetupper;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.cbean.scoping.UnionQuery;
import org.dbflute.exception.OrderByIllegalPurposeException;
import org.dbflute.exception.SetupSelectIllegalPurposeException;
import org.dbflute.exception.SpecifyDerivedReferrerEntityPropertyNotFoundException;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberLoginCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxCBDerivedReferrerBasicTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                        Attribute
    //                                                                        =========
    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_sepcify_derivedReferrer_max_query() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().derivedMemberLoginList().max(new SubQuery<MemberLoginCB>() {
            public void query(MemberLoginCB subCB) {
                subCB.specify().columnLoginDatetime();
                subCB.query().setMobileLoginFlg_Equal_False();
            }
        }, Member.ALIAS_latestLoginDatetime);

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        boolean existsLoginDatetime = false;
        boolean existsNullLoginDatetime = false;
        for (Member member : memberList) {
            String memberName = member.getMemberName();
            Date latestLoginDatetime = member.getLatestLoginDatetime();
            if (latestLoginDatetime != null) {
                existsLoginDatetime = true;
            } else {
                existsNullLoginDatetime = true;
            }
            log("memberName=" + memberName + ", latestLoginDatetime=" + latestLoginDatetime);
        }
        assertTrue(existsLoginDatetime);
        assertTrue(existsNullLoginDatetime);
    }

    public void test_sepcify_derivedReferrer_min_string() throws Exception {
        // ## Arrange ##
        MemberStatusCB cb = new MemberStatusCB();
        cb.specify().derivedMemberList().min(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnMemberAccount();
            }
        }, MemberStatus.ALIAS_anyMemberAccount); // as max

        // ## Act ##
        ListResultBean<MemberStatus> statusList = memberStatusBhv.selectList(cb);
        memberStatusBhv.loadMemberList(statusList, new ConditionBeanSetupper<MemberCB>() {
            public void setup(MemberCB cb) {
                cb.query().addOrderBy_MemberAccount_Asc();
            }
        });

        // ## Assert ##
        assertHasAnyElement(statusList);
        boolean exists = false;
        for (MemberStatus status : statusList) {
            String statusName = status.getMemberStatusName();
            String maxMemberAccount = status.getAnyMemberAccount();
            log(statusName + ", " + maxMemberAccount);
            List<Member> memberList = status.getMemberList();
            if (!memberList.isEmpty()) { // main road
                assertNotNull(statusName);
                Member firstMember = memberList.get(0);
                assertEquals(firstMember.getMemberAccount(), maxMemberAccount);
                exists = true;
            } else {
                assertNull(statusName);
            }
        }
        assertTrue(exists);
    }

    // ===================================================================================
    //                                                                  one-to-many-to-one
    //                                                                  ==================
    public void test_sepcify_derivedReferrer_OneToManyToOne_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.specify().specifySummaryProduct().columnLatestPurchaseDatetime();
            }
        }, Member.ALIAS_latestLoginDatetime);

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        boolean exists = false;
        for (Member member : memberList) {
            String memberName = member.getMemberName();
            Date latestLoginDatetime = member.getLatestLoginDatetime();
            log("memberName=" + memberName + ", latestLoginDatetime=" + latestLoginDatetime);
            if (latestLoginDatetime != null) {
                exists = true;
            }
        }
        assertTrue(exists);
    }

    public void test_sepcify_derivedReferrer_OneToManyToOne_sum() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().derivedPurchaseList().sum(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.specify().specifyProduct().columnRegularPrice();
            }
        }, Member.ALIAS_highestPurchasePrice);

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);
        memberBhv.loadPurchaseList(memberList, new ConditionBeanSetupper<PurchaseCB>() {
            public void setup(PurchaseCB cb) {
                cb.setupSelect_Product();
            }
        });

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        boolean existsPrice = false;
        boolean existsDuplicate = false;
        for (Member member : memberList) {
            String memberName = member.getMemberName();
            Integer sumPrice = member.getHighestPurchasePrice();
            log("memberName=" + memberName + ", sumPrice=" + sumPrice);
            if (sumPrice != null) {
                existsPrice = true;
            }
            List<Purchase> purchaseList = member.getPurchaseList();
            int expectedSum = 0;
            Set<Integer> productIdSet = new HashSet<Integer>();
            for (Purchase purchase : purchaseList) {
                Integer regularPrice = purchase.getProduct().getRegularPrice();
                expectedSum = expectedSum + regularPrice;
                Integer productId = purchase.getProductId();
                if (productIdSet.contains(productId)) {
                    existsDuplicate = true;
                }
                productIdSet.add(productId);
            }
            assertEquals(expectedSum, sumPrice != null ? sumPrice : 0);
        }
        assertTrue(existsPrice);
        assertTrue(existsDuplicate);
    }

    public void test_sepcify_derivedReferrer_OneToManyToOne_with_union() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.specify().specifySummaryProduct().columnLatestPurchaseDatetime();
                subCB.union(new UnionQuery<PurchaseCB>() {
                    public void query(PurchaseCB unionCB) {
                    }
                });
            }
        }, Member.ALIAS_latestLoginDatetime);
        cb.union(new UnionQuery<MemberCB>() {
            public void query(MemberCB unionCB) {
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        boolean exists = false;
        for (Member member : memberList) {
            String memberName = member.getMemberName();
            Date latestLoginDatetime = member.getLatestLoginDatetime();
            log("memberName=" + memberName + ", latestLoginDatetime=" + latestLoginDatetime);
            if (latestLoginDatetime != null) {
                exists = true;
            }
        }
        assertTrue(exists);
    }

    // ===================================================================================
    //                                                                 many-to-one-to-many
    //                                                                 ===================
    public void test_sepcify_derivedReferrer_ManyToOneToMany_self() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().specifyMemberStatus().derivedMemberList().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnMemberId();
            }
        }, Member.ALIAS_productKindCount);
        cb.query().queryMemberStatus().addOrderBy_DisplayOrder_Asc();
        cb.query().addOrderBy_MemberId_Desc();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        boolean exists = false;
        String preStatus = null;
        for (Member member : memberList) {
            String memberName = member.getMemberName();
            String statusCode = member.getMemberStatusCode();
            boolean border = false;
            if (preStatus == null) {
                preStatus = statusCode;
            } else {
                if (!preStatus.equalsIgnoreCase(statusCode)) {
                    border = true;
                }
                preStatus = statusCode;
            }
            Integer memberId = member.getMemberId();
            Integer groupMax = member.getProductKindCount();
            log(memberName + ", " + statusCode + ", " + memberId + ", " + groupMax);
            if (groupMax != null) {
                exists = true;
            }
            if (border) {
                assertEquals(memberId, groupMax);
            }
        }
        assertTrue(exists);
    }

    // ===================================================================================
    //                                                                 one-to-many-to-many
    //                                                                 ===================
    // implemented at DerivedReferrerNestedTest
    //public void test_sepcify_derivedReferrer_OneToManyToMany_max() {
    //...

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    // implemented at DerivedReferrerOptionTest
    //public void test_sepcify_derivedReferrer_option_coalesce() throws Exception {
    //...

    // ===================================================================================
    //                                                                            Order By
    //                                                                            ========
    // implemented at DerivedReferrerOrderByTest
    //public void test_sepcify_derivedReferrer_orderBy_basic() {
    //...

    // ===================================================================================
    //                                                                          with Union
    //                                                                          ==========
    // implemented at DerivedReferrerCollaborationTest
    //public void test_derivedReferrer_union_of_subQuery() {
    //...

    // ===================================================================================
    //                                                                  with SpecifyColumn
    //                                                                  ==================
    // implemented at DerivedReferrerCollaborationTest
    //public void test_sepcify_derivedReferrer_with_specifyColumn() {
    //...
    //...
    //...

    // ===================================================================================
    //                                                                             Illegal
    //                                                                             =======
    public void test_sepcify_derivedReferrer_illegal() {
        // ## Arrange ##
        MemberStatusCB cb = new MemberStatusCB();
        cb.specify().derivedMemberList().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                try {
                    // ## Act ##
                    subCB.setupSelect_MemberSecurityAsOne();

                    // ## Assert ##
                    fail();
                } catch (SetupSelectIllegalPurposeException e) {
                    // OK
                    log(e.getMessage());
                }
                try {
                    // ## Act ##
                    subCB.query().addOrderBy_MemberId_Asc();

                    // ## Assert ##
                    fail();
                } catch (OrderByIllegalPurposeException e) {
                    // OK
                    log(e.getMessage());
                }
                subCB.specify().columnBirthdate(); // OK
                subCB.query().derivedPurchaseList().max(new SubQuery<PurchaseCB>() { // OK
                            public void query(PurchaseCB subCB) {
                                subCB.specify().columnPurchasePrice();
                            }
                        }).equal(123);
            }
        }, "FOO");
    }

    public void test_sepcify_derivedReferrer_invalidAlias() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.specify().specifySummaryProduct().columnLatestPurchaseDatetime();
            }
        }, "NOT_EXIST_COLUMN");

        // ## Act ##
        try {
            memberBhv.selectList(cb);

            // ## Assert ##
            fail();
        } catch (SpecifyDerivedReferrerEntityPropertyNotFoundException e) {
            // OK
            log(e.getMessage());
        }
    }
}
