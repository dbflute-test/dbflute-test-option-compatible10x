/*
 * Copyright 2014-2014 the original author or authors.
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
package org.docksidestage.compatible10x.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import org.dbflute.bhv.referrer.*;
import org.docksidestage.compatible10x.dbflute.exbhv.*;
import org.docksidestage.compatible10x.dbflute.exentity.*;
import org.docksidestage.compatible10x.dbflute.cbean.*;

/**
 * The referrer loader of (会員)MEMBER as TABLE. <br />
 * <pre>
 * [primary key]
 *     MEMBER_ID
 *
 * [column]
 *     MEMBER_ID, MEMBER_NAME, MEMBER_ACCOUNT, MEMBER_STATUS_CODE, FORMALIZED_DATETIME, BIRTHDATE, REGISTER_DATETIME, REGISTER_USER, UPDATE_DATETIME, UPDATE_USER, VERSION_NO
 *
 * [sequence]
 *     
 *
 * [identity]
 *     MEMBER_ID
 *
 * [version-no]
 *     VERSION_NO
 *
 * [foreign table]
 *     MEMBER_STATUS, MEMBER_ADDRESS(AsValid), MEMBER_LOGIN(AsLatest), MEMBER_SECURITY(AsOne), MEMBER_SERVICE(AsOne), MEMBER_WITHDRAWAL(AsOne)
 *
 * [referrer table]
 *     MEMBER_ADDRESS, MEMBER_FOLLOWING, MEMBER_LOGIN, PURCHASE, MEMBER_SECURITY, MEMBER_SERVICE, MEMBER_WITHDRAWAL
 *
 * [foreign property]
 *     memberStatus, memberAddressAsValid, memberLoginAsLatest, memberSecurityAsOne, memberServiceAsOne, memberWithdrawalAsOne
 *
 * [referrer property]
 *     memberAddressList, memberFollowingByMyMemberIdList, memberFollowingByYourMemberIdList, memberLoginList, purchaseList
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfMember {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<Member> _selectedList;
    protected BehaviorSelector _selector;
    protected MemberBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfMember ready(List<Member> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected MemberBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(MemberBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    protected List<MemberAddress> _referrerMemberAddressList;
    public NestedReferrerLoaderGateway<LoaderOfMemberAddress> loadMemberAddressList(ConditionBeanSetupper<MemberAddressCB> refCBLambda) {
        myBhv().loadMemberAddressList(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerMemberAddressList = refLs);
        return hd -> hd.handle(new LoaderOfMemberAddress().ready(_referrerMemberAddressList, _selector));
    }

    protected List<MemberFollowing> _referrerMemberFollowingByMyMemberIdList;
    public NestedReferrerLoaderGateway<LoaderOfMemberFollowing> loadMemberFollowingByMyMemberIdList(ConditionBeanSetupper<MemberFollowingCB> refCBLambda) {
        myBhv().loadMemberFollowingByMyMemberIdList(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerMemberFollowingByMyMemberIdList = refLs);
        return hd -> hd.handle(new LoaderOfMemberFollowing().ready(_referrerMemberFollowingByMyMemberIdList, _selector));
    }

    protected List<MemberFollowing> _referrerMemberFollowingByYourMemberIdList;
    public NestedReferrerLoaderGateway<LoaderOfMemberFollowing> loadMemberFollowingByYourMemberIdList(ConditionBeanSetupper<MemberFollowingCB> refCBLambda) {
        myBhv().loadMemberFollowingByYourMemberIdList(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerMemberFollowingByYourMemberIdList = refLs);
        return hd -> hd.handle(new LoaderOfMemberFollowing().ready(_referrerMemberFollowingByYourMemberIdList, _selector));
    }

    protected List<MemberLogin> _referrerMemberLoginList;
    public NestedReferrerLoaderGateway<LoaderOfMemberLogin> loadMemberLoginList(ConditionBeanSetupper<MemberLoginCB> refCBLambda) {
        myBhv().loadMemberLoginList(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerMemberLoginList = refLs);
        return hd -> hd.handle(new LoaderOfMemberLogin().ready(_referrerMemberLoginList, _selector));
    }

    protected List<Purchase> _referrerPurchaseList;
    public NestedReferrerLoaderGateway<LoaderOfPurchase> loadPurchaseList(ConditionBeanSetupper<PurchaseCB> refCBLambda) {
        myBhv().loadPurchaseList(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerPurchaseList = refLs);
        return hd -> hd.handle(new LoaderOfPurchase().ready(_referrerPurchaseList, _selector));
    }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    protected LoaderOfMemberStatus _foreignMemberStatusLoader;
    public LoaderOfMemberStatus pulloutMemberStatus() {
        if (_foreignMemberStatusLoader == null)
        { _foreignMemberStatusLoader = new LoaderOfMemberStatus().ready(myBhv().pulloutMemberStatus(_selectedList), _selector); }
        return _foreignMemberStatusLoader;
    }

    protected LoaderOfMemberAddress _foreignMemberAddressAsValidLoader;
    public LoaderOfMemberAddress pulloutMemberAddressAsValid() {
        if (_foreignMemberAddressAsValidLoader == null)
        { _foreignMemberAddressAsValidLoader = new LoaderOfMemberAddress().ready(myBhv().pulloutMemberAddressAsValid(_selectedList), _selector); }
        return _foreignMemberAddressAsValidLoader;
    }

    protected LoaderOfMemberLogin _foreignMemberLoginAsLatestLoader;
    public LoaderOfMemberLogin pulloutMemberLoginAsLatest() {
        if (_foreignMemberLoginAsLatestLoader == null)
        { _foreignMemberLoginAsLatestLoader = new LoaderOfMemberLogin().ready(myBhv().pulloutMemberLoginAsLatest(_selectedList), _selector); }
        return _foreignMemberLoginAsLatestLoader;
    }

    protected LoaderOfMemberSecurity _foreignMemberSecurityAsOneLoader;
    public LoaderOfMemberSecurity pulloutMemberSecurityAsOne() {
        if (_foreignMemberSecurityAsOneLoader == null)
        { _foreignMemberSecurityAsOneLoader = new LoaderOfMemberSecurity().ready(myBhv().pulloutMemberSecurityAsOne(_selectedList), _selector); }
        return _foreignMemberSecurityAsOneLoader;
    }

    protected LoaderOfMemberService _foreignMemberServiceAsOneLoader;
    public LoaderOfMemberService pulloutMemberServiceAsOne() {
        if (_foreignMemberServiceAsOneLoader == null)
        { _foreignMemberServiceAsOneLoader = new LoaderOfMemberService().ready(myBhv().pulloutMemberServiceAsOne(_selectedList), _selector); }
        return _foreignMemberServiceAsOneLoader;
    }

    protected LoaderOfMemberWithdrawal _foreignMemberWithdrawalAsOneLoader;
    public LoaderOfMemberWithdrawal pulloutMemberWithdrawalAsOne() {
        if (_foreignMemberWithdrawalAsOneLoader == null)
        { _foreignMemberWithdrawalAsOneLoader = new LoaderOfMemberWithdrawal().ready(myBhv().pulloutMemberWithdrawalAsOne(_selectedList), _selector); }
        return _foreignMemberWithdrawalAsOneLoader;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<Member> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
