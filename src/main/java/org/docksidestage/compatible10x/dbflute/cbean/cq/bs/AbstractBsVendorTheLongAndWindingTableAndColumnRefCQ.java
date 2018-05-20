/*
 * Copyright 2014-2015 the original author or authors.
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
package org.docksidestage.compatible10x.dbflute.cbean.cq.bs;

import java.util.*;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.ckey.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.ordering.*;
import org.dbflute.cbean.scoping.*;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.dbmeta.DBMetaProvider;
import org.docksidestage.compatible10x.dbflute.allcommon.*;
import org.docksidestage.compatible10x.dbflute.cbean.*;
import org.docksidestage.compatible10x.dbflute.cbean.cq.*;

/**
 * The abstract condition-query of VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsVendorTheLongAndWindingTableAndColumnRefCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsVendorTheLongAndWindingTableAndColumnRefCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    public String asTableDbName() {
        return "VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefId The value of theLongAndWindingTableAndColumnRefId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_Equal(Long theLongAndWindingTableAndColumnRefId) {
        doSetTheLongAndWindingTableAndColumnRefId_Equal(theLongAndWindingTableAndColumnRefId);
    }

    protected void doSetTheLongAndWindingTableAndColumnRefId_Equal(Long theLongAndWindingTableAndColumnRefId) {
        regTheLongAndWindingTableAndColumnRefId(CK_EQ, theLongAndWindingTableAndColumnRefId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefId The value of theLongAndWindingTableAndColumnRefId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_NotEqual(Long theLongAndWindingTableAndColumnRefId) {
        doSetTheLongAndWindingTableAndColumnRefId_NotEqual(theLongAndWindingTableAndColumnRefId);
    }

    protected void doSetTheLongAndWindingTableAndColumnRefId_NotEqual(Long theLongAndWindingTableAndColumnRefId) {
        regTheLongAndWindingTableAndColumnRefId(CK_NES, theLongAndWindingTableAndColumnRefId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefId The value of theLongAndWindingTableAndColumnRefId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_GreaterThan(Long theLongAndWindingTableAndColumnRefId) {
        regTheLongAndWindingTableAndColumnRefId(CK_GT, theLongAndWindingTableAndColumnRefId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefId The value of theLongAndWindingTableAndColumnRefId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_LessThan(Long theLongAndWindingTableAndColumnRefId) {
        regTheLongAndWindingTableAndColumnRefId(CK_LT, theLongAndWindingTableAndColumnRefId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefId The value of theLongAndWindingTableAndColumnRefId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_GreaterEqual(Long theLongAndWindingTableAndColumnRefId) {
        regTheLongAndWindingTableAndColumnRefId(CK_GE, theLongAndWindingTableAndColumnRefId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefId The value of theLongAndWindingTableAndColumnRefId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_LessEqual(Long theLongAndWindingTableAndColumnRefId) {
        regTheLongAndWindingTableAndColumnRefId(CK_LE, theLongAndWindingTableAndColumnRefId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param minNumber The min number of theLongAndWindingTableAndColumnRefId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of theLongAndWindingTableAndColumnRefId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setTheLongAndWindingTableAndColumnRefId_RangeOf(Long minNumber, Long maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueTheLongAndWindingTableAndColumnRefId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefIdList The collection of theLongAndWindingTableAndColumnRefId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_InScope(Collection<Long> theLongAndWindingTableAndColumnRefIdList) {
        doSetTheLongAndWindingTableAndColumnRefId_InScope(theLongAndWindingTableAndColumnRefIdList);
    }

    protected void doSetTheLongAndWindingTableAndColumnRefId_InScope(Collection<Long> theLongAndWindingTableAndColumnRefIdList) {
        regINS(CK_INS, cTL(theLongAndWindingTableAndColumnRefIdList), xgetCValueTheLongAndWindingTableAndColumnRefId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     * @param theLongAndWindingTableAndColumnRefIdList The collection of theLongAndWindingTableAndColumnRefId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefId_NotInScope(Collection<Long> theLongAndWindingTableAndColumnRefIdList) {
        doSetTheLongAndWindingTableAndColumnRefId_NotInScope(theLongAndWindingTableAndColumnRefIdList);
    }

    protected void doSetTheLongAndWindingTableAndColumnRefId_NotInScope(Collection<Long> theLongAndWindingTableAndColumnRefIdList) {
        regINS(CK_NINS, cTL(theLongAndWindingTableAndColumnRefIdList), xgetCValueTheLongAndWindingTableAndColumnRefId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     */
    public void setTheLongAndWindingTableAndColumnRefId_IsNull() { regTheLongAndWindingTableAndColumnRefId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)}
     */
    public void setTheLongAndWindingTableAndColumnRefId_IsNotNull() { regTheLongAndWindingTableAndColumnRefId(CK_ISNN, DOBJ); }

    protected void regTheLongAndWindingTableAndColumnRefId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueTheLongAndWindingTableAndColumnRefId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID"); }
    protected abstract ConditionValue xgetCValueTheLongAndWindingTableAndColumnRefId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnId The value of theLongAndWindingTableAndColumnId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_Equal(Long theLongAndWindingTableAndColumnId) {
        doSetTheLongAndWindingTableAndColumnId_Equal(theLongAndWindingTableAndColumnId);
    }

    protected void doSetTheLongAndWindingTableAndColumnId_Equal(Long theLongAndWindingTableAndColumnId) {
        regTheLongAndWindingTableAndColumnId(CK_EQ, theLongAndWindingTableAndColumnId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnId The value of theLongAndWindingTableAndColumnId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_NotEqual(Long theLongAndWindingTableAndColumnId) {
        doSetTheLongAndWindingTableAndColumnId_NotEqual(theLongAndWindingTableAndColumnId);
    }

    protected void doSetTheLongAndWindingTableAndColumnId_NotEqual(Long theLongAndWindingTableAndColumnId) {
        regTheLongAndWindingTableAndColumnId(CK_NES, theLongAndWindingTableAndColumnId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnId The value of theLongAndWindingTableAndColumnId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_GreaterThan(Long theLongAndWindingTableAndColumnId) {
        regTheLongAndWindingTableAndColumnId(CK_GT, theLongAndWindingTableAndColumnId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnId The value of theLongAndWindingTableAndColumnId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_LessThan(Long theLongAndWindingTableAndColumnId) {
        regTheLongAndWindingTableAndColumnId(CK_LT, theLongAndWindingTableAndColumnId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnId The value of theLongAndWindingTableAndColumnId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_GreaterEqual(Long theLongAndWindingTableAndColumnId) {
        regTheLongAndWindingTableAndColumnId(CK_GE, theLongAndWindingTableAndColumnId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnId The value of theLongAndWindingTableAndColumnId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_LessEqual(Long theLongAndWindingTableAndColumnId) {
        regTheLongAndWindingTableAndColumnId(CK_LE, theLongAndWindingTableAndColumnId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param minNumber The min number of theLongAndWindingTableAndColumnId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of theLongAndWindingTableAndColumnId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setTheLongAndWindingTableAndColumnId_RangeOf(Long minNumber, Long maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueTheLongAndWindingTableAndColumnId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnIdList The collection of theLongAndWindingTableAndColumnId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_InScope(Collection<Long> theLongAndWindingTableAndColumnIdList) {
        doSetTheLongAndWindingTableAndColumnId_InScope(theLongAndWindingTableAndColumnIdList);
    }

    protected void doSetTheLongAndWindingTableAndColumnId_InScope(Collection<Long> theLongAndWindingTableAndColumnIdList) {
        regINS(CK_INS, cTL(theLongAndWindingTableAndColumnIdList), xgetCValueTheLongAndWindingTableAndColumnId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN}
     * @param theLongAndWindingTableAndColumnIdList The collection of theLongAndWindingTableAndColumnId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnId_NotInScope(Collection<Long> theLongAndWindingTableAndColumnIdList) {
        doSetTheLongAndWindingTableAndColumnId_NotInScope(theLongAndWindingTableAndColumnIdList);
    }

    protected void doSetTheLongAndWindingTableAndColumnId_NotInScope(Collection<Long> theLongAndWindingTableAndColumnIdList) {
        regINS(CK_NINS, cTL(theLongAndWindingTableAndColumnIdList), xgetCValueTheLongAndWindingTableAndColumnId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID");
    }

    /**
     * Set up InScopeRelation (sub-query). <br />
     * {in (select THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID from VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN where ...)} <br />
     * VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN by my THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID, named 'vendorTheLongAndWindingTableAndColumn'.
     * @param subCBLambda The callback for sub-query of VendorTheLongAndWindingTableAndColumn for 'in-scope'. (NotNull)
     */
    public void inScopeVendorTheLongAndWindingTableAndColumn(SubQuery<VendorTheLongAndWindingTableAndColumnCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        VendorTheLongAndWindingTableAndColumnCB cb = new VendorTheLongAndWindingTableAndColumnCB(); cb.xsetupForInScopeRelation(this);
        try { lock(); subCBLambda.query(cb); } finally { unlock(); }
        String pp = keepTheLongAndWindingTableAndColumnId_InScopeRelation_VendorTheLongAndWindingTableAndColumn(cb.query());
        registerInScopeRelation(cb.query(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID", "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID", pp, "vendorTheLongAndWindingTableAndColumn", false);
    }
    public abstract String keepTheLongAndWindingTableAndColumnId_InScopeRelation_VendorTheLongAndWindingTableAndColumn(VendorTheLongAndWindingTableAndColumnCQ sq);

    /**
     * Set up NotInScopeRelation (sub-query). <br />
     * {not in (select THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID from VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN where ...)} <br />
     * VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN by my THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID, named 'vendorTheLongAndWindingTableAndColumn'.
     * @param subCBLambda The callback for sub-query of VendorTheLongAndWindingTableAndColumn for 'not in-scope'. (NotNull)
     */
    public void notInScopeVendorTheLongAndWindingTableAndColumn(SubQuery<VendorTheLongAndWindingTableAndColumnCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        VendorTheLongAndWindingTableAndColumnCB cb = new VendorTheLongAndWindingTableAndColumnCB(); cb.xsetupForInScopeRelation(this);
        try { lock(); subCBLambda.query(cb); } finally { unlock(); }
        String pp = keepTheLongAndWindingTableAndColumnId_NotInScopeRelation_VendorTheLongAndWindingTableAndColumn(cb.query());
        registerInScopeRelation(cb.query(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID", "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID", pp, "vendorTheLongAndWindingTableAndColumn", true);
    }
    public abstract String keepTheLongAndWindingTableAndColumnId_NotInScopeRelation_VendorTheLongAndWindingTableAndColumn(VendorTheLongAndWindingTableAndColumnCQ sq);

    protected void regTheLongAndWindingTableAndColumnId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueTheLongAndWindingTableAndColumnId(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID"); }
    protected abstract ConditionValue xgetCValueTheLongAndWindingTableAndColumnId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * @param theLongAndWindingTableAndColumnRefDate The value of theLongAndWindingTableAndColumnRefDate as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_Equal(java.util.Date theLongAndWindingTableAndColumnRefDate) {
        regTheLongAndWindingTableAndColumnRefDate(CK_EQ,  fCTPD(theLongAndWindingTableAndColumnRefDate));
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * @param theLongAndWindingTableAndColumnRefDate The value of theLongAndWindingTableAndColumnRefDate as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_GreaterThan(java.util.Date theLongAndWindingTableAndColumnRefDate) {
        regTheLongAndWindingTableAndColumnRefDate(CK_GT,  fCTPD(theLongAndWindingTableAndColumnRefDate));
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * @param theLongAndWindingTableAndColumnRefDate The value of theLongAndWindingTableAndColumnRefDate as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_LessThan(java.util.Date theLongAndWindingTableAndColumnRefDate) {
        regTheLongAndWindingTableAndColumnRefDate(CK_LT,  fCTPD(theLongAndWindingTableAndColumnRefDate));
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * @param theLongAndWindingTableAndColumnRefDate The value of theLongAndWindingTableAndColumnRefDate as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_GreaterEqual(java.util.Date theLongAndWindingTableAndColumnRefDate) {
        regTheLongAndWindingTableAndColumnRefDate(CK_GE,  fCTPD(theLongAndWindingTableAndColumnRefDate));
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * @param theLongAndWindingTableAndColumnRefDate The value of theLongAndWindingTableAndColumnRefDate as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_LessEqual(java.util.Date theLongAndWindingTableAndColumnRefDate) {
        regTheLongAndWindingTableAndColumnRefDate(CK_LE, fCTPD(theLongAndWindingTableAndColumnRefDate));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * <pre>e.g. setTheLongAndWindingTableAndColumnRefDate_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of theLongAndWindingTableAndColumnRefDate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of theLongAndWindingTableAndColumnRefDate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_FromTo(Date fromDatetime, Date toDatetime, FromToOption fromToOption) {
        regFTQ(fCTPD(fromDatetime), fCTPD(toDatetime), xgetCValueTheLongAndWindingTableAndColumnRefDate(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE", fromToOption);
    }

    /**
     * DateFromTo. (Date means yyyy/MM/dd) {fromDate &lt;= column &lt; toDate + 1 day} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)}
     * <pre>
     * e.g. from:{2007/04/10 08:24:53} to:{2007/04/16 14:36:29}
     *  column &gt;= '2007/04/10 00:00:00' and column <span style="color: #CC4747">&lt; '2007/04/17 00:00:00'</span>
     * </pre>
     * @param fromDate The from-date(yyyy/MM/dd) of theLongAndWindingTableAndColumnRefDate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDate The to-date(yyyy/MM/dd) of theLongAndWindingTableAndColumnRefDate. (basically NotNull: if op.allowOneSide(), null allowed)
     */
    public void setTheLongAndWindingTableAndColumnRefDate_DateFromTo(Date fromDate, Date toDate) {
        setTheLongAndWindingTableAndColumnRefDate_FromTo(fromDate, toDate, xcDFTOP());
    }

    protected void regTheLongAndWindingTableAndColumnRefDate(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueTheLongAndWindingTableAndColumnRefDate(), "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE"); }
    protected abstract ConditionValue xgetCValueTheLongAndWindingTableAndColumnRefDate();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * @param shortDate The value of shortDate as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setShortDate_Equal(java.util.Date shortDate) {
        regShortDate(CK_EQ,  fCTPD(shortDate));
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * @param shortDate The value of shortDate as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setShortDate_GreaterThan(java.util.Date shortDate) {
        regShortDate(CK_GT,  fCTPD(shortDate));
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * @param shortDate The value of shortDate as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setShortDate_LessThan(java.util.Date shortDate) {
        regShortDate(CK_LT,  fCTPD(shortDate));
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * @param shortDate The value of shortDate as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setShortDate_GreaterEqual(java.util.Date shortDate) {
        regShortDate(CK_GE,  fCTPD(shortDate));
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * @param shortDate The value of shortDate as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setShortDate_LessEqual(java.util.Date shortDate) {
        regShortDate(CK_LE, fCTPD(shortDate));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * <pre>e.g. setShortDate_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of shortDate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of shortDate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    public void setShortDate_FromTo(Date fromDatetime, Date toDatetime, FromToOption fromToOption) {
        regFTQ(fCTPD(fromDatetime), fCTPD(toDatetime), xgetCValueShortDate(), "SHORT_DATE", fromToOption);
    }

    /**
     * DateFromTo. (Date means yyyy/MM/dd) {fromDate &lt;= column &lt; toDate + 1 day} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * SHORT_DATE: {NotNull, DATE(10)}
     * <pre>
     * e.g. from:{2007/04/10 08:24:53} to:{2007/04/16 14:36:29}
     *  column &gt;= '2007/04/10 00:00:00' and column <span style="color: #CC4747">&lt; '2007/04/17 00:00:00'</span>
     * </pre>
     * @param fromDate The from-date(yyyy/MM/dd) of shortDate. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDate The to-date(yyyy/MM/dd) of shortDate. (basically NotNull: if op.allowOneSide(), null allowed)
     */
    public void setShortDate_DateFromTo(Date fromDate, Date toDate) {
        setShortDate_FromTo(fromDate, toDate, xcDFTOP());
    }

    protected void regShortDate(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueShortDate(), "SHORT_DATE"); }
    protected abstract ConditionValue xgetCValueShortDate();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO = (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VendorTheLongAndWindingTableAndColumnRefCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, VendorTheLongAndWindingTableAndColumnRefCB.class);
    }

    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO &lt;&gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VendorTheLongAndWindingTableAndColumnRefCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, VendorTheLongAndWindingTableAndColumnRefCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br>
     * {where FOO &gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VendorTheLongAndWindingTableAndColumnRefCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, VendorTheLongAndWindingTableAndColumnRefCB.class);
    }

    /**
     * Prepare ScalarCondition as lessThan. <br>
     * {where FOO &lt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VendorTheLongAndWindingTableAndColumnRefCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, VendorTheLongAndWindingTableAndColumnRefCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br>
     * {where FOO &gt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VendorTheLongAndWindingTableAndColumnRefCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, VendorTheLongAndWindingTableAndColumnRefCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;VendorTheLongAndWindingTableAndColumnRefCB&gt;() {
     *     public void query(VendorTheLongAndWindingTableAndColumnRefCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VendorTheLongAndWindingTableAndColumnRefCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, VendorTheLongAndWindingTableAndColumnRefCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        VendorTheLongAndWindingTableAndColumnRefCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(VendorTheLongAndWindingTableAndColumnRefCQ sq);

    protected VendorTheLongAndWindingTableAndColumnRefCB xcreateScalarConditionCB() {
        VendorTheLongAndWindingTableAndColumnRefCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected VendorTheLongAndWindingTableAndColumnRefCB xcreateScalarConditionPartitionByCB() {
        VendorTheLongAndWindingTableAndColumnRefCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<VendorTheLongAndWindingTableAndColumnRefCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        VendorTheLongAndWindingTableAndColumnRefCB cb = new VendorTheLongAndWindingTableAndColumnRefCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(VendorTheLongAndWindingTableAndColumnRefCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<VendorTheLongAndWindingTableAndColumnRefCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(VendorTheLongAndWindingTableAndColumnRefCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        VendorTheLongAndWindingTableAndColumnRefCB cb = new VendorTheLongAndWindingTableAndColumnRefCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(VendorTheLongAndWindingTableAndColumnRefCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<VendorTheLongAndWindingTableAndColumnRefCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        VendorTheLongAndWindingTableAndColumnRefCB cb = new VendorTheLongAndWindingTableAndColumnRefCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(VendorTheLongAndWindingTableAndColumnRefCQ sq);

    // ===================================================================================
    //                                                                        Manual Order
    //                                                                        ============
    /**
     * Order along manual ordering information.
     * <pre>
     * ManualOrderOption mop = new ManualOrderOption();
     * mop.<span style="color: #CC4747">when_GreaterEqual</span>(priorityDate); <span style="color: #3F7E5E">// e.g. 2000/01/01</span>
     * cb.query().addOrderBy_Birthdate_Asc().<span style="color: #CC4747">withManualOrder(mop)</span>;
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when BIRTHDATE &gt;= '2000/01/01' then 0</span>
     * <span style="color: #3F7E5E">//     else 1</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     *
     * ManualOrderOption mop = new ManualOrderOption();
     * mop.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Withdrawal);
     * mop.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Formalized);
     * mop.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Provisional);
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder(mop)</span>;
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * <p>This function with Union is unsupported!</p>
     * <p>The order values are bound (treated as bind parameter).</p>
     * @param option The option of manual-order containing order values. (NotNull)
     */
    public void withManualOrder(ManualOrderOption option) { // is user public!
        xdoWithManualOrder(option);
    }

    // ===================================================================================
    //                                                                    Small Adjustment
    //                                                                    ================
    /**
     * Order along the list of manual values. #beforejava8 <br>
     * This function with Union is unsupported! <br>
     * The order values are bound (treated as bind parameter).
     * <pre>
     * MemberCB cb = new MemberCB();
     * List&lt;CDef.MemberStatus&gt; orderValueList = new ArrayList&lt;CDef.MemberStatus&gt;();
     * orderValueList.add(CDef.MemberStatus.Withdrawal);
     * orderValueList.add(CDef.MemberStatus.Formalized);
     * orderValueList.add(CDef.MemberStatus.Provisional);
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder(orderValueList)</span>;
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * @param orderValueList The list of order values for manual ordering. (NotNull)
     */
    public void withManualOrder(List<? extends Object> orderValueList) { // is user public!
        assertObjectNotNull("withManualOrder(orderValueList)", orderValueList);
        final ManualOrderOption option = new ManualOrderOption();
        option.acceptOrderValueList(orderValueList);
        withManualOrder(option);
    }

    @Override
    protected void filterFromToOption(String columnDbName, FromToOption option) {
        option.allowOneSide();
    }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    protected VendorTheLongAndWindingTableAndColumnRefCB newMyCB() {
        return new VendorTheLongAndWindingTableAndColumnRefCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return VendorTheLongAndWindingTableAndColumnRefCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
