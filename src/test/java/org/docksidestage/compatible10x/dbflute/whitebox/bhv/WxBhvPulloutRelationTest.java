package org.docksidestage.compatible10x.dbflute.whitebox.bhv;

import java.util.List;
import java.util.Set;

import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberAddress;
import org.docksidestage.compatible10x.dbflute.exentity.MemberSecurity;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxBhvPulloutRelationTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                         many-to-one
    //                                                                         ===========
    public void test_pullout_ManyToOne_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Act ##
        List<MemberStatus> pulloutList = memberBhv.pulloutMemberStatus(memberList);

        // ## Assert ##
        Set<String> expectedCodeSet = newHashSet();
        for (Member member : memberList) {
            MemberStatus status = member.getMemberStatus();
            expectedCodeSet.add(status.getMemberStatusCode());
        }
        assertEquals(expectedCodeSet.size(), pulloutList.size());
        Set<String> actualCodeSet = newHashSet();
        for (MemberStatus status : pulloutList) {
            actualCodeSet.add(status.getMemberStatusCode());
            List<Member> reverseList = status.getMemberList();
            assertHasAnyElement(reverseList);
            for (Member member : reverseList) {
                assertEquals(status.getMemberStatusCode(), member.getMemberStatusCode());
            }
        }
        assertEquals(expectedCodeSet, actualCodeSet);
    }

    // ===================================================================================
    //                                                                          one-to-one
    //                                                                          ==========
    public void test_pullout_OneToOne_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberSecurityAsOne();
        List<Integer> memberIdList = newArrayList(1, 2, 3, 4);
        cb.query().setMemberId_InScope(memberIdList);
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Act ##
        List<MemberSecurity> pulloutList = memberBhv.pulloutMemberSecurityAsOne(memberList);

        // ## Assert ##
        Set<Integer> expectedIdSet = newHashSet();
        for (Member member : memberList) {
            MemberSecurity security = member.getMemberSecurityAsOne();
            expectedIdSet.add(security.getMemberId());
        }
        assertEquals(expectedIdSet.size(), pulloutList.size());
        Set<Integer> actualIdSet = newHashSet();
        for (MemberSecurity security : pulloutList) {
            actualIdSet.add(security.getMemberId());
        }
        assertEquals(expectedIdSet, actualIdSet);
    }

    public void test_pullout_BizOneToOne() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberAddressAsValid(currentUtilDate());
        List<Integer> memberIdList = newArrayList(1, 2, 3, 4);
        cb.query().setMemberId_InScope(memberIdList);
        List<Member> memberList = memberBhv.selectList(cb);
        // ## Act ##
        List<MemberAddress> pulloutList = memberBhv.pulloutMemberAddressAsValid(memberList);
        // ## Assert ##
        Set<Integer> expectedIdSet = newHashSet();
        for (Member member : memberList) {
            MemberAddress address = member.getMemberAddressAsValid();
            if (address != null) {
                expectedIdSet.add(address.getMemberId());
            }
        }
        assertEquals(expectedIdSet.size(), pulloutList.size());
        Set<Integer> actualIdSet = newHashSet();
        for (MemberAddress address : pulloutList) {
            actualIdSet.add(address.getMemberId());
        }
        assertEquals(expectedIdSet, actualIdSet);
    }

    // ===================================================================================
    //                                                                     Illegal Pattern
    //                                                                     ===============
    public void test_pullout_NoPKCode_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberSecurityAsOne();
        List<Integer> memberIdList = newArrayList(1, 2, 3, 4);
        cb.query().setMemberId_InScope(memberIdList);
        List<Member> memberList = memberBhv.selectList(cb);
        for (Member member : memberList) {
            member.setMemberId(null);
        }

        // ## Act ##
        List<MemberSecurity> pulloutList = memberBhv.pulloutMemberSecurityAsOne(memberList);

        // ## Assert ##
        Set<Integer> expectedIdSet = newHashSet();
        for (Member member : memberList) {
            MemberSecurity security = member.getMemberSecurityAsOne();
            expectedIdSet.add(security.getMemberId());
        }
        assertEquals(expectedIdSet.size(), pulloutList.size());
        Set<Integer> actualIdSet = newHashSet();
        for (MemberSecurity security : pulloutList) {
            actualIdSet.add(security.getMemberId());
        }
        assertEquals(expectedIdSet, actualIdSet);
    }

    public void test_pullout_NoFKCode_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.setupSelect_MemberStatus();
        List<Member> memberList = memberBhv.selectList(cb);
        for (Member member : memberList) {
            member.setMemberStatusCode(null);
        }

        // ## Act ##
        List<MemberStatus> pulloutList = memberBhv.pulloutMemberStatus(memberList);

        // ## Assert ##
        Set<String> expectedCodeSet = newHashSet();
        for (Member member : memberList) {
            MemberStatus status = member.getMemberStatus();
            expectedCodeSet.add(status.getMemberStatusCode());
        }
        assertEquals(expectedCodeSet.size(), pulloutList.size());
        Set<String> actualCodeSet = newHashSet();
        for (MemberStatus status : pulloutList) {
            actualCodeSet.add(status.getMemberStatusCode());
        }
        assertEquals(expectedCodeSet, actualCodeSet);
    }

    // ===================================================================================
    //                                                                      Hash Collision
    //                                                                      ==============
    public void test_HashCollision_basic() throws Exception {
        // ## Arrange ##
        List<Member> memberList = newArrayList();
        final MemberStatus firstStatus;
        {
            Member member = new Member();
            member.setMemberId(1);
            firstStatus = new MemberStatus() {
                private static final long serialVersionUID = 1L;

                @Override
                public int instanceHash() {
                    return 1;
                }
            };
            firstStatus.setMemberStatusCode_Formalized();
            firstStatus.setMemberStatusName("sea");
            member.setMemberStatus(firstStatus);
            memberList.add(member);
        }
        {
            Member member = new Member();
            member.setMemberId(2);
            MemberStatus status = new MemberStatus() {
                private static final long serialVersionUID = 1L;

                @Override
                public int instanceHash() {
                    return 1;
                }
            };
            status.setMemberStatusCode_Provisional();
            status.setMemberStatusName("land");
            member.setMemberStatus(status);
            memberList.add(member);
        }
        {
            Member member = new Member();
            member.setMemberId(3);
            MemberStatus status = new MemberStatus() {
                private static final long serialVersionUID = 1L;

                @Override
                public int instanceHash() {
                    return 2;
                }
            };
            status.setMemberStatusCode_Formalized();
            status.setMemberStatusName("iks");
            member.setMemberStatus(status);
            memberList.add(member);
        }
        {
            Member member = new Member();
            member.setMemberId(4);
            member.setMemberStatus(firstStatus);
            memberList.add(member);
        }

        // ## Act ##
        List<MemberStatus> statusList = memberBhv.pulloutMemberStatus(memberList);

        // ## Assert ##
        assertHasAnyElement(statusList);
        for (MemberStatus status : statusList) {
            log(status, status.instanceHash());
        }
        assertEquals(3, statusList.size());
        assertEquals("sea", statusList.get(0).getMemberStatusName());
        assertEquals("land", statusList.get(1).getMemberStatusName());
        assertEquals("iks", statusList.get(2).getMemberStatusName());
    }
}
