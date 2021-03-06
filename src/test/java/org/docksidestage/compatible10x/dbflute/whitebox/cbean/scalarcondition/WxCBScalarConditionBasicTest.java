package org.docksidestage.compatible10x.dbflute.whitebox.cbean.scalarcondition;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.dbflute.bhv.referrer.ConditionBeanSetupper;
import org.dbflute.cbean.coption.LikeSearchOption;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.ScalarQuery;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.cbean.scoping.SubQuery;
import org.dbflute.exception.QueryIllegalPurposeException;
import org.dbflute.exception.SpecifyRelationIllegalPurposeException;
import org.dbflute.util.Srl;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberServiceCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberStatusCB;
import org.docksidestage.compatible10x.dbflute.cbean.PurchaseCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.PurchaseBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberService;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.dbflute.exentity.Purchase;
import org.docksidestage.compatible10x.dbflute.exentity.ServiceRank;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxCBScalarConditionBasicTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;
    private PurchaseBhv purchaseBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_ScalarCondition_basic() {
        // ## Arrange ##
        Date expected = selectExpectedMaxBirthdateOnFormalized();

        MemberCB cb = new MemberCB();
        cb.query().setMemberStatusCode_Equal_Formalized();
        cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnBirthdate();
                subCB.query().setMemberStatusCode_Equal_Formalized();
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            assertEquals(expected, birthdate);
        }
    }

    protected Date selectExpectedMaxBirthdateOnFormalized() {
        Date expected = null;
        {
            MemberCB cb = new MemberCB();
            cb.query().setMemberStatusCode_Equal_Formalized();
            ListResultBean<Member> listAll = memberBhv.selectList(cb);
            for (Member member : listAll) {
                Date day = member.getBirthdate();
                if (day != null && (expected == null || expected.getTime() < day.getTime())) {
                    expected = day;
                }
            }
        }
        return expected;
    }

    public void test_ScalarCondition_operand() throws Exception {
        {
            // ## Arrange ##
            MemberCB cb = new MemberCB();

            // ## Act ##
            cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            });

            // ## Assert ##
            assertTrue(cb.toDisplaySql().contains(" = "));
        }
        {
            // ## Arrange ##
            MemberCB cb = new MemberCB();

            // ## Act ##
            cb.query().scalar_NotEqual().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            });

            // ## Assert ##
            assertTrue(cb.toDisplaySql().contains(" <> "));
        }
        {
            // ## Arrange ##
            MemberCB cb = new MemberCB();

            // ## Act ##
            cb.query().scalar_GreaterThan().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            });

            // ## Assert ##
            assertTrue(cb.toDisplaySql().contains(" > "));
        }
        {
            // ## Arrange ##
            MemberCB cb = new MemberCB();

            // ## Act ##
            cb.query().scalar_LessThan().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            });

            // ## Assert ##
            assertTrue(cb.toDisplaySql().contains(" < "));
        }
        {
            // ## Arrange ##
            MemberCB cb = new MemberCB();

            // ## Act ##
            cb.query().scalar_GreaterEqual().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            });

            // ## Assert ##
            assertTrue(cb.toDisplaySql().contains(" >= "));
        }
        {
            // ## Arrange ##
            MemberCB cb = new MemberCB();

            // ## Act ##
            cb.query().scalar_LessEqual().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            });

            // ## Assert ##
            assertTrue(cb.toDisplaySql().contains(" <= "));
        }
    }

    public void test_ScalarCondition_severalCall() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.specify().columnMemberName();
        cb.query().scalar_NotEqual().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnBirthdate();
            }
        });
        cb.query().scalar_NotEqual().min(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnFormalizedDatetime();
            }
        });

        // ## Act ##
        memberBhv.selectList(cb); // expect no exception

        // ## Assert ##
        String sql = cb.toDisplaySql();
        assertTrue(Srl.containsAll(sql, "max", "min", "BIRTHDATE", "FORMALIZED_DATETIME"));
    }

    // ===================================================================================
    //                                                                            Relation
    //                                                                            ========
    public void test_scalarCondition_OneToOne() {
        // ## Arrange ##
        Integer avg = memberBhv.scalarSelect(Integer.class).avg(new ScalarQuery<MemberCB>() {
            public void query(MemberCB cb) {
                cb.specify().columnMemberId();
            }
        }).get();
        PurchaseCB cb = new PurchaseCB();
        cb.query().queryMember().scalar_GreaterThan().avg(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnMemberId();
            }
        });

        // ## Act ##
        ListResultBean<Purchase> purchaseList = purchaseBhv.selectList(cb);

        // ## Assert ##
        assertNotSame(0, purchaseList.size());
        for (Purchase purchase : purchaseList) {
            log(purchase);
            assertTrue(avg < purchase.getMemberId());
        }
    }

    // ===================================================================================
    //                                                                          ParitionBy
    //                                                                          ==========
    public void test_ScalarCondition_PartitionBy_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnBirthdate();
            }
        }).partitionBy(new SpecifyQuery<MemberCB>() {
            public void specify(MemberCB cb) {
                cb.specify().columnMemberStatusCode();
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        ListResultBean<MemberStatus> statusList = memberStatusBhv.selectList(new MemberStatusCB());
        memberStatusBhv.loadMemberList(statusList, new ConditionBeanSetupper<MemberCB>() {
            public void setup(MemberCB cb) {
                cb.query().addOrderBy_Birthdate_Desc();
            }
        });
        Map<String, Date> statusMap = new HashMap<String, Date>();
        for (MemberStatus status : statusList) {
            statusMap.put(status.getMemberStatusCode(), status.getMemberList().get(0).getBirthdate());
        }
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            log(member.getMemberName() + ", " + toString(birthdate, "yyyy/MM/dd"));
            Date expectedDate = statusMap.get(member.getMemberStatusCode());
            assertEquals(expectedDate, birthdate);
        }
    }

    public void test_ScalarCondition_PartitionBy_relation() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberServiceAsOne().withServiceRank();
        cb.query().queryMemberServiceAsOne().scalar_Equal().max(new SubQuery<MemberServiceCB>() {
            public void query(MemberServiceCB subCB) {
                subCB.specify().columnServicePointCount();
            }
        }).partitionBy(new SpecifyQuery<MemberServiceCB>() {
            public void specify(MemberServiceCB cb) {
                cb.specify().columnServiceRankCode();
                // unsupported
                //cb.specify().specifyMember().columnMemberStatusCode();
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        Set<String> rankSet = new HashSet<String>();
        for (Member member : memberList) {
            MemberService service = member.getMemberServiceAsOne();
            ServiceRank rank = service.getServiceRank();
            log(member.getMemberName() + ", " + service.getServicePointCount() + ", " + rank.getServiceRankName());
            rankSet.add(rank.getServiceRankCode());
        }
        assertEquals(rankSet.size(), memberList.size());
    }

    public void test_ScalarCondition_PartitionBy_vs_DerivedReferrer() {
        // ## Arrange ##
        // ## Act ##
        ListResultBean<Member> partitionByList;
        {
            MemberCB cb = new MemberCB();
            cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().columnBirthdate();
                }
            }).partitionBy(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnMemberStatusCode();
                }
            });
            partitionByList = memberBhv.selectList(cb);
        }
        ListResultBean<Member> derivedReferrerList;
        {
            MemberCB cb = new MemberCB();
            cb.columnQuery(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnBirthdate();
                }
            }).equal(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().specifyMemberStatus().derivedMemberList().max(new SubQuery<MemberCB>() {
                        public void query(MemberCB subCB) {
                            subCB.specify().columnBirthdate();
                        }
                    }, null);
                }
            });
            derivedReferrerList = memberBhv.selectList(cb);
        }

        // ## Assert ##
        assertHasAnyElement(partitionByList);
        assertHasAnyElement(derivedReferrerList);
        StringBuilder sb = new StringBuilder();
        sb.append(ln()).append("[PartitionBy]");
        for (Member member : partitionByList) {
            sb.append(ln()).append(" ").append(member.getMemberName());
            sb.append(", ").append(member.getMemberStatusCode());
            sb.append(", ").append(toString(member.getBirthdate(), "yyyy/MM/dd"));
        }
        sb.append(ln()).append("[DerivedReferrer]");
        for (Member member : derivedReferrerList) {
            sb.append(ln()).append(" ").append(member.getMemberName());
            sb.append(", ").append(member.getMemberStatusCode());
            sb.append(", ").append(toString(member.getBirthdate(), "yyyy/MM/dd"));
        }
        log(sb);
        assertEquals(partitionByList, derivedReferrerList);
    }

    public void test_ScalarCondition_PartitionBy_outerQuery() {
        // ## Arrange ##
        Date maxBirthdate = toUtilDate("9999/12/31");
        {
            Member member = new Member();
            member.setMemberId(1);
            member.setMemberName("foovic");
            member.setMemberStatusCode_Formalized();
            member.setBirthdate(maxBirthdate);
            memberBhv.updateNonstrict(member);
        }
        {
            Member member = new Member();
            member.setMemberId(2);
            member.setMemberName("bar");
            member.setMemberStatusCode_Formalized();
            member.setBirthdate(maxBirthdate);
            memberBhv.updateNonstrict(member);
        }

        MemberCB cb = new MemberCB();
        cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnBirthdate();
                subCB.query().setMemberName_LikeSearch("vi", new LikeSearchOption().likeContain());
            }
        }).partitionBy(new SpecifyQuery<MemberCB>() {
            public void specify(MemberCB cb) {
                cb.specify().columnMemberStatusCode();
            }
        });
        cb.query().setMemberName_LikeSearch("vi", new LikeSearchOption().likeContain());

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        int count = 0;
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            log(member.getMemberName(), birthdate);
            if (member.isMemberStatusCodeFormalized()) {
                assertEquals(maxBirthdate, birthdate);
                assertEquals(1, member.getMemberId());
                ++count;
            }
        }
        assertEquals(1, count);
    }

    public void test_ScalarCondition_PartitionBy_nonOuterQuery() {
        // ## Arrange ##
        Date maxBirthdate = toUtilDate("9999/12/31");
        {
            Member member = new Member();
            member.setMemberId(1);
            member.setMemberName("foovic");
            member.setMemberStatusCode_Formalized();
            member.setBirthdate(maxBirthdate);
            memberBhv.updateNonstrict(member);
        }
        {
            Member member = new Member();
            member.setMemberId(2);
            member.setMemberName("bar");
            member.setMemberStatusCode_Formalized();
            member.setBirthdate(maxBirthdate);
            memberBhv.updateNonstrict(member);
        }

        MemberCB cb = new MemberCB();
        cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnBirthdate();
                subCB.query().setMemberName_LikeSearch("vi", new LikeSearchOption().likeContain());
            }
        }).partitionBy(new SpecifyQuery<MemberCB>() {
            public void specify(MemberCB cb) {
                cb.specify().columnMemberStatusCode();
            }
        });

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        int count = 0;
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            log(member.getMemberName(), birthdate);
            if (member.isMemberStatusCodeFormalized()) {
                assertEquals(maxBirthdate, birthdate);
                ++count;
            }
        }
        assertEquals(2, count);
    }

    // ===================================================================================
    //                                                                             Illegal
    //                                                                             =======
    public void test_scalarCondition_specifyRelation() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberStatusCode_Equal_Formalized();

        // ## Act ##
        try {
            cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
                public void query(MemberCB subCB) {
                    subCB.specify().specifyMemberStatus();
                }
            });

            // ## Assert ##
            fail();
        } catch (SpecifyRelationIllegalPurposeException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_scalarCondition_partitionBy_query() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().scalar_Equal().max(new SubQuery<MemberCB>() {
            public void query(MemberCB subCB) {
                subCB.specify().columnBirthdate();
            }
        }).partitionBy(new SpecifyQuery<MemberCB>() {
            public void specify(MemberCB cb) {
                cb.specify().columnMemberStatusCode();
                cb.query().setMemberStatusCode_Equal_Formalized();
            }
        });

        // ## Act ##
        try {
            memberBhv.selectList(cb);
            // ## Assert ##
            fail();
        } catch (QueryIllegalPurposeException e) {
            // OK
            log(e.getMessage());
        }
    }
}
