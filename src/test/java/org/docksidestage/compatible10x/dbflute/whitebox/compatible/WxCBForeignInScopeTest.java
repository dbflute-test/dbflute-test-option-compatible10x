package org.docksidestage.compatible10x.dbflute.whitebox.compatible;

import java.util.List;

import org.dbflute.cbean.result.ListResultBean;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.ProductCategoryCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberStatusBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.ProductCategoryBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberStatus;
import org.docksidestage.compatible10x.dbflute.exentity.ProductCategory;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 */
public class WxCBForeignInScopeTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;
    private MemberStatusBhv memberStatusBhv;
    private ProductCategoryBhv productCategoryBhv;

    // ===================================================================================
    //                                                                              Simple
    //                                                                              ======
    public void test_basic() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().inScopeMemberStatus(statusCB -> {
            statusCB.query().setDisplayOrder_GreaterEqual(2);
        });

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            MemberStatus status = memberStatusBhv.selectByPKValueWithDeletedCheck(member.getMemberStatusCode());
            assertTrue(status.getDisplayOrder() >= 2);
        }
    }

    public void test_inline() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().inline().inScopeMemberStatus(statusCB -> {
            statusCB.query().setDisplayOrder_GreaterEqual(2);
        });

        // ## Act ##
        List<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(memberList);
        for (Member member : memberList) {
            MemberStatus status = memberStatusBhv.selectByPKValueWithDeletedCheck(member.getMemberStatusCode());
            assertTrue(status.getDisplayOrder() >= 2);
        }
    }

    public void test_stringPK() {
        // ## Arrange ##
        ProductCategoryCB cb = new ProductCategoryCB();
        cb.setupSelect_ProductCategorySelf();
        cb.query().inScopeProductCategorySelf(parentCB -> {
            parentCB.query().setParentCategoryCode_IsNull();
        });

        // ## Act ##
        ListResultBean<ProductCategory> categoryList = productCategoryBhv.selectList(cb);

        // ## Assert ##
        assertHasAnyElement(categoryList);
        for (ProductCategory category : categoryList) {
            assertNull(category.getProductCategorySelf().getParentCategoryCode());
        }
    }
}
