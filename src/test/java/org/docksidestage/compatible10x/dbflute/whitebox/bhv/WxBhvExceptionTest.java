package org.docksidestage.compatible10x.dbflute.whitebox.bhv;

import org.dbflute.exception.EntityPrimaryKeyNotFoundException;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.9.1F (2011/11/05 Saturday)
 */
public class WxBhvExceptionTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                      Not PrimaryKey
    //                                                                      ==============
    public void test_updateNonstrict_PrimarykeyNotFound() {
        // ## Arrange ##
        Member member = new Member();
        member.setMemberName("Pixy");

        // ## Act ##
        try {
            memberBhv.updateNonstrict(member);

            // ## Assert ##
            fail();
        } catch (EntityPrimaryKeyNotFoundException e) {
            // OK
            log(e.getMessage());
        }
    }
}
