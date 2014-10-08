package org.docksidestage.compatible10x.dbflute.whitebox.outsidesql;

import java.util.List;
import java.util.Map;

import org.dbflute.outsidesql.irregular.IrgMapListCursorHandler;
import org.docksidestage.compatible10x.dbflute.bsentity.dbmeta.MemberDbm;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.SimpleMemberPmb;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 1.0.5F (2014/05/12 Monday)
 */
public class WxOutsideSqlIrregularTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private MemberBhv memberBhv;

    // ===================================================================================
    //                                                                  Insert with Cursor
    //                                                                  ==================
    public void test_IrgMapListCursorHandler_basic() throws Exception {
        // ## Arrange ##
        String path = MemberBhv.PATH_selectSimpleMember;
        SimpleMemberPmb pmb = new SimpleMemberPmb();
        pmb.setMemberName_PrefixSearch("S");

        // ## Act ##
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) memberBhv.outsideSql().cursorHandling()
                .selectCursor(path, pmb, new IrgMapListCursorHandler());

        // ## Assert ##
        assertHasAnyElement(resultList);
        for (Map<String, Object> resultMap : resultList) {
            log(resultMap);
            String memberName = (String) resultMap.get(MemberDbm.getInstance().columnMemberName().getColumnDbName());
            assertTrue(memberName.startsWith("S"));
        }
    }
}
