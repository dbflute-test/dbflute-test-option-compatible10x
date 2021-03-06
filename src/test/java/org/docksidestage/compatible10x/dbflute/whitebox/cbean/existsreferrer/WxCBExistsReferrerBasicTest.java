package org.docksidestage.compatible10x.dbflute.whitebox.cbean.existsreferrer;

import java.util.Date;
import java.util.List;

import org.dbflute.bhv.referrer.ConditionBeanSetupper;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.exception.OrderByIllegalPurposeException;
import org.dbflute.exception.SetupSelectIllegalPurposeException;
import org.dbflute.exception.SpecifyIllegalPurposeException;
import org.dbflute.util.DfTypeUtil;
import org.dbflute.util.Srl;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberLoginCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberLoginBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberAddress;
import org.docksidestage.compatible10x.dbflute.exentity.MemberLogin;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxCBExistsReferrerBasicTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;
    private MemberLoginBhv memberLoginBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_ExistsReferrer_nested() {
        // ## Arrange ##
        final Member member = memberBhv.selectByPKValueWithDeletedCheck(3);
        MemberStatusCB cb = new MemberStatusCB();
        cb.query().existsMemberList(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                    public void query(PurchaseCB subCB) {
                        subCB.query().queryMember().setMemberId_Equal(member.getMemberId());
                        subCB.columnQuery(new SpecifyQuery<PurchaseCB>() {
                            public void specify(PurchaseCB cb) {
                                cb.specify().columnPurchaseCount();
                            }
                        }).greaterEqual(new SpecifyQuery<PurchaseCB>() {
                            public void specify(PurchaseCB cb) {
                                cb.specify().specifyProduct().derivedPurchaseList().avg(new SubQuery<PurchaseCB>() {
                                    public void query(PurchaseCB subCB) {
                                        subCB.specify().columnPurchaseCount();
                                    }
                                }, null);
                            }
                        });
                    }
                });
                subCB.query().setMemberStatusCode_Equal_AsMemberStatus(member.getMemberStatusCodeAsMemberStatus());
            }
        });

        // ## Act ##
        MemberStatus status = memberStatusBhv.selectEntityWithDeletedCheck(cb);

        // ## Arrange ##
        String memberStatusCode = status.getMemberStatusCode();
        assertEquals(member.getMemberStatusCode(), memberStatusCode);
        String sql = cb.toDisplaySql();
        String lastFront = Srl.substringLastFront(sql, "exists");
        assertTrue(Srl.containsIgnoreCase(lastFront, "where sub1loc.MEMBER_STATUS_CODE = dfloc.MEMBER_STATUS_CODE"));
        String lastRear = Srl.substringLastRear(sql, "exists");
        assertTrue(Srl.containsIgnoreCase(lastRear, "inner join MEMBER sub2rel_0")); // auto-detected
        assertTrue(Srl.containsIgnoreCase(lastRear, "where sub2loc.MEMBER_ID = sub1loc.MEMBER_ID"));
        assertTrue(Srl.containsIgnoreCase(lastRear, "and sub2rel_0.MEMBER_ID = 3"));
        assertTrue(Srl.containsIgnoreCase(lastRear, " >= (select avg(sub3loc.PURCHASE_COUNT)"));
        assertTrue(Srl.containsIgnoreCase(lastRear, "where sub3loc.PRODUCT_ID = sub2rel_1.PRODUCT_ID"));
        assertTrue(Srl.containsIgnoreCase(lastRear, "and sub1loc.MEMBER_STATUS_CODE = '" + memberStatusCode + "'"));
    }

    // ===================================================================================
    //                                                                     Query(Relation)
    //                                                                     ===============
    public void test_ExistsReferrer_QueryRelation_resolvedAliasBatting_basic() {
        // ## Arrange ##
        MemberLoginCB cb = new MemberLoginCB();
        cb.setupSelect_MemberStatus();
        cb.setupSelect_Member().withMemberStatus();
        cb.query().queryMember().existsPurchaseList(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.query().queryMember().setMemberId_Equal(3);
            }
        });

        // ## Act ##
        ListResultBean<MemberLogin> loginList = memberLoginBhv.selectList(cb);

        // ## Assert ##
        assertNotSame(0, loginList.size());
        for (MemberLogin login : loginList) {
            final Member member = login.getMember();
            Long loginId = login.getMemberLoginId();
            String statusName = login.getMemberStatus().getMemberStatusName();
            Integer memberId = member.getMemberId();
            String memberName = member.getMemberName();
            log(loginId + ", " + statusName + ", " + memberId + ", " + memberName);
            assertEquals(Integer.valueOf(3), memberId);
        }
        String sql = cb.toDisplaySql();
        assertTrue(Srl.containsIgnoreCase(sql, "inner join MEMBER_STATUS dfrel_1"));
        assertTrue(Srl.containsIgnoreCase(sql, "inner join MEMBER_STATUS dfrel_1_0"));
        assertTrue(Srl.containsIgnoreCase(sql, "where sub1loc.MEMBER_ID = dfrel_1.MEMBER_ID"));
        assertTrue(Srl.containsIgnoreCase(sql, "sub1rel_0.MEMBER_ID = 3"));
    }

    public void test_ExistsReferrer_QueryRelation_resolvedAliasBatting_nested() {
        // ## Arrange ##
        ListResultBean<MemberStatus> expectedList;
        {
            MemberStatusCB cb = new MemberStatusCB();
            cb.query().existsMemberLoginList(new SubQuery<MemberLoginCB>() {
                public void query(MemberLoginCB subCB) {
                    subCB.query().setMemberId_Equal(3);
                }
            });
            cb.query().addOrderBy_DisplayOrder_Asc();
            expectedList = memberStatusBhv.selectList(cb);
            int countAll = memberStatusBhv.selectCount(new MemberStatusCB());
            assertTrue(countAll > expectedList.size());
            assertNotSame(0, expectedList.size());
        }

        MemberStatusCB cb = new MemberStatusCB();
        cb.query().existsMemberLoginList(new SubQuery<MemberLoginCB>() {
            public void query(MemberLoginCB subCB) {
                subCB.query().queryMember().existsPurchaseList(new SubQuery<PurchaseCB>() {
                    public void query(PurchaseCB subCB) {
                        subCB.query().queryMember().setMemberId_Equal(3);
                    }
                });
            }
        });
        cb.query().addOrderBy_DisplayOrder_Asc();

        // ## Act ##
        ListResultBean<MemberStatus> statusList = memberStatusBhv.selectList(cb);

        // ## Assert ##
        assertNotSame(0, statusList.size());
        for (MemberStatus status : statusList) {
            log(status);
        }
        assertEquals(expectedList, statusList);
        String sql = cb.toDisplaySql();
        String lastFront = Srl.substringLastFront(sql, "exists");
        assertTrue(Srl.containsIgnoreCase(lastFront, "inner join MEMBER sub1rel_1")); // auto-detected
        String lastRear = Srl.substringLastRear(sql, "exists");
        assertTrue(Srl.containsIgnoreCase(lastRear, "inner join MEMBER sub2rel_0")); // auto-detected
        assertTrue(Srl.containsIgnoreCase(lastRear, "where sub2loc.MEMBER_ID = sub1rel_1.MEMBER_ID"));
        assertTrue(Srl.containsIgnoreCase(lastRear, "and sub2rel_0.MEMBER_ID = 3"));
    }

    public void test_ExistsReferrer_QueryRelation_resolvedAliasBatting_QueryDelete() {
        // ## Arrange ##
        MemberLoginCB cb = new MemberLoginCB();
        cb.setupSelect_Member().withMemberStatus();
        cb.query().queryMember().existsPurchaseList(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.query().queryMember().setMemberId_Equal(3);
            }
        });

        // ## Act ##
        memberLoginBhv.queryDelete(cb);

        // ## Assert ##
        {
            MemberLoginCB actualCB = new MemberLoginCB();
            actualCB.query().setMemberId_Equal(3);
            assertEquals(0, memberLoginBhv.selectCount(actualCB));
        }
        {
            MemberLoginCB actualCB = new MemberLoginCB();
            actualCB.query().setMemberId_Equal(5);
            assertNotSame(0, memberLoginBhv.selectCount(actualCB));
        }
    }

    // ===================================================================================
    //                                                                         BizOneToOne
    //                                                                         ===========
    public void test_ExistsReferrer_BixOneToOne() {
        // ## Arrange ##
        final Date date = DfTypeUtil.toDate("2008/09/15");
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberAddressAsValid(date);
        cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.query().queryMember().queryMemberAddressAsValid(date).setAddress_PrefixSearch("S");
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        for (Member member : memberList) {
            MemberAddress address = member.getMemberAddressAsValid();
            log(member.getMemberName() + ", " + address.getAddress());
            assertTrue(address.getAddress().startsWith("S"));
        }
    }

    // ===================================================================================
    //                                                                   NotExistsReferrer
    //                                                                   =================
    public void test_notExistsReferrer_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().notExistsPurchaseList(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.query().setPaymentCompleteFlg_Equal_True();
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertNotSame(0, memberList);
        memberBhv.loadPurchaseList(memberList, new ConditionBeanSetupper<PurchaseCB>() {
            public void setup(PurchaseCB cb) {
            }
        });
        for (Member member : memberList) {
            List<Purchase> purchaseList = member.getPurchaseList();
            boolean exists = false;
            for (Purchase purchase : purchaseList) {
                if (purchase.isPaymentCompleteFlgTrue()) {
                    exists = true;
                }
            }
            assertFalse(exists);
        }
    }

    // ===================================================================================
    //                                                                             Illegal
    //                                                                             =======
    public void test_existsReferrer_illegal() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                try {
                    // ## Act ##
                    subCB.setupSelect_Member();

                    // ## Assert ##
                    fail();
                } catch (SetupSelectIllegalPurposeException e) {
                    // OK
                    log(e.getMessage());
                }
                try {
                    // ## Act ##
                    subCB.specify();

                    // ## Assert ##
                    fail();
                } catch (SpecifyIllegalPurposeException e) {
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
            }
        });
    }
}
