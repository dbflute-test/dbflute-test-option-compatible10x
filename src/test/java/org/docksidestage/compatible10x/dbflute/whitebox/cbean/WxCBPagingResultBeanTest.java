package org.docksidestage.compatible10x.dbflute.whitebox.cbean;

import java.util.List;

import org.dbflute.cbean.paging.numberlink.PageNumberLink;
import org.dbflute.cbean.paging.numberlink.PageNumberLinkSetupper;
import org.dbflute.cbean.paging.numberlink.group.PageGroupBean;
import org.dbflute.cbean.paging.numberlink.range.PageRangeBean;
import org.dbflute.cbean.result.PagingResultBean;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.0.0 (2012/10/16 Tuesday)
 */
public class WxCBPagingResultBeanTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    public void test_paging_noData() {
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setMemberName_Equal("no exist");
        cb.query().addOrderBy_MemberName_Asc();
        cb.paging(4, 1);

        // ## Act ##
        PagingResultBean<Member> page = memberBhv.selectPage(cb);

        // ## Assert ##
        assertHasZeroElement(page);
        PageRangeBean pageRange = page.pageRange(op -> op.rangeSize(2));
        List<PageNumberLink> linkList = pageRange.buildPageNumberLinkList(new PageNumberLinkSetupper<PageNumberLink>() {
            public PageNumberLink setup(int pageNumberElement, boolean current) {
                return new PageNumberLink().initialize(pageNumberElement, current, "...");
            }
        });
        assertHasOnlyOneElement(linkList);
        PageNumberLink numberLink = linkList.get(0);
        log(numberLink);
        assertEquals(1, numberLink.getPageNumberElement());
        assertTrue(numberLink.isCurrent());
    }

    // ===================================================================================
    //                                                                     PageGroup/Range
    //                                                                     ===============
    public void test_selectPage_PageRangeOption_PageGroupOption() {
        // ## Arrange ##
        // ## Act ##
        MemberCB cb2 = new MemberCB();
        cb2.query().addOrderBy_MemberName_Asc();
        cb2.paging(4, 2);
        PagingResultBean<Member> page2 = memberBhv.selectPage(cb2);

        MemberCB cb3 = new MemberCB();
        cb3.query().addOrderBy_MemberName_Asc();
        cb3.paging(4, 3);
        PagingResultBean<Member> page3 = memberBhv.selectPage(cb3);

        // ## Assert ##
        assertNotSame(0, page3.size());

        log("{PageRange}");
        {
            {
                PageRangeBean pageRange = page2.pageRange(op -> op.rangeSize(2));
                boolean existsPre = pageRange.isExistPrePageRange();
                boolean existsNext = pageRange.isExistNextPageRange();
                log("    page2: " + existsPre + " " + pageRange.createPageNumberList() + " " + existsNext);
            }
            {
                PageRangeBean pageRange = page3.pageRange(op -> op.rangeSize(2));
                boolean existsPre = pageRange.isExistPrePageRange();
                boolean existsNext = pageRange.isExistNextPageRange();
                log("    page3: " + existsPre + " " + pageRange.createPageNumberList() + " " + existsNext);
            }
            log("PagingResultBean.toString():" + ln() + " " + page2 + ln() + " " + page3);
            log("");
        }
        log("{PageRange-fillLimit}");
        {
            {
                PageRangeBean pageRange = page2.pageRange(op -> op.rangeSize(2).fillLimit());
                boolean existsPre = pageRange.isExistPrePageRange();
                boolean existsNext = pageRange.isExistNextPageRange();
                log("    page2: " + existsPre + " " + pageRange.createPageNumberList() + " " + existsNext);
            }
            {
                PageRangeBean pageRange = page3.pageRange(op -> op.rangeSize(2).fillLimit());
                boolean existsPre = pageRange.isExistPrePageRange();
                boolean existsNext = pageRange.isExistNextPageRange();
                log("    page3: " + existsPre + " " + pageRange.createPageNumberList() + " " + existsNext);
            }
            log("PagingResultBean.toString():" + ln() + " " + page2 + ln() + " " + page3);
            log("");
        }
        log("{PageGroup}");
        {
            {
                PageGroupBean pageGroup = page2.pageGroup(op -> op.groupSize(2));
                boolean existsPre = pageGroup.isExistPrePageGroup();
                boolean existsNext = pageGroup.isExistNextPageGroup();
                log("    page2: " + existsPre + " " + pageGroup.createPageNumberList() + " " + existsNext);
            }
            {
                PageGroupBean pageGroup = page3.pageGroup(op -> op.groupSize(2));
                boolean existsPre = pageGroup.isExistPrePageGroup();
                boolean existsNext = pageGroup.isExistNextPageGroup();
                log("    page3: " + existsPre + " " + pageGroup.createPageNumberList() + " " + existsNext);
            }
            log("PagingResultBean.toString():" + ln() + " " + page2 + ln() + " " + page3);
            log("");
        }
    }
}
