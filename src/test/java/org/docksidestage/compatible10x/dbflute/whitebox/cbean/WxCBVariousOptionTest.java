package org.docksidestage.compatible10x.dbflute.whitebox.cbean;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.exception.DangerousResultSizeException;
import org.dbflute.exception.FetchingOverSafetySizeException;
import org.dbflute.exception.PagingOverSafetySizeException;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.7.2 (2010/06/18 Friday)
 */
public class WxCBVariousOptionTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                       Safety Result
    //                                                                       =============
    public void test_checkSafetyResult_selectList() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.checkSafetyResult(3);

        // ## Act ##
        try {
            memberBhv.selectList(cb);

            // ## Assert ##
            fail();
        } catch (DangerousResultSizeException e) {
            // OK
            log(e.getMessage());
            assertEquals(FetchingOverSafetySizeException.class, e.getCause().getClass());
        }

        // ## Act ##
        cb.fetchFirst(3);
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertEquals(3, memberList.size());
    }

    public void test_checkSafetyResult_selectPage() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.checkSafetyResult(3);
        cb.paging(2, 2);

        // ## Act ##
        try {
            memberBhv.selectPage(cb);

            // ## Assert ##
            fail();
        } catch (DangerousResultSizeException e) {
            // OK
            log(e.getMessage());
            assertEquals(3, e.getSafetyMaxResultSize());
            assertEquals(PagingOverSafetySizeException.class, e.getCause().getClass());
        }

        // ## Act ##
        cb.query().setMemberId_Equal(3);
        PagingResultBean<Member> memberPage = memberBhv.selectPage(cb);

        // ## Assert ##
        assertEquals(1, memberPage.size());
    }
}
