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
package org.docksidestage.compatible10x.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.docksidestage.compatible10x.dbflute.allcommon.DBMetaInstanceHandler;
import org.docksidestage.compatible10x.dbflute.exentity.*;

/**
 * The entity of VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsVendorTheLongAndWindingTableAndColumnRef extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)} */
    protected Long _theLongAndWindingTableAndColumnRefId;

    /** THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN} */
    protected Long _theLongAndWindingTableAndColumnId;

    /** THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)} */
    protected java.util.Date _theLongAndWindingTableAndColumnRefDate;

    /** SHORT_DATE: {NotNull, DATE(10)} */
    protected java.util.Date _shortDate;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_theLongAndWindingTableAndColumnRefId == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN by my THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID, named 'vendorTheLongAndWindingTableAndColumn'. */
    protected VendorTheLongAndWindingTableAndColumn _vendorTheLongAndWindingTableAndColumn;

    /**
     * [get] VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN by my THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID, named 'vendorTheLongAndWindingTableAndColumn'. <br>
     * @return The entity of foreign property 'vendorTheLongAndWindingTableAndColumn'. (NullAllowed: when e.g. null FK column, no setupSelect)
     */
    public VendorTheLongAndWindingTableAndColumn getVendorTheLongAndWindingTableAndColumn() {
        return _vendorTheLongAndWindingTableAndColumn;
    }

    /**
     * [set] VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN by my THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID, named 'vendorTheLongAndWindingTableAndColumn'.
     * @param vendorTheLongAndWindingTableAndColumn The entity of foreign property 'vendorTheLongAndWindingTableAndColumn'. (NullAllowed)
     */
    public void setVendorTheLongAndWindingTableAndColumn(VendorTheLongAndWindingTableAndColumn vendorTheLongAndWindingTableAndColumn) {
        _vendorTheLongAndWindingTableAndColumn = vendorTheLongAndWindingTableAndColumn;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsVendorTheLongAndWindingTableAndColumnRef) {
            BsVendorTheLongAndWindingTableAndColumnRef other = (BsVendorTheLongAndWindingTableAndColumnRef)obj;
            if (!xSV(_theLongAndWindingTableAndColumnRefId, other._theLongAndWindingTableAndColumnRefId)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _theLongAndWindingTableAndColumnRefId);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_vendorTheLongAndWindingTableAndColumn != null)
        { sb.append(li).append(xbRDS(_vendorTheLongAndWindingTableAndColumn, "vendorTheLongAndWindingTableAndColumn")); }
        return sb.toString();
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_theLongAndWindingTableAndColumnRefId));
        sb.append(dm).append(xfND(_theLongAndWindingTableAndColumnId));
        sb.append(dm).append(xfUD(_theLongAndWindingTableAndColumnRefDate));
        sb.append(dm).append(xfUD(_shortDate));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_vendorTheLongAndWindingTableAndColumn != null)
        { sb.append(dm).append("vendorTheLongAndWindingTableAndColumn"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public VendorTheLongAndWindingTableAndColumnRef clone() {
        return (VendorTheLongAndWindingTableAndColumnRef)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)} <br>
     * @return The value of the column 'THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getTheLongAndWindingTableAndColumnRefId() {
        checkSpecifiedProperty("theLongAndWindingTableAndColumnRefId");
        return _theLongAndWindingTableAndColumnRefId;
    }

    /**
     * [set] THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID: {PK, NotNull, BIGINT(19)} <br>
     * @param theLongAndWindingTableAndColumnRefId The value of the column 'THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_ID'. (basically NotNull if update: for the constraint)
     */
    public void setTheLongAndWindingTableAndColumnRefId(Long theLongAndWindingTableAndColumnRefId) {
        registerModifiedProperty("theLongAndWindingTableAndColumnRefId");
        _theLongAndWindingTableAndColumnRefId = theLongAndWindingTableAndColumnRefId;
    }

    /**
     * [get] THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN} <br>
     * @return The value of the column 'THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID'. (basically NotNull if selected: for the constraint)
     */
    public Long getTheLongAndWindingTableAndColumnId() {
        checkSpecifiedProperty("theLongAndWindingTableAndColumnId");
        return _theLongAndWindingTableAndColumnId;
    }

    /**
     * [set] THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID: {IX, NotNull, BIGINT(19), FK to VENDOR_THE_LONG_AND_WINDING_TABLE_AND_COLUMN} <br>
     * @param theLongAndWindingTableAndColumnId The value of the column 'THE_LONG_AND_WINDING_TABLE_AND_COLUMN_ID'. (basically NotNull if update: for the constraint)
     */
    public void setTheLongAndWindingTableAndColumnId(Long theLongAndWindingTableAndColumnId) {
        registerModifiedProperty("theLongAndWindingTableAndColumnId");
        _theLongAndWindingTableAndColumnId = theLongAndWindingTableAndColumnId;
    }

    /**
     * [get] THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)} <br>
     * @return The value of the column 'THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE'. (basically NotNull if selected: for the constraint)
     */
    public java.util.Date getTheLongAndWindingTableAndColumnRefDate() {
        checkSpecifiedProperty("theLongAndWindingTableAndColumnRefDate");
        return _theLongAndWindingTableAndColumnRefDate;
    }

    /**
     * [set] THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE: {NotNull, DATE(10)} <br>
     * @param theLongAndWindingTableAndColumnRefDate The value of the column 'THE_LONG_AND_WINDING_TABLE_AND_COLUMN_REF_DATE'. (basically NotNull if update: for the constraint)
     */
    public void setTheLongAndWindingTableAndColumnRefDate(java.util.Date theLongAndWindingTableAndColumnRefDate) {
        registerModifiedProperty("theLongAndWindingTableAndColumnRefDate");
        _theLongAndWindingTableAndColumnRefDate = theLongAndWindingTableAndColumnRefDate;
    }

    /**
     * [get] SHORT_DATE: {NotNull, DATE(10)} <br>
     * @return The value of the column 'SHORT_DATE'. (basically NotNull if selected: for the constraint)
     */
    public java.util.Date getShortDate() {
        checkSpecifiedProperty("shortDate");
        return _shortDate;
    }

    /**
     * [set] SHORT_DATE: {NotNull, DATE(10)} <br>
     * @param shortDate The value of the column 'SHORT_DATE'. (basically NotNull if update: for the constraint)
     */
    public void setShortDate(java.util.Date shortDate) {
        registerModifiedProperty("shortDate");
        _shortDate = shortDate;
    }
}
