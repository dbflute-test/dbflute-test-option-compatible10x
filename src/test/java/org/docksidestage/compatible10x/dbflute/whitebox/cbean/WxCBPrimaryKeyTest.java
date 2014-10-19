package org.docksidestage.compatible10x.dbflute.whitebox.cbean;

import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxCBPrimaryKeyTest extends UnitContainerTestCase {

    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                  acceptPrimaryKey()
    //                                                                  ==================
    public void test_acceptPrimaryKey() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        cb.acceptPrimaryKey(3);

        // ## Assert ##
        assertEquals(3, cb.query().xdfgetMemberId().getFixedQuery().get("equal"));
    }

    // ===================================================================================
    //                                                                    acceptUniqueOf()
    //                                                                    ================
    public void test_acceptUniqueOf_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.acceptUniqueOf("Pixy");

        // ## Act ##
        Member member = memberBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        assertEquals("Pixy", member.getMemberAccount());
    }

    public void test_acceptUniqueOf_null() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        // ## Act ##
        try {
            cb.acceptUniqueOf(null);
            // ## Assert ##
            fail();
        } catch (IllegalArgumentException e) {
            log(e.getMessage());
        }
    }
}
