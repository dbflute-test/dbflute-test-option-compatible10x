/*
 * Copyright 2004-2014 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.compatible10x.dbflute.cbean.cq.ciq;

import java.util.Map;
import org.dbflute.cbean.*;
import org.dbflute.cbean.ckey.*;
import org.dbflute.cbean.coption.ConditionOption;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.docksidestage.compatible10x.dbflute.cbean.*;
import org.docksidestage.compatible10x.dbflute.cbean.cq.bs.*;
import org.docksidestage.compatible10x.dbflute.cbean.cq.*;

/**
 * The condition-query for in-line of SUMMARY_PRODUCT.
 * @author DBFlute(AutoGenerator)
 */
public class SummaryProductCIQ extends AbstractBsSummaryProductCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsSummaryProductCQ _myCQ;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public SummaryProductCIQ(ConditionQuery referrerQuery, SqlClause sqlClause
                        , String aliasName, int nestLevel, BsSummaryProductCQ myCQ) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
        _myCQ = myCQ;
        _foreignPropertyName = _myCQ.xgetForeignPropertyName(); // accept foreign property name
        _relationPath = _myCQ.xgetRelationPath(); // accept relation path
        _inline = true;
    }

    // ===================================================================================
    //                                                             Override about Register
    //                                                             =======================
    protected void reflectRelationOnUnionQuery(ConditionQuery bq, ConditionQuery uq)
    { throw new IllegalConditionBeanOperationException("InlineView cannot use Union: " + bq + " : " + uq); }

    @Override
    protected void setupConditionValueAndRegisterWhereClause(ConditionKey k, Object v, ConditionValue cv, String col)
    { regIQ(k, v, cv, col); }

    @Override
    protected void setupConditionValueAndRegisterWhereClause(ConditionKey k, Object v, ConditionValue cv, String col, ConditionOption op)
    { regIQ(k, v, cv, col, op); }

    @Override
    protected void registerWhereClause(String wc)
    { registerInlineWhereClause(wc); }

    @Override
    protected boolean isInScopeRelationSuppressLocalAliasName() {
        if (_onClause) { throw new IllegalConditionBeanOperationException("InScopeRelation on OnClause is unsupported."); }
        return true;
    }

    // ===================================================================================
    //                                                                Override about Query
    //                                                                ====================
    protected ConditionValue getCValueProductId() { return _myCQ.getProductId(); }
    public String keepProductId_ExistsReferrer_PurchaseList(PurchaseCQ sq)
    { throwIICBOE("ExistsReferrer"); return null; }
    public String keepProductId_NotExistsReferrer_PurchaseList(PurchaseCQ sq)
    { throwIICBOE("NotExistsReferrer"); return null; }
    public String keepProductId_InScopeRelation_PurchaseList(PurchaseCQ sq)
    { return _myCQ.keepProductId_InScopeRelation_PurchaseList(sq); }
    public String keepProductId_NotInScopeRelation_PurchaseList(PurchaseCQ sq)
    { return _myCQ.keepProductId_NotInScopeRelation_PurchaseList(sq); }
    public String keepProductId_SpecifyDerivedReferrer_PurchaseList(PurchaseCQ sq)
    { throwIICBOE("(Specify)DerivedReferrer"); return null; }
    public String keepProductId_QueryDerivedReferrer_PurchaseList(PurchaseCQ sq)
    { throwIICBOE("(Query)DerivedReferrer"); return null; }
    public String keepProductId_QueryDerivedReferrer_PurchaseListParameter(Object vl)
    { throwIICBOE("(Query)DerivedReferrer"); return null; }
    protected ConditionValue getCValueProductName() { return _myCQ.getProductName(); }
    protected ConditionValue getCValueProductHandleCode() { return _myCQ.getProductHandleCode(); }
    protected ConditionValue getCValueProductStatusCode() { return _myCQ.getProductStatusCode(); }
    public String keepProductStatusCode_InScopeRelation_ProductStatus(ProductStatusCQ sq)
    { return _myCQ.keepProductStatusCode_InScopeRelation_ProductStatus(sq); }
    public String keepProductStatusCode_NotInScopeRelation_ProductStatus(ProductStatusCQ sq)
    { return _myCQ.keepProductStatusCode_NotInScopeRelation_ProductStatus(sq); }
    protected ConditionValue getCValueLatestPurchaseDatetime() { return _myCQ.getLatestPurchaseDatetime(); }
    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String pp) { return null; }
    public String keepScalarCondition(SummaryProductCQ sq)
    { throwIICBOE("ScalarCondition"); return null; }
    public String keepSpecifyMyselfDerived(SummaryProductCQ sq)
    { throwIICBOE("(Specify)MyselfDerived"); return null;}
    public String keepQueryMyselfDerived(SummaryProductCQ sq)
    { throwIICBOE("(Query)MyselfDerived"); return null;}
    public String keepQueryMyselfDerivedParameter(Object vl)
    { throwIICBOE("(Query)MyselfDerived"); return null;}
    public String keepMyselfExists(SummaryProductCQ sq)
    { throwIICBOE("MyselfExists"); return null;}
    public String keepMyselfInScope(SummaryProductCQ sq)
    { throwIICBOE("MyselfInScope"); return null;}

    protected void throwIICBOE(String name)
    { throw new IllegalConditionBeanOperationException(name + " at InlineView is unsupported."); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xinCB() { return SummaryProductCB.class.getName(); }
    protected String xinCQ() { return SummaryProductCQ.class.getName(); }
}
