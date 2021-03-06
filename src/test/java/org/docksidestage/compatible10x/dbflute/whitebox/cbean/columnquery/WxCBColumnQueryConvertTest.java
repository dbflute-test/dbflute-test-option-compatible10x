package org.docksidestage.compatible10x.dbflute.whitebox.cbean.columnquery;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.6.0 (2008/01/16 Wednesday)
 */
public class WxCBColumnQueryConvertTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                                Date 
    //                                                                                ====
    public void test_ColumnQuery_convert_addDay() throws Exception {
        // ## Arrange ##
        int countAll = memberBhv.selectCount(new MemberCB());
        MemberCB cb = new MemberCB();
        cb.columnQuery(new SpecifyQuery<MemberCB>() {
            public void specify(MemberCB cb) {
                cb.specify().columnFormalizedDatetime();
            }
        }).lessEqual(new SpecifyQuery<MemberCB>() {
            public void specify(MemberCB cb) {
                cb.specify().columnBirthdate();
            }
        }).left().convert(op -> op.coalesce("1192-12-31")) // left
                .right().convert(op -> op.coalesce("3000-12-31").addYear(100)); // right
        cb.query().addOrderBy_MemberId_Asc();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        assertEquals(countAll, memberList.size());
    }

    // ===================================================================================
    //                                                                         Illegal Use 
    //                                                                         ===========
    public void test_ColumnQuery_convert_nullSet() throws Exception {
        // ## Arrange ##
        MemberCB cb = new MemberCB();

        try {
            // ## Act ##
            cb.columnQuery(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnFormalizedDatetime();
                }
            }).greaterEqual(new SpecifyQuery<MemberCB>() {
                public void specify(MemberCB cb) {
                    cb.specify().columnBirthdate();
                }
            }).convert(op -> op.addDay((Integer) null));

            // ## Assert ##
            fail();
        } catch (IllegalConditionBeanOperationException e) {
            log(e.getMessage());
        }
    }
}
