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
package org.docksidestage.compatible10x.dbflute.bsbhv;

import java.util.List;

import org.dbflute.*;
import org.dbflute.bhv.*;
import org.dbflute.bhv.core.BehaviorCommandInvoker;
import org.dbflute.bhv.readable.*;
import org.dbflute.bhv.writable.*;
import org.dbflute.bhv.referrer.*;
import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.HpSLSFunction;
import org.dbflute.cbean.result.*;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.exception.*;
import org.dbflute.hook.CommonColumnAutoSetupper;
import org.dbflute.optional.OptionalEntity;
import org.dbflute.outsidesql.executor.*;
import org.docksidestage.compatible10x.dbflute.exbhv.*;
import org.docksidestage.compatible10x.dbflute.bsbhv.loader.*;
import org.docksidestage.compatible10x.dbflute.exentity.*;
import org.docksidestage.compatible10x.dbflute.bsentity.dbmeta.*;
import org.docksidestage.compatible10x.dbflute.cbean.*;

/**
 * The behavior of (会員)MEMBER as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsMemberBhv extends AbstractBehaviorWritable<Member, MemberCB> {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /*df:beginQueryPath*/
    /** The example for select using domain entity */
    public static final String PATH_selectDomainMember = "selectDomainMember";
    /** The example for selecting scalar value */
    public static final String PATH_selectLatestFormalizedDatetime = "selectLatestFormalizedDatetime";
    /** The example for scalar select */
    public static final String PATH_selectMemberName = "selectMemberName";
    /** The example for select using options */
    public static final String PATH_selectOptionMember = "selectOptionMember";
    /** Example for ManualPaging */
    public static final String PATH_selectPurchaseMaxPriceMember = "selectPurchaseMaxPriceMember";
    public static final String PATH_selectPurchaseSummaryMember = "selectPurchaseSummaryMember";
    /** Example for Simple Select */
    public static final String PATH_selectSimpleMember = "selectSimpleMember";
    /** Example for AutoPaging */
    public static final String PATH_selectUnpaidSummaryMember = "selectUnpaidSummaryMember";
    public static final String PATH_updateMemberChangedToWithdrawalForcedly = "updateMemberChangedToWithdrawalForcedly";
    public static final String PATH_subdirectory_selectSubDirectoryCheck = "subdirectory:selectSubDirectoryCheck";
    public static final String PATH_whitebox_cmentity_selectCommonColumnMember = "whitebox:cmentity:selectCommonColumnMember";
    public static final String PATH_whitebox_cmentity_selectForcedType = "whitebox:cmentity:selectForcedType";
    public static final String PATH_whitebox_cmentity_selectPrimaryKeyComment = "whitebox:cmentity:selectPrimaryKeyComment";
    public static final String PATH_whitebox_cmentity_selectSurpriseSimilarPKComment = "whitebox:cmentity:selectSurpriseSimilarPKComment";
    public static final String PATH_whitebox_pmbean_selectCompareDate = "whitebox:pmbean:selectCompareDate";
    public static final String PATH_whitebox_pmbean_selectMapLikeSearch = "whitebox:pmbean:selectMapLikeSearch";
    /** The test for parameter auto-detect */
    public static final String PATH_whitebox_pmbean_selectParameterAutoDetect = "whitebox:pmbean:selectParameterAutoDetect";
    /** The test for definition order of parameter bean */
    public static final String PATH_whitebox_pmbean_selectParameterDefOrder = "whitebox:pmbean:selectParameterDefOrder";
    /** The test for no auto-detect */
    public static final String PATH_whitebox_pmbean_selectParameterNonAutoDetect = "whitebox:pmbean:selectParameterNonAutoDetect";
    public static final String PATH_whitebox_pmbean_selectResolvedPackageName = "whitebox:pmbean:selectResolvedPackageName";
    public static final String PATH_whitebox_pmcomment_selectPmCommentCollection = "whitebox:pmcomment:selectPmCommentCollection";
    public static final String PATH_whitebox_pmcomment_selectPmCommentEmbedded = "whitebox:pmcomment:selectPmCommentEmbedded";
    public static final String PATH_whitebox_pmcomment_selectPmCommentHint = "whitebox:pmcomment:selectPmCommentHint";
    public static final String PATH_whitebox_pmcomment_selectPmCommentOrderByIf = "whitebox:pmcomment:selectPmCommentOrderByIf";
    public static final String PATH_whitebox_pmcomment_selectPmCommentPossible = "whitebox:pmcomment:selectPmCommentPossible";
    public static final String PATH_whitebox_wrongexample_selectBindVariableNotFoundProperty = "whitebox:wrongexample:selectBindVariableNotFoundProperty";
    public static final String PATH_whitebox_wrongexample_selectEndCommentNotFound = "whitebox:wrongexample:selectEndCommentNotFound";
    public static final String PATH_whitebox_wrongexample_selectIfCommentNotBooleanResult = "whitebox:wrongexample:selectIfCommentNotBooleanResult";
    public static final String PATH_whitebox_wrongexample_selectIfCommentWrongExpression = "whitebox:wrongexample:selectIfCommentWrongExpression";
    /*df:endQueryPath*/

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public MemberDbm asDBMeta() { return MemberDbm.getInstance(); }
    /** {@inheritDoc} */
    public String asTableDbName() { return "MEMBER"; }

    /** @return The instance of DBMeta as my table type. (NotNull) */
    public MemberDbm getMyDBMeta() { return MemberDbm.getInstance(); }

    // ===================================================================================
    //                                                                        New Instance
    //                                                                        ============
    /** {@inheritDoc} */
    public MemberCB newConditionBean() { return new MemberCB(); }

    /** @return The instance of new entity as my table type. (NotNull) */
    public Member newMyEntity() { return new Member(); }

    /** @return The instance of new condition-bean as my table type. (NotNull) */
    public MemberCB newMyConditionBean() { return new MemberCB(); }

    // ===================================================================================
    //                                                                        Count Select
    //                                                                        ============
    /**
     * Select the count of uniquely-selected records by the condition-bean. {IgnorePagingCondition, IgnoreSpecifyColumn}<br>
     * SpecifyColumn is ignored but you can use it only to remove text type column for union's distinct.
     * <pre>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().setFoo...(value);
     * <span style="color: #70226C">int</span> count = <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">selectCount</span>(cb);
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @return The count for the condition. (NotMinus)
     */
    public int selectCount(MemberCB cb) {
        return facadeSelectCount(cb);
    }

    // ===================================================================================
    //                                                                       Entity Select
    //                                                                       =============
    /**
     * Select the entity by the condition-bean. #beforejava8 <br>
     * <span style="color: #AD4747; font-size: 120%">The return might be null if no data, so you should have null check.</span> <br>
     * <span style="color: #AD4747; font-size: 120%">If the data always exists as your business rule, use selectEntityWithDeletedCheck().</span>
     * <pre>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().setFoo...(value);
     * Member member = <span style="color: #0000C0">memberBhv</span>.<span style="color: #DD4747">selectEntity</span>(cb);
     * <span style="color: #70226C">if</span> (member != <span style="color: #70226C">null</span>) { <span style="color: #3F7E5E">// null check</span>
     *     ... = member.get...();
     * } <span style="color: #70226C">else</span> {
     *     ...
     * }
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @return The entity selected by the condition. (NullAllowed: if no data, it returns null)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public Member selectEntity(MemberCB cb) {
        return facadeSelectEntity(cb);
    }

    protected Member facadeSelectEntity(MemberCB cb) {
        return doSelectEntity(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends Member> OptionalEntity<ENTITY> doSelectOptionalEntity(MemberCB cb, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectEntity(cb, tp), cb);
    }

    protected Entity doReadEntity(ConditionBean cb) { return facadeSelectEntity(downcast(cb)); }

    /**
     * Select the entity by the condition-bean with deleted check. <br>
     * <span style="color: #AD4747; font-size: 120%">If the data is always present as your business rule, this method is good.</span>
     * <pre>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().set...;
     * Member member = <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">selectEntityWithDeletedCheck</span>(cb);
     * ... = member.get...(); <span style="color: #3F7E5E">// the entity always be not null</span>
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @return The entity selected by the condition. (NotNull: if no data, throws exception)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public Member selectEntityWithDeletedCheck(MemberCB cb) {
        return facadeSelectEntityWithDeletedCheck(cb);
    }

    /**
     * Select the entity by the primary-key value.
     * @param memberId (会員ID): PK, ID, NotNull, INTEGER(10), FK to MEMBER_ADDRESS. (NotNull)
     * @return The entity selected by the PK. (NullAllowed: if no data, it returns null)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public Member selectByPKValue(Integer memberId) {
        return facadeSelectByPKValue(memberId);
    }

    protected Member facadeSelectByPKValue(Integer memberId) {
        return doSelectByPK(memberId, typeOfSelectedEntity());
    }

    protected <ENTITY extends Member> ENTITY doSelectByPK(Integer memberId, Class<? extends ENTITY> tp) {
        return doSelectEntity(xprepareCBAsPK(memberId), tp);
    }

    protected <ENTITY extends Member> OptionalEntity<ENTITY> doSelectOptionalByPK(Integer memberId, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectByPK(memberId, tp), memberId);
    }

    /**
     * Select the entity by the primary-key value with deleted check.
     * @param memberId (会員ID): PK, ID, NotNull, INTEGER(10), FK to MEMBER_ADDRESS. (NotNull)
     * @return The entity selected by the PK. (NotNull: if no data, throws exception)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public Member selectByPKValueWithDeletedCheck(Integer memberId) {
        return doSelectByPKWithDeletedCheck(memberId, typeOfSelectedEntity());
    }

    protected <ENTITY extends Member> ENTITY doSelectByPKWithDeletedCheck(Integer memberId, Class<ENTITY> tp) {
        return doSelectEntityWithDeletedCheck(xprepareCBAsPK(memberId), tp);
    }

    protected MemberCB xprepareCBAsPK(Integer memberId) {
        assertObjectNotNull("memberId", memberId);
        return newConditionBean().acceptPK(memberId);
    }

    /**
     * Select the entity by the unique-key value.
     * @param memberAccount (会員アカウント): UQ, NotNull, VARCHAR(50). (NotNull)
     * @return The optional entity selected by the unique key. (NotNull: if no data, empty entity)
     * @throws EntityAlreadyDeletedException When get(), required() of return value is called and the value is null, which means entity has already been deleted (not found).
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public OptionalEntity<Member> selectByUniqueOf(String memberAccount) {
        return facadeSelectByUniqueOf(memberAccount);
    }

    protected OptionalEntity<Member> facadeSelectByUniqueOf(String memberAccount) {
        return doSelectByUniqueOf(memberAccount, typeOfSelectedEntity());
    }

    protected <ENTITY extends Member> OptionalEntity<ENTITY> doSelectByUniqueOf(String memberAccount, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectEntity(xprepareCBAsUniqueOf(memberAccount), tp), memberAccount);
    }

    protected MemberCB xprepareCBAsUniqueOf(String memberAccount) {
        assertObjectNotNull("memberAccount", memberAccount);
        return newConditionBean().acceptUniqueOf(memberAccount);
    }

    // ===================================================================================
    //                                                                         List Select
    //                                                                         ===========
    /**
     * Select the list as result bean.
     * <pre>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().set...;
     * cb.query().addOrderBy...;
     * ListResultBean&lt;Member&gt; <span style="color: #553000">memberList</span> = <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">selectList</span>(cb);
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     ... = member.get...;
     * }
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @return The result bean of selected list. (NotNull: if no data, returns empty list)
     * @throws DangerousResultSizeException When the result size is over the specified safety size.
     */
    public ListResultBean<Member> selectList(MemberCB cb) {
        return facadeSelectList(cb);
    }

    // ===================================================================================
    //                                                                         Page Select
    //                                                                         ===========
    /**
     * Select the page as result bean. <br>
     * (both count-select and paging-select are executed)
     * <pre>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * cb.<span style="color: #CC4747">paging</span>(20, 3); <span style="color: #3F7E5E">// 20 records per a page and current page number is 3</span>
     * PagingResultBean&lt;Member&gt; <span style="color: #553000">page</span> = <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">selectPage</span>(cb);
     * <span style="color: #70226C">int</span> allRecordCount = <span style="color: #553000">page</span>.getAllRecordCount();
     * <span style="color: #70226C">int</span> allPageCount = <span style="color: #553000">page</span>.getAllPageCount();
     * <span style="color: #70226C">boolean</span> isExistPrePage = <span style="color: #553000">page</span>.isExistPrePage();
     * <span style="color: #70226C">boolean</span> isExistNextPage = <span style="color: #553000">page</span>.isExistNextPage();
     * ...
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">page</span>) {
     *     ... = member.get...();
     * }
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @return The result bean of selected page. (NotNull: if no data, returns bean as empty list)
     * @throws DangerousResultSizeException When the result size is over the specified safety size.
     */
    public PagingResultBean<Member> selectPage(MemberCB cb) {
        return facadeSelectPage(cb);
    }

    // ===================================================================================
    //                                                                       Cursor Select
    //                                                                       =============
    /**
     * Select the cursor by the condition-bean.
     * <pre>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().set...
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">selectCursor</span>(cb, <span style="color: #553000">member</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     ... = <span style="color: #553000">member</span>.getMemberName();
     * });
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @param entityRowHandler The handler of entity row of Member. (NotNull)
     */
    public void selectCursor(MemberCB cb, EntityRowHandler<Member> entityRowHandler) {
        facadeSelectCursor(cb, entityRowHandler);
    }

    // ===================================================================================
    //                                                                       Scalar Select
    //                                                                       =============
    /**
     * Select the scalar value derived by a function from uniquely-selected records. <br>
     * You should call a function method after this method called like as follows:
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">selectScalar</span>(Date.class).max(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">cb</span>.specify().<span style="color: #CC4747">column...</span>; <span style="color: #3F7E5E">// required for the function</span>
     *     <span style="color: #553000">cb</span>.query().set...
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar function object to specify function for scalar value. (NotNull)
     */
    public <RESULT> HpSLSFunction<MemberCB, RESULT> selectScalar(Class<RESULT> resultType) {
        return facadeScalarSelect(resultType);
    }

    /**
     * Select the scalar value derived by a function from uniquely-selected records. <br>
     * You should call a function method after this method called like as follows:
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">scalarSelect</span>(Date.class).max(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">cb</span>.specify().<span style="color: #CC4747">column...</span>; <span style="color: #3F7E5E">// required for the function</span>
     *     <span style="color: #553000">cb</span>.query().set...
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar function object to specify function for scalar value. (NotNull)
     */
    public <RESULT> HpSLSFunction<MemberCB, RESULT> scalarSelect(Class<RESULT> resultType) {
        return facadeScalarSelect(resultType);
    }

    // ===================================================================================
    //                                                                            Sequence
    //                                                                            ========
    @Override
    protected Number doReadNextVal() {
        String msg = "This table is NOT related to sequence: " + asTableDbName();
        throw new UnsupportedOperationException(msg);
    }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    /**
     * Load referrer for the list by the referrer loader.
     * <pre>
     * List&lt;Member&gt; <span style="color: #553000">memberList</span> = <span style="color: #0000C0">memberBhv</span>.selectList(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">cb</span>.query().set...
     * });
     * memberBhv.<span style="color: #CC4747">load</span>(<span style="color: #553000">memberList</span>, <span style="color: #553000">memberLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">memberLoader</span>.<span style="color: #CC4747">loadPurchase</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">purchaseCB</span>.setupSelect...
     *         <span style="color: #553000">purchaseCB</span>.query().set...
     *         <span style="color: #553000">purchaseCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can also load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(purchaseLoader -&gt; {</span>
     *     <span style="color: #3F7E5E">//    purchaseLoader.loadPurchasePayment(...);</span>
     *     <span style="color: #3F7E5E">//});</span>
     *
     *     <span style="color: #3F7E5E">// you can also pull out foreign table and load its referrer</span>
     *     <span style="color: #3F7E5E">// (setupSelect of the foreign table should be called)</span>
     *     <span style="color: #3F7E5E">//memberLoader.pulloutMemberStatus().loadMemberLogin(...)</span>
     * });
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     List&lt;Purchase&gt; purchaseList = member.<span style="color: #CC4747">getPurchaseList()</span>;
     *     <span style="color: #70226C">for</span> (Purchase purchase : purchaseList) {
     *         ...
     *     }
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has order by FK before callback.
     * @param memberList The entity list of member. (NotNull)
     * @param loaderLambda The callback to handle the referrer loader for actually loading referrer. (NotNull)
     */
    public void load(List<Member> memberList, ReferrerLoaderHandler<LoaderOfMember> loaderLambda) {
        xassLRArg(memberList, loaderLambda);
        loaderLambda.handle(new LoaderOfMember().ready(memberList, _behaviorSelector));
    }

    /**
     * Load referrer for the entity by the referrer loader.
     * <pre>
     * Member <span style="color: #553000">member</span> = <span style="color: #0000C0">memberBhv</span>.selectEntityWithDeletedCheck(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> <span style="color: #553000">cb</span>.acceptPK(1));
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">load</span>(<span style="color: #553000">member</span>, <span style="color: #553000">memberLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">memberLoader</span>.<span style="color: #CC4747">loadPurchase</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">purchaseCB</span>.setupSelect...
     *         <span style="color: #553000">purchaseCB</span>.query().set...
     *         <span style="color: #553000">purchaseCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can also load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(purchaseLoader -&gt; {</span>
     *     <span style="color: #3F7E5E">//    purchaseLoader.loadPurchasePayment(...);</span>
     *     <span style="color: #3F7E5E">//});</span>
     *
     *     <span style="color: #3F7E5E">// you can also pull out foreign table and load its referrer</span>
     *     <span style="color: #3F7E5E">// (setupSelect of the foreign table should be called)</span>
     *     <span style="color: #3F7E5E">//memberLoader.pulloutMemberStatus().loadMemberLogin(...)</span>
     * });
     * List&lt;Purchase&gt; purchaseList = <span style="color: #553000">member</span>.<span style="color: #CC4747">getPurchaseList()</span>;
     * <span style="color: #70226C">for</span> (Purchase purchase : purchaseList) {
     *     ...
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has order by FK before callback.
     * @param member The entity of member. (NotNull)
     * @param loaderLambda The callback to handle the referrer loader for actually loading referrer. (NotNull)
     */
    public void load(Member member, ReferrerLoaderHandler<LoaderOfMember> loaderLambda) {
        xassLRArg(member, loaderLambda);
        loaderLambda.handle(new LoaderOfMember().ready(xnewLRAryLs(member), _behaviorSelector));
    }

    /**
     * Load referrer of memberAddressList by the set-upper of referrer. <br>
     * (会員住所情報)MEMBER_ADDRESS by MEMBER_ID, named 'memberAddressList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberAddressList</span>(<span style="color: #553000">memberList</span>, <span style="color: #553000">addressCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">addressCB</span>.setupSelect...
     *     <span style="color: #553000">addressCB</span>.query().set...
     *     <span style="color: #553000">addressCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     ... = member.<span style="color: #CC4747">getMemberAddressList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMemberId_InScope(pkList);
     * cb.query().addOrderBy_MemberId_Asc();
     * </pre>
     * @param memberList The entity list of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberAddress> loadMemberAddressList(List<Member> memberList, ConditionBeanSetupper<MemberAddressCB> refCBLambda) {
        xassLRArg(memberList, refCBLambda);
        return doLoadMemberAddressList(memberList, new LoadReferrerOption<MemberAddressCB, MemberAddress>().xinit(refCBLambda));
    }

    /**
     * Load referrer of memberAddressList by the set-upper of referrer. <br>
     * (会員住所情報)MEMBER_ADDRESS by MEMBER_ID, named 'memberAddressList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberAddressList</span>(<span style="color: #553000">member</span>, <span style="color: #553000">addressCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">addressCB</span>.setupSelect...
     *     <span style="color: #553000">addressCB</span>.query().set...
     *     <span style="color: #553000">addressCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * ... = <span style="color: #553000">member</span>.<span style="color: #CC4747">getMemberAddressList()</span>;
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMemberId_InScope(pkList);
     * cb.query().addOrderBy_MemberId_Asc();
     * </pre>
     * @param member The entity of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberAddress> loadMemberAddressList(Member member, ConditionBeanSetupper<MemberAddressCB> refCBLambda) {
        xassLRArg(member, refCBLambda);
        return doLoadMemberAddressList(xnewLRLs(member), new LoadReferrerOption<MemberAddressCB, MemberAddress>().xinit(refCBLambda));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.} #beforejava8
     * @param member The entity of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberAddress> loadMemberAddressList(Member member, LoadReferrerOption<MemberAddressCB, MemberAddress> loadReferrerOption) {
        xassLRArg(member, loadReferrerOption);
        return loadMemberAddressList(xnewLRLs(member), loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean set-upper} #beforejava8
     * @param memberList The entity list of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    @SuppressWarnings("unchecked")
    public NestedReferrerListGateway<MemberAddress> loadMemberAddressList(List<Member> memberList, LoadReferrerOption<MemberAddressCB, MemberAddress> loadReferrerOption) {
        xassLRArg(memberList, loadReferrerOption);
        if (memberList.isEmpty()) { return (NestedReferrerListGateway<MemberAddress>)EMPTY_NREF_LGWAY; }
        return doLoadMemberAddressList(memberList, loadReferrerOption);
    }

    protected NestedReferrerListGateway<MemberAddress> doLoadMemberAddressList(List<Member> memberList, LoadReferrerOption<MemberAddressCB, MemberAddress> option) {
        return helpLoadReferrerInternally(memberList, option, "memberAddressList");
    }

    /**
     * Load referrer of memberFollowingByMyMemberIdList by the set-upper of referrer. <br>
     * (会員フォローイング)MEMBER_FOLLOWING by MY_MEMBER_ID, named 'memberFollowingByMyMemberIdList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberFollowingByMyMemberIdList</span>(<span style="color: #553000">memberList</span>, <span style="color: #553000">followingCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">followingCB</span>.setupSelect...
     *     <span style="color: #553000">followingCB</span>.query().set...
     *     <span style="color: #553000">followingCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     ... = member.<span style="color: #CC4747">getMemberFollowingByMyMemberIdList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMyMemberId_InScope(pkList);
     * cb.query().addOrderBy_MyMemberId_Asc();
     * </pre>
     * @param memberList The entity list of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByMyMemberIdList(List<Member> memberList, ConditionBeanSetupper<MemberFollowingCB> refCBLambda) {
        xassLRArg(memberList, refCBLambda);
        return doLoadMemberFollowingByMyMemberIdList(memberList, new LoadReferrerOption<MemberFollowingCB, MemberFollowing>().xinit(refCBLambda));
    }

    /**
     * Load referrer of memberFollowingByMyMemberIdList by the set-upper of referrer. <br>
     * (会員フォローイング)MEMBER_FOLLOWING by MY_MEMBER_ID, named 'memberFollowingByMyMemberIdList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberFollowingByMyMemberIdList</span>(<span style="color: #553000">member</span>, <span style="color: #553000">followingCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">followingCB</span>.setupSelect...
     *     <span style="color: #553000">followingCB</span>.query().set...
     *     <span style="color: #553000">followingCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * ... = <span style="color: #553000">member</span>.<span style="color: #CC4747">getMemberFollowingByMyMemberIdList()</span>;
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMyMemberId_InScope(pkList);
     * cb.query().addOrderBy_MyMemberId_Asc();
     * </pre>
     * @param member The entity of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByMyMemberIdList(Member member, ConditionBeanSetupper<MemberFollowingCB> refCBLambda) {
        xassLRArg(member, refCBLambda);
        return doLoadMemberFollowingByMyMemberIdList(xnewLRLs(member), new LoadReferrerOption<MemberFollowingCB, MemberFollowing>().xinit(refCBLambda));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.} #beforejava8
     * @param member The entity of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByMyMemberIdList(Member member, LoadReferrerOption<MemberFollowingCB, MemberFollowing> loadReferrerOption) {
        xassLRArg(member, loadReferrerOption);
        return loadMemberFollowingByMyMemberIdList(xnewLRLs(member), loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean set-upper} #beforejava8
     * @param memberList The entity list of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    @SuppressWarnings("unchecked")
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByMyMemberIdList(List<Member> memberList, LoadReferrerOption<MemberFollowingCB, MemberFollowing> loadReferrerOption) {
        xassLRArg(memberList, loadReferrerOption);
        if (memberList.isEmpty()) { return (NestedReferrerListGateway<MemberFollowing>)EMPTY_NREF_LGWAY; }
        return doLoadMemberFollowingByMyMemberIdList(memberList, loadReferrerOption);
    }

    protected NestedReferrerListGateway<MemberFollowing> doLoadMemberFollowingByMyMemberIdList(List<Member> memberList, LoadReferrerOption<MemberFollowingCB, MemberFollowing> option) {
        return helpLoadReferrerInternally(memberList, option, "memberFollowingByMyMemberIdList");
    }

    /**
     * Load referrer of memberFollowingByYourMemberIdList by the set-upper of referrer. <br>
     * (会員フォローイング)MEMBER_FOLLOWING by YOUR_MEMBER_ID, named 'memberFollowingByYourMemberIdList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberFollowingByYourMemberIdList</span>(<span style="color: #553000">memberList</span>, <span style="color: #553000">followingCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">followingCB</span>.setupSelect...
     *     <span style="color: #553000">followingCB</span>.query().set...
     *     <span style="color: #553000">followingCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     ... = member.<span style="color: #CC4747">getMemberFollowingByYourMemberIdList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setYourMemberId_InScope(pkList);
     * cb.query().addOrderBy_YourMemberId_Asc();
     * </pre>
     * @param memberList The entity list of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByYourMemberIdList(List<Member> memberList, ConditionBeanSetupper<MemberFollowingCB> refCBLambda) {
        xassLRArg(memberList, refCBLambda);
        return doLoadMemberFollowingByYourMemberIdList(memberList, new LoadReferrerOption<MemberFollowingCB, MemberFollowing>().xinit(refCBLambda));
    }

    /**
     * Load referrer of memberFollowingByYourMemberIdList by the set-upper of referrer. <br>
     * (会員フォローイング)MEMBER_FOLLOWING by YOUR_MEMBER_ID, named 'memberFollowingByYourMemberIdList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberFollowingByYourMemberIdList</span>(<span style="color: #553000">member</span>, <span style="color: #553000">followingCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">followingCB</span>.setupSelect...
     *     <span style="color: #553000">followingCB</span>.query().set...
     *     <span style="color: #553000">followingCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * ... = <span style="color: #553000">member</span>.<span style="color: #CC4747">getMemberFollowingByYourMemberIdList()</span>;
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setYourMemberId_InScope(pkList);
     * cb.query().addOrderBy_YourMemberId_Asc();
     * </pre>
     * @param member The entity of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByYourMemberIdList(Member member, ConditionBeanSetupper<MemberFollowingCB> refCBLambda) {
        xassLRArg(member, refCBLambda);
        return doLoadMemberFollowingByYourMemberIdList(xnewLRLs(member), new LoadReferrerOption<MemberFollowingCB, MemberFollowing>().xinit(refCBLambda));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.} #beforejava8
     * @param member The entity of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByYourMemberIdList(Member member, LoadReferrerOption<MemberFollowingCB, MemberFollowing> loadReferrerOption) {
        xassLRArg(member, loadReferrerOption);
        return loadMemberFollowingByYourMemberIdList(xnewLRLs(member), loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean set-upper} #beforejava8
     * @param memberList The entity list of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    @SuppressWarnings("unchecked")
    public NestedReferrerListGateway<MemberFollowing> loadMemberFollowingByYourMemberIdList(List<Member> memberList, LoadReferrerOption<MemberFollowingCB, MemberFollowing> loadReferrerOption) {
        xassLRArg(memberList, loadReferrerOption);
        if (memberList.isEmpty()) { return (NestedReferrerListGateway<MemberFollowing>)EMPTY_NREF_LGWAY; }
        return doLoadMemberFollowingByYourMemberIdList(memberList, loadReferrerOption);
    }

    protected NestedReferrerListGateway<MemberFollowing> doLoadMemberFollowingByYourMemberIdList(List<Member> memberList, LoadReferrerOption<MemberFollowingCB, MemberFollowing> option) {
        return helpLoadReferrerInternally(memberList, option, "memberFollowingByYourMemberIdList");
    }

    /**
     * Load referrer of memberLoginList by the set-upper of referrer. <br>
     * (会員ログイン)MEMBER_LOGIN by MEMBER_ID, named 'memberLoginList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberLoginList</span>(<span style="color: #553000">memberList</span>, <span style="color: #553000">loginCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">loginCB</span>.setupSelect...
     *     <span style="color: #553000">loginCB</span>.query().set...
     *     <span style="color: #553000">loginCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     ... = member.<span style="color: #CC4747">getMemberLoginList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMemberId_InScope(pkList);
     * cb.query().addOrderBy_MemberId_Asc();
     * </pre>
     * @param memberList The entity list of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberLogin> loadMemberLoginList(List<Member> memberList, ConditionBeanSetupper<MemberLoginCB> refCBLambda) {
        xassLRArg(memberList, refCBLambda);
        return doLoadMemberLoginList(memberList, new LoadReferrerOption<MemberLoginCB, MemberLogin>().xinit(refCBLambda));
    }

    /**
     * Load referrer of memberLoginList by the set-upper of referrer. <br>
     * (会員ログイン)MEMBER_LOGIN by MEMBER_ID, named 'memberLoginList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadMemberLoginList</span>(<span style="color: #553000">member</span>, <span style="color: #553000">loginCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">loginCB</span>.setupSelect...
     *     <span style="color: #553000">loginCB</span>.query().set...
     *     <span style="color: #553000">loginCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * ... = <span style="color: #553000">member</span>.<span style="color: #CC4747">getMemberLoginList()</span>;
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMemberId_InScope(pkList);
     * cb.query().addOrderBy_MemberId_Asc();
     * </pre>
     * @param member The entity of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberLogin> loadMemberLoginList(Member member, ConditionBeanSetupper<MemberLoginCB> refCBLambda) {
        xassLRArg(member, refCBLambda);
        return doLoadMemberLoginList(xnewLRLs(member), new LoadReferrerOption<MemberLoginCB, MemberLogin>().xinit(refCBLambda));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.} #beforejava8
     * @param member The entity of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberLogin> loadMemberLoginList(Member member, LoadReferrerOption<MemberLoginCB, MemberLogin> loadReferrerOption) {
        xassLRArg(member, loadReferrerOption);
        return loadMemberLoginList(xnewLRLs(member), loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean set-upper} #beforejava8
     * @param memberList The entity list of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    @SuppressWarnings("unchecked")
    public NestedReferrerListGateway<MemberLogin> loadMemberLoginList(List<Member> memberList, LoadReferrerOption<MemberLoginCB, MemberLogin> loadReferrerOption) {
        xassLRArg(memberList, loadReferrerOption);
        if (memberList.isEmpty()) { return (NestedReferrerListGateway<MemberLogin>)EMPTY_NREF_LGWAY; }
        return doLoadMemberLoginList(memberList, loadReferrerOption);
    }

    protected NestedReferrerListGateway<MemberLogin> doLoadMemberLoginList(List<Member> memberList, LoadReferrerOption<MemberLoginCB, MemberLogin> option) {
        return helpLoadReferrerInternally(memberList, option, "memberLoginList");
    }

    /**
     * Load referrer of purchaseList by the set-upper of referrer. <br>
     * (購入)PURCHASE by MEMBER_ID, named 'purchaseList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadPurchaseList</span>(<span style="color: #553000">memberList</span>, <span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.setupSelect...
     *     <span style="color: #553000">purchaseCB</span>.query().set...
     *     <span style="color: #553000">purchaseCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * <span style="color: #70226C">for</span> (Member member : <span style="color: #553000">memberList</span>) {
     *     ... = member.<span style="color: #CC4747">getPurchaseList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMemberId_InScope(pkList);
     * cb.query().addOrderBy_MemberId_Asc();
     * </pre>
     * @param memberList The entity list of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<Purchase> loadPurchaseList(List<Member> memberList, ConditionBeanSetupper<PurchaseCB> refCBLambda) {
        xassLRArg(memberList, refCBLambda);
        return doLoadPurchaseList(memberList, new LoadReferrerOption<PurchaseCB, Purchase>().xinit(refCBLambda));
    }

    /**
     * Load referrer of purchaseList by the set-upper of referrer. <br>
     * (購入)PURCHASE by MEMBER_ID, named 'purchaseList'.
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">loadPurchaseList</span>(<span style="color: #553000">member</span>, <span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.setupSelect...
     *     <span style="color: #553000">purchaseCB</span>.query().set...
     *     <span style="color: #553000">purchaseCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * ... = <span style="color: #553000">member</span>.<span style="color: #CC4747">getPurchaseList()</span>;
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setMemberId_InScope(pkList);
     * cb.query().addOrderBy_MemberId_Asc();
     * </pre>
     * @param member The entity of member. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<Purchase> loadPurchaseList(Member member, ConditionBeanSetupper<PurchaseCB> refCBLambda) {
        xassLRArg(member, refCBLambda);
        return doLoadPurchaseList(xnewLRLs(member), new LoadReferrerOption<PurchaseCB, Purchase>().xinit(refCBLambda));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.} #beforejava8
     * @param member The entity of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<Purchase> loadPurchaseList(Member member, LoadReferrerOption<PurchaseCB, Purchase> loadReferrerOption) {
        xassLRArg(member, loadReferrerOption);
        return loadPurchaseList(xnewLRLs(member), loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean set-upper} #beforejava8
     * @param memberList The entity list of member. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    @SuppressWarnings("unchecked")
    public NestedReferrerListGateway<Purchase> loadPurchaseList(List<Member> memberList, LoadReferrerOption<PurchaseCB, Purchase> loadReferrerOption) {
        xassLRArg(memberList, loadReferrerOption);
        if (memberList.isEmpty()) { return (NestedReferrerListGateway<Purchase>)EMPTY_NREF_LGWAY; }
        return doLoadPurchaseList(memberList, loadReferrerOption);
    }

    protected NestedReferrerListGateway<Purchase> doLoadPurchaseList(List<Member> memberList, LoadReferrerOption<PurchaseCB, Purchase> option) {
        return helpLoadReferrerInternally(memberList, option, "purchaseList");
    }

    // ===================================================================================
    //                                                                   Pull out Relation
    //                                                                   =================
    /**
     * Pull out the list of foreign table 'MemberStatus'.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of foreign table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<MemberStatus> pulloutMemberStatus(List<Member> memberList)
    { return helpPulloutInternally(memberList, "memberStatus"); }

    /**
     * Pull out the list of foreign table 'MemberAddress'.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of foreign table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<MemberAddress> pulloutMemberAddressAsValid(List<Member> memberList)
    { return helpPulloutInternally(memberList, "memberAddressAsValid"); }

    /**
     * Pull out the list of foreign table 'MemberLogin'.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of foreign table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<MemberLogin> pulloutMemberLoginAsLatest(List<Member> memberList)
    { return helpPulloutInternally(memberList, "memberLoginAsLatest"); }

    /**
     * Pull out the list of referrer-as-one table 'MemberSecurity'.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of referrer-as-one table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<MemberSecurity> pulloutMemberSecurityAsOne(List<Member> memberList)
    { return helpPulloutInternally(memberList, "memberSecurityAsOne"); }

    /**
     * Pull out the list of referrer-as-one table 'MemberService'.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of referrer-as-one table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<MemberService> pulloutMemberServiceAsOne(List<Member> memberList)
    { return helpPulloutInternally(memberList, "memberServiceAsOne"); }

    /**
     * Pull out the list of referrer-as-one table 'MemberWithdrawal'.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of referrer-as-one table. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<MemberWithdrawal> pulloutMemberWithdrawalAsOne(List<Member> memberList)
    { return helpPulloutInternally(memberList, "memberWithdrawalAsOne"); }

    // ===================================================================================
    //                                                                      Extract Column
    //                                                                      ==============
    /**
     * Extract the value list of (single) primary key memberId.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of the column value. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<Integer> extractMemberIdList(List<Member> memberList)
    { return helpExtractListInternally(memberList, "memberId"); }

    /**
     * Extract the value list of (single) unique key memberAccount.
     * @param memberList The list of member. (NotNull, EmptyAllowed)
     * @return The list of the column value. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<String> extractMemberAccountList(List<Member> memberList)
    { return helpExtractListInternally(memberList, "memberAccount"); }

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    /**
     * Insert the entity modified-only. (DefaultConstraintsEnabled)
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * member.setFoo...(value);
     * member.setBar...(value);
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//member.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//member.set...;</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">insert</span>(member);
     * ... = member.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * <p>While, when the entity is created by select, all columns are registered.</p>
     * @param member The entity of insert. (NotNull, PrimaryKeyNullAllowed: when auto-increment)
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insert(Member member) {
        doInsert(member, null);
    }

    /**
     * Update the entity modified-only. (ZeroUpdateException, ExclusiveControl) <br>
     * By PK as default, and also you can update by unique keys using entity's uniqueOf().
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * member.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//member.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//member.set...;</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * member.<span style="color: #CC4747">setVersionNo</span>(value);
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">update</span>(member);
     * </pre>
     * @param member The entity of update. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @throws EntityAlreadyUpdatedException When the entity has already been updated.
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void update(Member member) {
        doUpdate(member, null);
    }

    /**
     * Update the entity non-strictly modified-only. (ZeroUpdateException, NonExclusiveControl) <br>
     * By PK as default, and also you can update by unique keys using entity's uniqueOf().
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * member.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//member.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//member.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//member.setVersionNo(value);</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">updateNonstrict</span>(member);
     * </pre>
     * @param member The entity of update. (NotNull, PrimaryKeyNotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void updateNonstrict(Member member) {
        doUpdateNonstrict(member, null);
    }

    /**
     * Insert or update the entity modified-only. (DefaultConstraintsEnabled, ExclusiveControl) <br>
     * if (the entity has no PK) { insert() } else { update(), but no data, insert() } <br>
     * <p><span style="color: #994747; font-size: 120%">Also you can update by unique keys using entity's uniqueOf().</span></p>
     * @param member The entity of insert or update. (NotNull, ...depends on insert or update)
     * @throws EntityAlreadyUpdatedException When the entity has already been updated.
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insertOrUpdate(Member member) {
        doInsertOrUpdate(member, null, null);
    }

    /**
     * Insert or update the entity non-strictly modified-only. (DefaultConstraintsEnabled, NonExclusiveControl) <br>
     * if (the entity has no PK) { insert() } else { update(), but no data, insert() }
     * <p><span style="color: #994747; font-size: 120%">Also you can update by unique keys using entity's uniqueOf().</span></p>
     * @param member The entity of insert or update. (NotNull, ...depends on insert or update)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insertOrUpdateNonstrict(Member member) {
        doInsertOrUpdateNonstrict(member, null, null);
    }

    /**
     * Delete the entity. (ZeroUpdateException, ExclusiveControl) <br>
     * By PK as default, and also you can delete by unique keys using entity's uniqueOf().
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * member.<span style="color: #CC4747">setVersionNo</span>(value);
     * <span style="color: #70226C">try</span> {
     *     <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">delete</span>(member);
     * } <span style="color: #70226C">catch</span> (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param member The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @throws EntityAlreadyUpdatedException When the entity has already been updated.
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void delete(Member member) {
        doDelete(member, null);
    }

    /**
     * Delete the entity non-strictly. {ZeroUpdateException, NonExclusiveControl} <br>
     * By PK as default, and also you can delete by unique keys using entity's uniqueOf().
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//member.setVersionNo(value);</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">deleteNonstrict</span>(member);
     * </pre>
     * @param member The entity of delete. (NotNull, PrimaryKeyNotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrict(Member member) {
        doDeleteNonstrict(member, null);
    }

    /**
     * Delete the entity non-strictly ignoring deleted. {ZeroUpdateException, NonExclusiveControl}
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//member.setVersionNo(value);</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">deleteNonstrictIgnoreDeleted</span>(member);
     * <span style="color: #3F7E5E">// if the target entity doesn't exist, no exception</span>
     * </pre>
     * @param member The entity of delete. (NotNull, PrimaryKeyNotNull)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void deleteNonstrictIgnoreDeleted(Member member) {
        doDeleteNonstrictIgnoreDeleted(member, null);
    }

    protected void doDeleteNonstrictIgnoreDeleted(Member et, final DeleteOption<MemberCB> op) {
        assertObjectNotNull("member", et); prepareDeleteOption(op); helpDeleteNonstrictIgnoreDeletedInternally(et, op);
    }

    // ===================================================================================
    //                                                                        Batch Update
    //                                                                        ============
    /**
     * Batch-insert the entity list modified-only of same-set columns. (DefaultConstraintsEnabled) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement. <br>
     * <p><span style="color: #CC4747; font-size: 120%">The columns of least common multiple are registered like this:</span></p>
     * <pre>
     * <span style="color: #70226C">for</span> (... : ...) {
     *     Member member = <span style="color: #70226C">new</span> Member();
     *     member.setFooName("foo");
     *     <span style="color: #70226C">if</span> (...) {
     *         member.setFooPrice(123);
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are registered</span>
     *     <span style="color: #3F7E5E">// FOO_PRICE not-called in any entities are registered as null without default value</span>
     *     <span style="color: #3F7E5E">// columns not-called in all entities are registered as null or default value</span>
     *     memberList.add(member);
     * }
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">batchInsert</span>(memberList);
     * </pre>
     * <p>While, when the entities are created by select, all columns are registered.</p>
     * <p>And if the table has an identity, entities after the process don't have incremented values.
     * (When you use the (normal) insert(), you can get the incremented value from your entity)</p>
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNullAllowed: when auto-increment)
     * @return The array of inserted count. (NotNull, EmptyAllowed)
     */
    public int[] batchInsert(List<Member> memberList) {
        return doBatchInsert(memberList, null);
    }

    /**
     * Batch-update the entity list modified-only of same-set columns. (ExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement. <br>
     * <span style="color: #CC4747; font-size: 120%">You should specify same-set columns to all entities like this:</span>
     * <pre>
     * for (... : ...) {
     *     Member member = <span style="color: #70226C">new</span> Member();
     *     member.setFooName("foo");
     *     <span style="color: #70226C">if</span> (...) {
     *         member.setFooPrice(123);
     *     } <span style="color: #70226C">else</span> {
     *         member.setFooPrice(null); <span style="color: #3F7E5E">// updated as null</span>
     *         <span style="color: #3F7E5E">//member.setFooDate(...); // *not allowed, fragmented</span>
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are updated</span>
     *     <span style="color: #3F7E5E">// (others are not updated: their values are kept)</span>
     *     memberList.add(member);
     * }
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">batchUpdate</span>(memberList);
     * </pre>
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @throws BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(List<Member> memberList) {
        return doBatchUpdate(memberList, null);
    }

    /**
     * Batch-update the entity list specified-only. (ExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * <pre>
     * <span style="color: #3F7E5E">// e.g. update two columns only</span>
     * memberBhv.<span style="color: #CC4747">batchUpdate</span>(memberList, <span style="color: #553000">colCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #3F7E5E">// the two only updated</span>
     *     <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnFooStatusCode()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *     <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnBarDate()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     * });
     * <span style="color: #3F7E5E">// e.g. update every column in the table</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">batchUpdate</span>(memberList, <span style="color: #553000">colCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #3F7E5E">// all columns are updated</span>
     *     <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnEveryColumn()</span>; <span style="color: #3F7E5E">// no check of modified properties</span>
     * });
     * </pre>
     * <p>You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistic lock column because they are specified implicitly.</p>
     * <p>And you should specify columns that are modified in any entities (at least one entity).
     * But if you specify every column, it has no check.</p>
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param colCBLambda The callback for specification of update columns. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @throws BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchUpdate(List<Member> memberList, SpecifyQuery<MemberCB> colCBLambda) {
        return doBatchUpdate(memberList, createSpecifiedUpdateOption(colCBLambda));
    }

    /**
     * Batch-update the entity list non-strictly modified-only of same-set columns. (NonExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement. <br>
     * <span style="color: #CC4747; font-size: 140%">You should specify same-set columns to all entities like this:</span>
     * <pre>
     * <span style="color: #70226C">for</span> (... : ...) {
     *     Member member = <span style="color: #70226C">new</span> Member();
     *     member.setFooName("foo");
     *     <span style="color: #70226C">if</span> (...) {
     *         member.setFooPrice(123);
     *     } <span style="color: #70226C">else</span> {
     *         member.setFooPrice(null); <span style="color: #3F7E5E">// updated as null</span>
     *         <span style="color: #3F7E5E">//member.setFooDate(...); // *not allowed, fragmented</span>
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are updated</span>
     *     <span style="color: #3F7E5E">// (others are not updated: their values are kept)</span>
     *     memberList.add(member);
     * }
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">batchUpdate</span>(memberList);
     * </pre>
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchUpdateNonstrict(List<Member> memberList) {
        return doBatchUpdateNonstrict(memberList, null);
    }

    /**
     * Batch-update the entity list non-strictly specified-only. (NonExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * <pre>
     * <span style="color: #3F7E5E">// e.g. update two columns only</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">batchUpdateNonstrict</span>(memberList, <span style="color: #553000">colCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #3F7E5E">// the two only updated</span>
     *     <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnFooStatusCode()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *     <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnBarDate()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     * });
     * <span style="color: #3F7E5E">// e.g. update every column in the table</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">batchUpdateNonstrict</span>(memberList, <span style="color: #553000">colCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #3F7E5E">// all columns are updated</span>
     *     <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnEveryColumn()</span>; <span style="color: #3F7E5E">// no check of modified properties</span>
     * });
     * </pre>
     * <p>You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistic lock column because they are specified implicitly.</p>
     * <p>And you should specify columns that are modified in any entities (at least one entity).</p>
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param colCBLambda The callback for specification of update columns. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchUpdateNonstrict(List<Member> memberList, SpecifyQuery<MemberCB> colCBLambda) {
        return doBatchUpdateNonstrict(memberList, createSpecifiedUpdateOption(colCBLambda));
    }

    /**
     * Batch-delete the entity list. (ExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     * @throws BatchEntityAlreadyUpdatedException When the entity has already been updated. This exception extends EntityAlreadyUpdatedException.
     */
    public int[] batchDelete(List<Member> memberList) {
        return doBatchDelete(memberList, null);
    }

    /**
     * Batch-delete the entity list non-strictly. {NonExclusiveControl} <br>
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchDeleteNonstrict(List<Member> memberList) {
        return doBatchDeleteNonstrict(memberList, null);
    }

    // ===================================================================================
    //                                                                        Query Update
    //                                                                        ============
    /**
     * Insert the several entities by query (modified-only for fixed value).
     * <pre>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">queryInsert</span>(new QueryInsertSetupper&lt;Member, MemberCB&gt;() {
     *     public ConditionBean setup(Member entity, MemberCB intoCB) {
     *         FooCB cb = FooCB();
     *         cb.setupSelect_Bar();
     *
     *         <span style="color: #3F7E5E">// mapping</span>
     *         intoCB.specify().columnMyName().mappedFrom(cb.specify().columnFooName());
     *         intoCB.specify().columnMyCount().mappedFrom(cb.specify().columnFooCount());
     *         intoCB.specify().columnMyDate().mappedFrom(cb.specify().specifyBar().columnBarDate());
     *         entity.setMyFixedValue("foo"); <span style="color: #3F7E5E">// fixed value</span>
     *         <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     *         <span style="color: #3F7E5E">//entity.setRegisterUser(value);</span>
     *         <span style="color: #3F7E5E">//entity.set...;</span>
     *         <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     *         <span style="color: #3F7E5E">//entity.setVersionNo(value);</span>
     *
     *         return cb;
     *     }
     * });
     * </pre>
     * @param manyArgLambda The callback to set up query-insert. (NotNull)
     * @return The inserted count.
     */
    public int queryInsert(QueryInsertSetupper<Member, MemberCB> manyArgLambda) {
        return doQueryInsert(manyArgLambda, null);
    }

    /**
     * Update the several entities by query non-strictly modified-only. (NonExclusiveControl)
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//member.setPK...(value);</span>
     * member.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//member.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//member.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//member.setVersionNo(value);</span>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().setFoo...(value);
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">queryUpdate</span>(member, cb);
     * </pre>
     * @param member The entity that contains update values. (NotNull, PrimaryKeyNullAllowed)
     * @param cb The condition-bean of Member. (NotNull)
     * @return The updated count.
     * @throws NonQueryUpdateNotAllowedException When the query has no condition.
     */
    public int queryUpdate(Member member, MemberCB cb) {
        return doQueryUpdate(member, cb, null);
    }

    /**
     * Delete the several entities by query. (NonExclusiveControl)
     * <pre>
     * MemberCB cb = new MemberCB();
     * cb.query().setFoo...(value);
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">queryDelete</span>(member, cb);
     * </pre>
     * @param cb The condition-bean of Member. (NotNull)
     * @return The deleted count.
     * @throws NonQueryDeleteNotAllowedException When the query has no condition.
     */
    public int queryDelete(MemberCB cb) {
        return doQueryDelete(cb, null);
    }

    // ===================================================================================
    //                                                                      Varying Update
    //                                                                      ==============
    // -----------------------------------------------------
    //                                         Entity Update
    //                                         -------------
    /**
     * Insert the entity with varying requests. <br>
     * For example, disableCommonColumnAutoSetup(), disablePrimaryKeyIdentity(). <br>
     * Other specifications are same as insert(entity).
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * member.setFoo...(value);
     * member.setBar...(value);
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">varyingInsert</span>(member, <span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #3F7E5E">// you can insert by your values for common columns</span>
     *     <span style="color: #553000">op</span>.disableCommonColumnAutoSetup();
     * });
     * ... = member.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param member The entity of insert. (NotNull, PrimaryKeyNullAllowed: when auto-increment)
     * @param opLambda The callback for option of insert for varying requests. (NotNull)
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsert(Member member, WritableOptionCall<MemberCB, InsertOption<MemberCB>> opLambda) {
        doInsert(member, createInsertOption(opLambda));
    }

    /**
     * Update the entity with varying requests modified-only. (ZeroUpdateException, ExclusiveControl) <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br>
     * Other specifications are same as update(entity).
     * <pre>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * member.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * member.<span style="color: #CC4747">setVersionNo</span>(value);
     * <span style="color: #3F7E5E">// you can update by self calculation values</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">varyingUpdate</span>(member, <span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.self(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">cb</span>.specify().<span style="color: #CC4747">columnXxxCount()</span>;
     *     }).plus(1); <span style="color: #3F7E5E">// XXX_COUNT = XXX_COUNT + 1</span>
     * });
     * </pre>
     * @param member The entity of update. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @throws EntityAlreadyUpdatedException When the entity has already been updated.
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingUpdate(Member member, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> opLambda) {
        doUpdate(member, createUpdateOption(opLambda));
    }

    /**
     * Update the entity with varying requests non-strictly modified-only. (ZeroUpdateException, NonExclusiveControl) <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br>
     * Other specifications are same as updateNonstrict(entity).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * Member member = <span style="color: #70226C">new</span> Member();
     * member.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * member.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//member.setVersionNo(value);</span>
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">varyingUpdateNonstrict</span>(member, <span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.self(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">cb</span>.specify().<span style="color: #CC4747">columnXxxCount()</span>;
     *     }).plus(1); <span style="color: #3F7E5E">// XXX_COUNT = XXX_COUNT + 1</span>
     * });
     * </pre>
     * @param member The entity of update. (NotNull, PrimaryKeyNotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingUpdateNonstrict(Member member, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> opLambda) {
        doUpdateNonstrict(member, createUpdateOption(opLambda));
    }

    /**
     * Insert or update the entity with varying requests. (ExclusiveControl: when update) <br>
     * Other specifications are same as insertOrUpdate(entity).
     * @param member The entity of insert or update. (NotNull)
     * @param insertOpLambda The callback for option of insert for varying requests. (NotNull)
     * @param updateOpLambda The callback for option of update for varying requests. (NotNull)
     * @throws EntityAlreadyUpdatedException When the entity has already been updated.
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsertOrUpdate(Member member, WritableOptionCall<MemberCB, InsertOption<MemberCB>> insertOpLambda, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> updateOpLambda) {
        doInsertOrUpdate(member, createInsertOption(insertOpLambda), createUpdateOption(updateOpLambda));
    }

    /**
     * Insert or update the entity with varying requests non-strictly. (NonExclusiveControl: when update) <br>
     * Other specifications are same as insertOrUpdateNonstrict(entity).
     * @param member The entity of insert or update. (NotNull)
     * @param insertOpLambda The callback for option of insert for varying requests. (NotNull)
     * @param updateOpLambda The callback for option of update for varying requests. (NotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsertOrUpdateNonstrict(Member member, WritableOptionCall<MemberCB, InsertOption<MemberCB>> insertOpLambda, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> updateOpLambda) {
        doInsertOrUpdateNonstrict(member, createInsertOption(insertOpLambda), createUpdateOption(updateOpLambda));
    }

    /**
     * Delete the entity with varying requests. (ZeroUpdateException, ExclusiveControl) <br>
     * Now a valid option does not exist. <br>
     * Other specifications are same as delete(entity).
     * @param member The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @throws EntityAlreadyUpdatedException When the entity has already been updated.
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDelete(Member member, WritableOptionCall<MemberCB, DeleteOption<MemberCB>> opLambda) {
        doDelete(member, createDeleteOption(opLambda));
    }

    /**
     * Delete the entity with varying requests non-strictly. (ZeroUpdateException, NonExclusiveControl) <br>
     * Now a valid option does not exist. <br>
     * Other specifications are same as deleteNonstrict(entity).
     * @param member The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDeleteNonstrict(Member member, WritableOptionCall<MemberCB, DeleteOption<MemberCB>> opLambda) {
        doDeleteNonstrict(member, createDeleteOption(opLambda));
    }

    // -----------------------------------------------------
    //                                          Batch Update
    //                                          ------------
    /**
     * Batch-insert the list with varying requests. <br>
     * For example, disableCommonColumnAutoSetup()
     * , disablePrimaryKeyIdentity(), limitBatchInsertLogging(). <br>
     * Other specifications are same as batchInsert(entityList).
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of insert for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchInsert(List<Member> memberList, WritableOptionCall<MemberCB, InsertOption<MemberCB>> opLambda) {
        return doBatchInsert(memberList, createInsertOption(opLambda));
    }

    /**
     * Batch-update the list with varying requests. <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br>
     * Other specifications are same as batchUpdate(entityList).
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchUpdate(List<Member> memberList, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> opLambda) {
        return doBatchUpdate(memberList, createUpdateOption(opLambda));
    }

    /**
     * Batch-update the list with varying requests non-strictly. <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br>
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchUpdateNonstrict(List<Member> memberList, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> opLambda) {
        return doBatchUpdateNonstrict(memberList, createUpdateOption(opLambda));
    }

    /**
     * Batch-delete the list with varying requests. <br>
     * For example, limitBatchDeleteLogging(). <br>
     * Other specifications are same as batchDelete(entityList).
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchDelete(List<Member> memberList, WritableOptionCall<MemberCB, DeleteOption<MemberCB>> opLambda) {
        return doBatchDelete(memberList, createDeleteOption(opLambda));
    }

    /**
     * Batch-delete the list with varying requests non-strictly. <br>
     * For example, limitBatchDeleteLogging(). <br>
     * Other specifications are same as batchDeleteNonstrict(entityList).
     * @param memberList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchDeleteNonstrict(List<Member> memberList, WritableOptionCall<MemberCB, DeleteOption<MemberCB>> opLambda) {
        return doBatchDeleteNonstrict(memberList, createDeleteOption(opLambda));
    }

    // -----------------------------------------------------
    //                                          Query Update
    //                                          ------------
    /**
     * Insert the several entities by query with varying requests (modified-only for fixed value). <br>
     * For example, disableCommonColumnAutoSetup(), disablePrimaryKeyIdentity(). <br>
     * Other specifications are same as queryInsert(entity, setupper).
     * @param manyArgLambda The set-upper of query-insert. (NotNull)
     * @param opLambda The callback for option of insert for varying requests. (NotNull)
     * @return The inserted count.
     */
    public int varyingQueryInsert(QueryInsertSetupper<Member, MemberCB> manyArgLambda, WritableOptionCall<MemberCB, InsertOption<MemberCB>> opLambda) {
        return doQueryInsert(manyArgLambda, createInsertOption(opLambda));
    }

    /**
     * Update the several entities by query with varying requests non-strictly modified-only. {NonExclusiveControl} <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), allowNonQueryUpdate(). <br>
     * Other specifications are same as queryUpdate(entity, cb).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * Member member = <span style="color: #70226C">new</span> Member();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//member.setPK...(value);</span>
     * member.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//member.setVersionNo(value);</span>
     * MemberCB cb = <span style="color: #70226C">new</span> MemberCB();
     * cb.query().setFoo...(value);
     * <span style="color: #0000C0">memberBhv</span>.<span style="color: #CC4747">varyingQueryUpdate</span>(member, cb, <span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.self(<span style="color: #553000">colCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">colCB</span>.specify().<span style="color: #CC4747">columnFooCount()</span>;
     *     }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * });
     * </pre>
     * @param member The entity that contains update values. (NotNull) {PrimaryKeyNotRequired}
     * @param cb The condition-bean of Member. (NotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @return The updated count.
     * @throws NonQueryUpdateNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryUpdate(Member member, MemberCB cb, WritableOptionCall<MemberCB, UpdateOption<MemberCB>> opLambda) {
        return doQueryUpdate(member, cb, createUpdateOption(opLambda));
    }

    /**
     * Delete the several entities by query with varying requests non-strictly. <br>
     * For example, allowNonQueryDelete(). <br>
     * Other specifications are same as queryDelete(cb).
     * @param cb The condition-bean of Member. (NotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @return The deleted count.
     * @throws NonQueryDeleteNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryDelete(MemberCB cb, WritableOptionCall<MemberCB, DeleteOption<MemberCB>> opLambda) {
        return doQueryDelete(cb, createDeleteOption(opLambda));
    }

    // ===================================================================================
    //                                                                          OutsideSql
    //                                                                          ==========
    /**
     * Prepare the all facade executor of outside-SQL to execute it.
     * <pre>
     * <span style="color: #3F7E5E">// main style</span>
     * memberBhv.outideSql().selectEntity(pmb); <span style="color: #3F7E5E">// optional</span>
     * memberBhv.outideSql().selectList(pmb); <span style="color: #3F7E5E">// ListResultBean</span>
     * memberBhv.outideSql().selectPage(pmb); <span style="color: #3F7E5E">// PagingResultBean</span>
     * memberBhv.outideSql().selectPagedListOnly(pmb); <span style="color: #3F7E5E">// ListResultBean</span>
     * memberBhv.outideSql().selectCursor(pmb, handler); <span style="color: #3F7E5E">// (by handler)</span>
     * memberBhv.outideSql().execute(pmb); <span style="color: #3F7E5E">// int (updated count)</span>
     * memberBhv.outideSql().call(pmb); <span style="color: #3F7E5E">// void (pmb has OUT parameters)</span>
     *
     * <span style="color: #3F7E5E">// traditional style</span>
     * memberBhv.outideSql().traditionalStyle().selectEntity(path, pmb, entityType);
     * memberBhv.outideSql().traditionalStyle().selectList(path, pmb, entityType);
     * memberBhv.outideSql().traditionalStyle().selectPage(path, pmb, entityType);
     * memberBhv.outideSql().traditionalStyle().selectPagedListOnly(path, pmb, entityType);
     * memberBhv.outideSql().traditionalStyle().selectCursor(path, pmb, handler);
     * memberBhv.outideSql().traditionalStyle().execute(path, pmb);
     *
     * <span style="color: #3F7E5E">// options</span>
     * memberBhv.outideSql().removeBlockComment().selectList()
     * memberBhv.outideSql().removeLineComment().selectList()
     * memberBhv.outideSql().formatSql().selectList()
     * </pre>
     * <p>The invoker of behavior command should be not null when you call this method.</p>
     * @return The new-created all facade executor of outside-SQL. (NotNull)
     */
    public OutsideSqlBasicExecutor<MemberBhv> outsideSql() {
        OutsideSqlAllFacadeExecutor<MemberBhv> facadeExecutor = doOutsideSql();
        return facadeExecutor.xbasicExecutor(); // variable to resolve generic type
    }

    // ===================================================================================
    //                                                                Optimistic Lock Info
    //                                                                ====================
    @Override
    protected boolean hasVersionNoValue(Entity et) { return downcast(et).getVersionNo() != null; }

    // ===================================================================================
    //                                                                         Type Helper
    //                                                                         ===========
    protected Class<? extends Member> typeOfSelectedEntity() { return Member.class; }
    protected Class<Member> typeOfHandlingEntity() { return Member.class; }
    protected Class<MemberCB> typeOfHandlingConditionBean() { return MemberCB.class; }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    @org.springframework.beans.factory.annotation.Autowired
    @org.springframework.beans.factory.annotation.Qualifier("behaviorCommandInvoker")
    public void setBehaviorCommandInvoker(BehaviorCommandInvoker behaviorCommandInvoker) {
        super.setBehaviorCommandInvoker(behaviorCommandInvoker);
    }

    @Override
    @org.springframework.beans.factory.annotation.Autowired
    @org.springframework.beans.factory.annotation.Qualifier("behaviorSelector")
    public void setBehaviorSelector(BehaviorSelector behaviorSelector) {
        super.setBehaviorSelector(behaviorSelector);
    }

    @Override
    @org.springframework.beans.factory.annotation.Autowired
    @org.springframework.beans.factory.annotation.Qualifier("commonColumnAutoSetupper")
    public void setCommonColumnAutoSetupper(CommonColumnAutoSetupper commonColumnAutoSetupper) {
        super.setCommonColumnAutoSetupper(commonColumnAutoSetupper);
    }
}
