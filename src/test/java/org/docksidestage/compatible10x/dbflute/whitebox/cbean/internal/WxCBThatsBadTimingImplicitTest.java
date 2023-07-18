package org.docksidestage.compatible10x.dbflute.whitebox.cbean.internal;

import java.util.Date;

import org.dbflute.cbean.coption.LikeSearchOption;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.OrQuery;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.cbean.scoping.UnionQuery;
import org.dbflute.exception.SetupSelectIllegalPurposeException;
import org.dbflute.exception.SpecifyIllegalPurposeException;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberLoginCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberWithdrawalCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.2.7 (2023/07/18 Tuesday at ichihara)
 */
public class WxCBThatsBadTimingImplicitTest extends UnitContainerTestCase {

    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;

    // ===================================================================================
    //                                                                           All Right
    //                                                                           =========
    public void test_badTiming_allRight() {
        // ## Arrange ##
        final MemberStatusCB cb = new MemberStatusCB();
        cb.query().setDisplayOrder_Equal(3);
        cb.query().existsMemberList(new SubQuery<MemberCB>() {
            public void query(MemberCB memberCB) {
                memberCB.query().setBirthdate_LessEqual(new Date());
                memberCB.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                    public void query(PurchaseCB purchaseCB) {
                        purchaseCB.query().setPurchaseCount_GreaterEqual(2);
                    }
                });
                memberCB.query().existsMemberWithdrawalAsOne(new SubQuery<MemberWithdrawalCB>() {
                    public void query(MemberWithdrawalCB subCB) {
                        final LikeSearchOption option = new LikeSearchOption().likeContain().escapeByPipeLine();
                        subCB.query().queryWithdrawalReason().setWithdrawalReasonText_LikeSearch("xxx", option);
                        subCB.union(new UnionQuery<MemberWithdrawalCB>() {
                            public void query(MemberWithdrawalCB unionCB) {
                                unionCB.query().setWithdrawalReasonInputText_IsNotNull();
                            }
                        });
                    }
                });
            }
        });
        cb.query().setMemberStatusCode_Equal_Formalized();
        cb.query().existsMemberLoginList(new SubQuery<MemberLoginCB>() {
            public void query(MemberLoginCB subCB) {
                subCB.query().queryMemberStatus().existsMemberList(new SubQuery<MemberCB>() {
                    public void query(MemberCB subCB) {
                        subCB.query().setBirthdate_GreaterEqual(new Date());
                    }
                });
            }
        });
        cb.query().addOrderBy_DisplayOrder_Asc().addOrderBy_MemberStatusCode_Desc();

        // ## Act ##
        memberStatusBhv.selectList(cb); // expect no exception

        // ## Assert ##
        log(ln() + cb.toDisplaySql());
    }

    // ===================================================================================
    //                                                                         SetupSelect
    //                                                                         ===========
    public void test_badTiming_setupSelect() {
        // ## Arrange ##
        // ## Act ##
        {
            final MemberCB cb = createCB();
            cb.specify().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    subCB.specify().columnPurchaseDatetime();
                    cb.setupSelect_MemberStatus(); // expect no exception
                }
            }, Member.ALIAS_latestLoginDatetime);
        }
        {
            final MemberCB cb = createCB();
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {

                public void query(PurchaseCB subCB) {
                    cb.setupSelect_MemberStatus(); // expect no exception
                }
            });
        }
        try {
            // ## Act ##
            final MemberCB cb = createCB();
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                public void query(final PurchaseCB subCB) {
                    subCB.query().queryProduct().existsPurchaseList(new SubQuery<PurchaseCB>() {
                        public void query(PurchaseCB noNoNoNoNoNoNoNoNoCB) {
                            subCB.setupSelect_Member(); // also illegal purpose
                        }
                    });
                }
            });
            // ## Assert ##
            fail();
        } catch (SetupSelectIllegalPurposeException e) { // depends on order
            log(e.getMessage());
        }
        {
            final MemberCB cb = createCB();
            cb.query().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    subCB.specify().columnPurchaseDatetime();
                    cb.setupSelect_MemberStatus(); // expect no exception
                }
            }).lessEqual(currentUtilDate());
        }
        {
            final MemberCB cb = createCB();
            cb.orScopeQuery(new OrQuery<MemberCB>() {
                public void query(MemberCB orCB) {
                    cb.setupSelect_MemberStatus(); // no check (can't be helped)
                }
            });
            cb.columnQuery(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnBirthdate();
                }
            }).lessEqual(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnFormalizedDatetime();
                }
            });
            memberBhv.selectList(cb); // expect no exception
        }
    }

    // ===================================================================================
    //                                                                           specify()
    //                                                                           =========
    public void test_badTiming_specify() {
        {
            final MemberCB cb = createCB();
            cb.specify().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    subCB.specify().columnPurchaseDatetime();
                    cb.specify(); // expect no exception
                }
            }, Member.ALIAS_latestLoginDatetime);
        }
        {
            final MemberCB cb = createCB();
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {

                public void query(PurchaseCB subCB) {
                    cb.specify(); // expect no exception
                }
            });
        }
        try {
            // ## Act ##
            final MemberCB cb = createCB();
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                public void query(final PurchaseCB subCB) {
                    subCB.query().queryProduct().existsPurchaseList(new SubQuery<PurchaseCB>() {
                        public void query(PurchaseCB noNoNoNoNoNoNoNoNoCB) {
                            subCB.specify();
                        }
                    });
                }
            });
            // ## Assert ##
            fail();
        } catch (SpecifyIllegalPurposeException e) { // depends on check order

            log(e.getMessage());
        }

        {
            final MemberCB cb = createCB();
            cb.query().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    subCB.specify().columnPurchaseDatetime();
                    cb.specify(); // expect no exception
                }
            }).lessEqual(currentUtilDate());
        }
        {
            final MemberCB cb = createCB();
            cb.orScopeQuery(new OrQuery<MemberCB>() {
                public void query(MemberCB orCB) {
                    cb.specify().columnBirthdate(); // no check (can't be helped)
                }
            });
            cb.columnQuery(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnBirthdate();
                }
            }).lessEqual(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnFormalizedDatetime();
                }
            });
            memberBhv.selectList(cb); // expect no exception
        }
    }

    // ===================================================================================
    //                                                                             query()
    //                                                                             =======
    public void test_badTiming_query() {
        {
            final MemberCB cb = createCB();
            cb.specify().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    subCB.specify().columnPurchaseDatetime();
                    cb.query(); // expect no exception
                }
            }, Member.ALIAS_latestLoginDatetime);
        }
        {
            final MemberCB cb = createCB();
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    cb.query(); // expect no exception
                }
            });
        }
        {
            final MemberCB cb = createCB();
            cb.query().queryMemberStatus().existsMemberLoginList(new SubQuery<MemberLoginCB>() {
                public void query(MemberLoginCB subCB) {
                    cb.query(); // expect no exception
                }
            });
        }
        {
            final MemberCB cb = createCB();
            cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
                public void query(final PurchaseCB subCB) {
                    subCB.query().queryProduct().existsPurchaseList(new SubQuery<PurchaseCB>() {
                        public void query(PurchaseCB noNoNoNoNoNoNoNoNoCB) {
                            subCB.query(); // expect no exception
                        }
                    });
                }
            });
        }
        {
            final MemberCB cb = createCB();
            cb.query().derivedPurchaseList().max(new SubQuery<PurchaseCB>() {
                public void query(PurchaseCB subCB) {
                    subCB.specify().columnPurchaseDatetime();
                    cb.query(); // expect no exception
                }
            }).lessEqual(currentUtilDate());
        }
        {
            final MemberCB cb = createCB();
            cb.orScopeQuery(new OrQuery<MemberCB>() {
                public void query(MemberCB orCB) {
                    cb.query().addOrderBy_Birthdate_Asc(); // no check (can't be helped)
                }
            });
            cb.columnQuery(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnBirthdate();
                }
            }).lessEqual(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnFormalizedDatetime();
                }
            });
            memberBhv.selectList(cb); // expect no exception
        }
    }

    // ===================================================================================
    //                                                                        Dream Cruise
    //                                                                        ============
    public void test_badTiming_DreamCruise() {
        // ## Arrange ##
        final MemberCB cb = createCB();
        cb.specify().columnBirthdate();
        final MemberCB dreamCruiseCB = cb.dreamCruiseCB();
        cb.query().existsPurchaseList(new SubQuery<PurchaseCB>() {
            public void query(PurchaseCB subCB) {
                subCB.query().queryProduct().notExistsPurchaseList(new SubQuery<PurchaseCB>() {
                    public void query(PurchaseCB subCB) {
                        subCB.columnQuery(new SpecifyQuery<PurchaseCB>() {
                            public void specify(PurchaseCB cb) {
                                cb.specify().columnMemberId();
                            }
                        }).notEqual(new SpecifyQuery<PurchaseCB>() {
                            public void specify(PurchaseCB cb) {
                                cb.overTheWaves(dreamCruiseCB.specify().columnMemberId());
                            }
                        });
                    }
                });
            }
        });
        cb.query().existsMemberLoginList(new SubQuery<MemberLoginCB>() {
            public void query(MemberLoginCB subCB) {
                cb.setupSelect_MemberStatus(); // no check after dream cruise (can't be helped)
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            log(member);
            assertNotNull(member.getMemberStatus());
        }
    }

    // ===================================================================================
    //                                                                         Test Helper
    //                                                                         ===========
    protected MemberCB createCB() {
        final MemberCB cb = new MemberCB();
        // to test implicit behavior
        //cb.enableThatsBadTiming();
        return cb;
    }
}
