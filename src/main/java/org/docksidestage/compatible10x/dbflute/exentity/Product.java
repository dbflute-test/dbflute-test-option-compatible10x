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
package org.docksidestage.compatible10x.dbflute.exentity;

import org.docksidestage.compatible10x.dbflute.bsentity.BsProduct;

/**
 * The entity of PRODUCT.
 * <p>
 * You can implement your original methods here.
 * This class remains when re-generating.
 * </p>
 * @author DBFlute(AutoGenerator)
 */
public class Product extends BsProduct {

    /** Serial version UID. (Default) */
    private static final long serialVersionUID = 1L;

    public static final String ALIAS_purchaseMemberCount = "PURCHASE_MEMBER_COUNT";
    public static final String ALIAS_purchaseCount = "PURCHASE_COUNT";

    private Integer _purchaseMemberCount;
    private Integer _purchaseCount;

    public Integer getPurchaseCount() {
        return _purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        _purchaseCount = purchaseCount;
    }

    public void setPurchaseMemberCount(Integer purchaseMemberCount) {
        _purchaseMemberCount = purchaseMemberCount;
    }

    public Integer getPurchaseMemberCount() {
        return _purchaseMemberCount;
    }
}
