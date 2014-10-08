package org.docksidestage.compatible10x.dbflute.topic;

import java.sql.SQLException;

import org.dbflute.cbean.result.ListResultBean;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.5.1 (2009/06/10 Wednesday)
 */
public class NoTransactionTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                          ColumnInfo
    //                                                                          ==========
    public void test_noTransaction() throws SQLException {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setBirthdate_IsNotNull();

        // ## Act & Assert ##
        for (int i = 0; i < 300; i++) { // Expect the connection pool won't be empty
            ListResultBean<Member> memberList = memberBhv.selectList(cb); // Expect no exception
            assertFalse(memberList.isEmpty());
        }
    }
}
