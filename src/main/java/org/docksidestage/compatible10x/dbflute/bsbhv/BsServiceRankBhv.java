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
package org.docksidestage.compatible10x.dbflute.bsbhv;

import java.util.List;

import org.dbflute.*;
import org.dbflute.bhv.*;
import org.dbflute.bhv.readable.*;
import org.dbflute.bhv.writable.*;
import org.dbflute.bhv.referrer.*;
import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.HpSLSFunction;
import org.dbflute.cbean.result.*;
import org.dbflute.cbean.scoping.SpecifyQuery;
import org.dbflute.exception.*;
import org.dbflute.optional.OptionalEntity;
import org.dbflute.outsidesql.executor.*;
import org.docksidestage.compatible10x.dbflute.exbhv.*;
import org.docksidestage.compatible10x.dbflute.bsbhv.loader.*;
import org.docksidestage.compatible10x.dbflute.exentity.*;
import org.docksidestage.compatible10x.dbflute.bsentity.dbmeta.*;
import org.docksidestage.compatible10x.dbflute.cbean.*;

/**
 * The behavior of (サービスランク)SERVICE_RANK as TABLE. <br>
 * <pre>
 * [primary key]
 *     SERVICE_RANK_CODE
 *
 * [column]
 *     SERVICE_RANK_CODE, SERVICE_RANK_NAME, SERVICE_POINT_INCIDENCE, NEW_ACCEPTABLE_FLG, DESCRIPTION, DISPLAY_ORDER
 *
 * [sequence]
 *     
 *
 * [identity]
 *     
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     
 *
 * [referrer table]
 *     MEMBER_SERVICE
 *
 * [foreign property]
 *     
 *
 * [referrer property]
 *     memberServiceList
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsServiceRankBhv extends AbstractBehaviorWritable<ServiceRank, ServiceRankCB> {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /*df:beginQueryPath*/
    /*df:endQueryPath*/

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public ServiceRankDbm asDBMeta() { return ServiceRankDbm.getInstance(); }
    /** {@inheritDoc} */
    public String asTableDbName() { return "SERVICE_RANK"; }

    /** @return The instance of DBMeta as my table type. (NotNull) */
    public ServiceRankDbm getMyDBMeta() { return ServiceRankDbm.getInstance(); }

    // ===================================================================================
    //                                                                        New Instance
    //                                                                        ============
    /** {@inheritDoc} */
    public ServiceRankCB newConditionBean() { return new ServiceRankCB(); }

    /** @return The instance of new entity as my table type. (NotNull) */
    public ServiceRank newMyEntity() { return new ServiceRank(); }

    /** @return The instance of new condition-bean as my table type. (NotNull) */
    public ServiceRankCB newMyConditionBean() { return new ServiceRankCB(); }

    // ===================================================================================
    //                                                                        Count Select
    //                                                                        ============
    /**
     * Select the count of uniquely-selected records by the condition-bean. {IgnorePagingCondition, IgnoreSpecifyColumn}<br>
     * SpecifyColumn is ignored but you can use it only to remove text type column for union's distinct.
     * <pre>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().setFoo...(value);
     * <span style="color: #70226C">int</span> count = <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">selectCount</span>(cb);
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The count for the condition. (NotMinus)
     */
    public int selectCount(ServiceRankCB cb) {
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
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().setFoo...(value);
     * ServiceRank serviceRank = <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #DD4747">selectEntity</span>(cb);
     * <span style="color: #70226C">if</span> (serviceRank != <span style="color: #70226C">null</span>) { <span style="color: #3F7E5E">// null check</span>
     *     ... = serviceRank.get...();
     * } <span style="color: #70226C">else</span> {
     *     ...
     * }
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The entity selected by the condition. (NullAllowed: if no data, it returns null)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public ServiceRank selectEntity(ServiceRankCB cb) {
        return facadeSelectEntity(cb);
    }

    protected ServiceRank facadeSelectEntity(ServiceRankCB cb) {
        return doSelectEntity(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends ServiceRank> OptionalEntity<ENTITY> doSelectOptionalEntity(ServiceRankCB cb, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectEntity(cb, tp), cb);
    }

    protected Entity doReadEntity(ConditionBean cb) { return facadeSelectEntity(downcast(cb)); }

    /**
     * Select the entity by the condition-bean with deleted check. <br>
     * <span style="color: #AD4747; font-size: 120%">If the data is always present as your business rule, this method is good.</span>
     * <pre>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().set...;
     * ServiceRank serviceRank = <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">selectEntityWithDeletedCheck</span>(cb);
     * ... = serviceRank.get...(); <span style="color: #3F7E5E">// the entity always be not null</span>
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The entity selected by the condition. (NotNull: if no data, throws exception)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public ServiceRank selectEntityWithDeletedCheck(ServiceRankCB cb) {
        return facadeSelectEntityWithDeletedCheck(cb);
    }

    /**
     * Select the entity by the primary-key value.
     * @param serviceRankCode (サービスランクコード): PK, NotNull, CHAR(3), classification=ServiceRank. (NotNull)
     * @return The entity selected by the PK. (NullAllowed: if no data, it returns null)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public ServiceRank selectByPKValue(String serviceRankCode) {
        return facadeSelectByPKValue(serviceRankCode);
    }

    protected ServiceRank facadeSelectByPKValue(String serviceRankCode) {
        return doSelectByPK(serviceRankCode, typeOfSelectedEntity());
    }

    protected <ENTITY extends ServiceRank> ENTITY doSelectByPK(String serviceRankCode, Class<? extends ENTITY> tp) {
        return doSelectEntity(xprepareCBAsPK(serviceRankCode), tp);
    }

    protected <ENTITY extends ServiceRank> OptionalEntity<ENTITY> doSelectOptionalByPK(String serviceRankCode, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectByPK(serviceRankCode, tp), serviceRankCode);
    }

    /**
     * Select the entity by the primary-key value with deleted check.
     * @param serviceRankCode (サービスランクコード): PK, NotNull, CHAR(3), classification=ServiceRank. (NotNull)
     * @return The entity selected by the PK. (NotNull: if no data, throws exception)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public ServiceRank selectByPKValueWithDeletedCheck(String serviceRankCode) {
        return doSelectByPKWithDeletedCheck(serviceRankCode, typeOfSelectedEntity());
    }

    protected <ENTITY extends ServiceRank> ENTITY doSelectByPKWithDeletedCheck(String serviceRankCode, Class<ENTITY> tp) {
        return doSelectEntityWithDeletedCheck(xprepareCBAsPK(serviceRankCode), tp);
    }

    protected ServiceRankCB xprepareCBAsPK(String serviceRankCode) {
        assertObjectNotNull("serviceRankCode", serviceRankCode);
        return newConditionBean().acceptPK(serviceRankCode);
    }

    /**
     * Select the entity by the unique-key value.
     * @param displayOrder (表示順): UQ, NotNull, INTEGER(10). (NotNull)
     * @return The optional entity selected by the unique key. (NotNull: if no data, empty entity)
     * @throws EntityAlreadyDeletedException When get(), required() of return value is called and the value is null, which means entity has already been deleted (not found).
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws SelectEntityConditionNotFoundException When the condition for selecting an entity is not found.
     */
    public OptionalEntity<ServiceRank> selectByUniqueOf(Integer displayOrder) {
        return facadeSelectByUniqueOf(displayOrder);
    }

    protected OptionalEntity<ServiceRank> facadeSelectByUniqueOf(Integer displayOrder) {
        return doSelectByUniqueOf(displayOrder, typeOfSelectedEntity());
    }

    protected <ENTITY extends ServiceRank> OptionalEntity<ENTITY> doSelectByUniqueOf(Integer displayOrder, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectEntity(xprepareCBAsUniqueOf(displayOrder), tp), displayOrder);
    }

    protected ServiceRankCB xprepareCBAsUniqueOf(Integer displayOrder) {
        assertObjectNotNull("displayOrder", displayOrder);
        return newConditionBean().acceptUniqueOf(displayOrder);
    }

    // ===================================================================================
    //                                                                         List Select
    //                                                                         ===========
    /**
     * Select the list as result bean.
     * <pre>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().set...;
     * cb.query().addOrderBy...;
     * ListResultBean&lt;ServiceRank&gt; <span style="color: #553000">serviceRankList</span> = <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">selectList</span>(cb);
     * <span style="color: #70226C">for</span> (ServiceRank serviceRank : <span style="color: #553000">serviceRankList</span>) {
     *     ... = serviceRank.get...;
     * }
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The result bean of selected list. (NotNull: if no data, returns empty list)
     * @throws DangerousResultSizeException When the result size is over the specified safety size.
     */
    public ListResultBean<ServiceRank> selectList(ServiceRankCB cb) {
        return facadeSelectList(cb);
    }

    // ===================================================================================
    //                                                                         Page Select
    //                                                                         ===========
    /**
     * Select the page as result bean. <br>
     * (both count-select and paging-select are executed)
     * <pre>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().setFoo...(value);
     * cb.query().addOrderBy_Bar...();
     * cb.<span style="color: #CC4747">paging</span>(20, 3); <span style="color: #3F7E5E">// 20 records per a page and current page number is 3</span>
     * PagingResultBean&lt;ServiceRank&gt; <span style="color: #553000">page</span> = <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">selectPage</span>(cb);
     * <span style="color: #70226C">int</span> allRecordCount = <span style="color: #553000">page</span>.getAllRecordCount();
     * <span style="color: #70226C">int</span> allPageCount = <span style="color: #553000">page</span>.getAllPageCount();
     * <span style="color: #70226C">boolean</span> isExistPrePage = <span style="color: #553000">page</span>.isExistPrePage();
     * <span style="color: #70226C">boolean</span> isExistNextPage = <span style="color: #553000">page</span>.isExistNextPage();
     * ...
     * <span style="color: #70226C">for</span> (ServiceRank serviceRank : <span style="color: #553000">page</span>) {
     *     ... = serviceRank.get...();
     * }
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The result bean of selected page. (NotNull: if no data, returns bean as empty list)
     * @throws DangerousResultSizeException When the result size is over the specified safety size.
     */
    public PagingResultBean<ServiceRank> selectPage(ServiceRankCB cb) {
        return facadeSelectPage(cb);
    }

    // ===================================================================================
    //                                                                       Cursor Select
    //                                                                       =============
    /**
     * Select the cursor by the condition-bean.
     * <pre>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().set...
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">selectCursor</span>(cb, <span style="color: #553000">member</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     ... = <span style="color: #553000">member</span>.getMemberName();
     * });
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @param entityRowHandler The handler of entity row of ServiceRank. (NotNull)
     */
    public void selectCursor(ServiceRankCB cb, EntityRowHandler<ServiceRank> entityRowHandler) {
        facadeSelectCursor(cb, entityRowHandler);
    }

    // ===================================================================================
    //                                                                       Scalar Select
    //                                                                       =============
    /**
     * Select the scalar value derived by a function from uniquely-selected records. <br>
     * You should call a function method after this method called like as follows:
     * <pre>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">selectScalar</span>(Date.class).max(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">cb</span>.specify().<span style="color: #CC4747">column...</span>; <span style="color: #3F7E5E">// required for the function</span>
     *     <span style="color: #553000">cb</span>.query().set...
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar function object to specify function for scalar value. (NotNull)
     */
    public <RESULT> HpSLSFunction<ServiceRankCB, RESULT> selectScalar(Class<RESULT> resultType) {
        return facadeScalarSelect(resultType);
    }

    /**
     * Select the scalar value derived by a function from uniquely-selected records. <br>
     * You should call a function method after this method called like as follows:
     * <pre>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">scalarSelect</span>(Date.class).max(<span style="color: #553000">cb</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">cb</span>.specify().<span style="color: #CC4747">column...</span>; <span style="color: #3F7E5E">// required for the function</span>
     *     <span style="color: #553000">cb</span>.query().set...
     * });
     * </pre>
     * @param <RESULT> The type of result.
     * @param resultType The type of result. (NotNull)
     * @return The scalar function object to specify function for scalar value. (NotNull)
     */
    public <RESULT> HpSLSFunction<ServiceRankCB, RESULT> scalarSelect(Class<RESULT> resultType) {
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
     * Load referrer for the list by the the referrer loader.
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
     * @param serviceRankList The entity list of serviceRank. (NotNull)
     * @param loaderLambda The callback to handle the referrer loader for actually loading referrer. (NotNull)
     */
    public void load(List<ServiceRank> serviceRankList, ReferrerLoaderHandler<LoaderOfServiceRank> loaderLambda) {
        xassLRArg(serviceRankList, loaderLambda);
        loaderLambda.handle(new LoaderOfServiceRank().ready(serviceRankList, _behaviorSelector));
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
     * @param serviceRank The entity of serviceRank. (NotNull)
     * @param loaderLambda The callback to handle the referrer loader for actually loading referrer. (NotNull)
     */
    public void load(ServiceRank serviceRank, ReferrerLoaderHandler<LoaderOfServiceRank> loaderLambda) {
        xassLRArg(serviceRank, loaderLambda);
        loaderLambda.handle(new LoaderOfServiceRank().ready(xnewLRAryLs(serviceRank), _behaviorSelector));
    }

    /**
     * Load referrer of memberServiceList by the set-upper of referrer. <br>
     * (会員サービス)MEMBER_SERVICE by SERVICE_RANK_CODE, named 'memberServiceList'.
     * <pre>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">loadMemberServiceList</span>(<span style="color: #553000">serviceRankList</span>, <span style="color: #553000">serviceCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">serviceCB</span>.setupSelect...
     *     <span style="color: #553000">serviceCB</span>.query().set...
     *     <span style="color: #553000">serviceCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * <span style="color: #70226C">for</span> (ServiceRank serviceRank : <span style="color: #553000">serviceRankList</span>) {
     *     ... = serviceRank.<span style="color: #CC4747">getMemberServiceList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setServiceRankCode_InScope(pkList);
     * cb.query().addOrderBy_ServiceRankCode_Asc();
     * </pre>
     * @param serviceRankList The entity list of serviceRank. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberService> loadMemberServiceList(List<ServiceRank> serviceRankList, ConditionBeanSetupper<MemberServiceCB> refCBLambda) {
        xassLRArg(serviceRankList, refCBLambda);
        return doLoadMemberServiceList(serviceRankList, new LoadReferrerOption<MemberServiceCB, MemberService>().xinit(refCBLambda));
    }

    /**
     * Load referrer of memberServiceList by the set-upper of referrer. <br>
     * (会員サービス)MEMBER_SERVICE by SERVICE_RANK_CODE, named 'memberServiceList'.
     * <pre>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">loadMemberServiceList</span>(<span style="color: #553000">serviceRank</span>, <span style="color: #553000">serviceCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">serviceCB</span>.setupSelect...
     *     <span style="color: #553000">serviceCB</span>.query().set...
     *     <span style="color: #553000">serviceCB</span>.query().addOrderBy...
     * }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     * <span style="color: #3F7E5E">//}).withNestedReferrer(referrerList -&gt; {</span>
     * <span style="color: #3F7E5E">//    ...</span>
     * <span style="color: #3F7E5E">//});</span>
     * ... = <span style="color: #553000">serviceRank</span>.<span style="color: #CC4747">getMemberServiceList()</span>;
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setServiceRankCode_InScope(pkList);
     * cb.query().addOrderBy_ServiceRankCode_Asc();
     * </pre>
     * @param serviceRank The entity of serviceRank. (NotNull)
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberService> loadMemberServiceList(ServiceRank serviceRank, ConditionBeanSetupper<MemberServiceCB> refCBLambda) {
        xassLRArg(serviceRank, refCBLambda);
        return doLoadMemberServiceList(xnewLRLs(serviceRank), new LoadReferrerOption<MemberServiceCB, MemberService>().xinit(refCBLambda));
    }

    /**
     * {Refer to overload method that has an argument of the list of entity.} #beforejava8
     * @param serviceRank The entity of serviceRank. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerListGateway<MemberService> loadMemberServiceList(ServiceRank serviceRank, LoadReferrerOption<MemberServiceCB, MemberService> loadReferrerOption) {
        xassLRArg(serviceRank, loadReferrerOption);
        return loadMemberServiceList(xnewLRLs(serviceRank), loadReferrerOption);
    }

    /**
     * {Refer to overload method that has an argument of condition-bean set-upper} #beforejava8
     * @param serviceRankList The entity list of serviceRank. (NotNull)
     * @param loadReferrerOption The option of load-referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    @SuppressWarnings("unchecked")
    public NestedReferrerListGateway<MemberService> loadMemberServiceList(List<ServiceRank> serviceRankList, LoadReferrerOption<MemberServiceCB, MemberService> loadReferrerOption) {
        xassLRArg(serviceRankList, loadReferrerOption);
        if (serviceRankList.isEmpty()) { return (NestedReferrerListGateway<MemberService>)EMPTY_NREF_LGWAY; }
        return doLoadMemberServiceList(serviceRankList, loadReferrerOption);
    }

    protected NestedReferrerListGateway<MemberService> doLoadMemberServiceList(List<ServiceRank> serviceRankList, LoadReferrerOption<MemberServiceCB, MemberService> option) {
        return helpLoadReferrerInternally(serviceRankList, option, "memberServiceList");
    }

    // ===================================================================================
    //                                                                   Pull out Relation
    //                                                                   =================
    // ===================================================================================
    //                                                                      Extract Column
    //                                                                      ==============
    /**
     * Extract the value list of (single) primary key serviceRankCode.
     * @param serviceRankList The list of serviceRank. (NotNull, EmptyAllowed)
     * @return The list of the column value. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<String> extractServiceRankCodeList(List<ServiceRank> serviceRankList)
    { return helpExtractListInternally(serviceRankList, "serviceRankCode"); }

    /**
     * Extract the value list of (single) unique key displayOrder.
     * @param serviceRankList The list of serviceRank. (NotNull, EmptyAllowed)
     * @return The list of the column value. (NotNull, EmptyAllowed, NotNullElement)
     */
    public List<Integer> extractDisplayOrderList(List<ServiceRank> serviceRankList)
    { return helpExtractListInternally(serviceRankList, "displayOrder"); }

    // ===================================================================================
    //                                                                       Entity Update
    //                                                                       =============
    /**
     * Insert the entity modified-only. (DefaultConstraintsEnabled)
     * <pre>
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * serviceRank.setFoo...(value);
     * serviceRank.setBar...(value);
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//serviceRank.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//serviceRank.set...;</span>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">insert</span>(serviceRank);
     * ... = serviceRank.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * <p>While, when the entity is created by select, all columns are registered.</p>
     * @param serviceRank The entity of insert. (NotNull, PrimaryKeyNullAllowed: when auto-increment)
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insert(ServiceRank serviceRank) {
        doInsert(serviceRank, null);
    }

    /**
     * Update the entity modified-only. (ZeroUpdateException, NonExclusiveControl)
     * <pre>
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * serviceRank.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * serviceRank.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//serviceRank.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//serviceRank.set...;</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * serviceRank.<span style="color: #CC4747">setVersionNo</span>(value);
     * try {
     *     <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">update</span>(serviceRank);
     * } catch (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param serviceRank The entity of update. (NotNull, PrimaryKeyNotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void update(ServiceRank serviceRank) {
        doUpdate(serviceRank, null);
    }

    /**
     * Insert or update the entity modified-only. (DefaultConstraintsEnabled, NonExclusiveControl) <br>
     * if (the entity has no PK) { insert() } else { update(), but no data, insert() } <br>
     * <p><span style="color: #CC4747; font-size: 120%">Attention, you cannot update by unique keys instead of PK.</span></p>
     * @param serviceRank The entity of insert or update. (NotNull, ...depends on insert or update)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void insertOrUpdate(ServiceRank serviceRank) {
        doInsertOrUpdate(serviceRank, null, null);
    }

    /**
     * Delete the entity. (ZeroUpdateException, NonExclusiveControl)
     * <pre>
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * serviceRank.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * serviceRank.<span style="color: #CC4747">setVersionNo</span>(value);
     * <span style="color: #70226C">try</span> {
     *     <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">delete</span>(serviceRank);
     * } <span style="color: #70226C">catch</span> (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param serviceRank The entity of delete. (NotNull, PrimaryKeyNotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void delete(ServiceRank serviceRank) {
        doDelete(serviceRank, null);
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
     *     ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     *     serviceRank.setFooName("foo");
     *     <span style="color: #70226C">if</span> (...) {
     *         serviceRank.setFooPrice(123);
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are registered</span>
     *     <span style="color: #3F7E5E">// FOO_PRICE not-called in any entities are registered as null without default value</span>
     *     <span style="color: #3F7E5E">// columns not-called in all entities are registered as null or default value</span>
     *     serviceRankList.add(serviceRank);
     * }
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">batchInsert</span>(serviceRankList);
     * </pre>
     * <p>While, when the entities are created by select, all columns are registered.</p>
     * <p>And if the table has an identity, entities after the process don't have incremented values.
     * (When you use the (normal) insert(), you can get the incremented value from your entity)</p>
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNullAllowed: when auto-increment)
     * @return The array of inserted count. (NotNull, EmptyAllowed)
     */
    public int[] batchInsert(List<ServiceRank> serviceRankList) {
        return doBatchInsert(serviceRankList, null);
    }

    /**
     * Batch-update the entity list modified-only of same-set columns. (NonExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement. <br>
     * <span style="color: #CC4747; font-size: 120%">You should specify same-set columns to all entities like this:</span>
     * <pre>
     * for (... : ...) {
     *     ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     *     serviceRank.setFooName("foo");
     *     <span style="color: #70226C">if</span> (...) {
     *         serviceRank.setFooPrice(123);
     *     } <span style="color: #70226C">else</span> {
     *         serviceRank.setFooPrice(null); <span style="color: #3F7E5E">// updated as null</span>
     *         <span style="color: #3F7E5E">//serviceRank.setFooDate(...); // *not allowed, fragmented</span>
     *     }
     *     <span style="color: #3F7E5E">// FOO_NAME and FOO_PRICE (and record meta columns) are updated</span>
     *     <span style="color: #3F7E5E">// (others are not updated: their values are kept)</span>
     *     serviceRankList.add(serviceRank);
     * }
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">batchUpdate</span>(serviceRankList);
     * </pre>
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchUpdate(List<ServiceRank> serviceRankList) {
        return doBatchUpdate(serviceRankList, null);
    }

    /**
     * Batch-update the entity list specified-only. (NonExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * <pre>
     * <span style="color: #3F7E5E">// e.g. update two columns only</span>
     * serviceRankBhv.<span style="color: #CC4747">batchUpdate</span>(serviceRankList, new SpecifyQuery&lt;ServiceRankCB&gt;() {
     *     public void specify(ServiceRankCB cb) { <span style="color: #3F7E5E">// the two only updated</span>
     *         cb.specify().<span style="color: #CC4747">columnFooStatusCode()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *         cb.specify().<span style="color: #CC4747">columnBarDate()</span>; <span style="color: #3F7E5E">// should be modified in any entities</span>
     *     }
     * });
     * <span style="color: #3F7E5E">// e.g. update every column in the table</span>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">batchUpdate</span>(serviceRankList, new SpecifyQuery&lt;ServiceRankCB&gt;() {
     *     public void specify(ServiceRankCB cb) { <span style="color: #3F7E5E">// all columns are updated</span>
     *         cb.specify().<span style="color: #CC4747">columnEveryColumn()</span>; <span style="color: #3F7E5E">// no check of modified properties</span>
     *     }
     * });
     * </pre>
     * <p>You can specify update columns used on set clause of update statement.
     * However you do not need to specify common columns for update
     * and an optimistic lock column because they are specified implicitly.</p>
     * <p>And you should specify columns that are modified in any entities (at least one entity).
     * But if you specify every column, it has no check.</p>
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param colCBLambda The callback for specification of update columns. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchUpdate(List<ServiceRank> serviceRankList, SpecifyQuery<ServiceRankCB> colCBLambda) {
        return doBatchUpdate(serviceRankList, createSpecifiedUpdateOption(colCBLambda));
    }

    /**
     * Batch-delete the entity list. (NonExclusiveControl) <br>
     * This method uses executeBatch() of java.sql.PreparedStatement.
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     */
    public int[] batchDelete(List<ServiceRank> serviceRankList) {
        return doBatchDelete(serviceRankList, null);
    }

    // ===================================================================================
    //                                                                        Query Update
    //                                                                        ============
    /**
     * Insert the several entities by query (modified-only for fixed value).
     * <pre>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">queryInsert</span>(new QueryInsertSetupper&lt;ServiceRank, ServiceRankCB&gt;() {
     *     public ConditionBean setup(ServiceRank entity, ServiceRankCB intoCB) {
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
    public int queryInsert(QueryInsertSetupper<ServiceRank, ServiceRankCB> manyArgLambda) {
        return doQueryInsert(manyArgLambda, null);
    }

    /**
     * Update the several entities by query non-strictly modified-only. (NonExclusiveControl)
     * <pre>
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//serviceRank.setPK...(value);</span>
     * serviceRank.setFoo...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set values of common columns</span>
     * <span style="color: #3F7E5E">//serviceRank.setRegisterUser(value);</span>
     * <span style="color: #3F7E5E">//serviceRank.set...;</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//serviceRank.setVersionNo(value);</span>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().setFoo...(value);
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">queryUpdate</span>(serviceRank, cb);
     * </pre>
     * @param serviceRank The entity that contains update values. (NotNull, PrimaryKeyNullAllowed)
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The updated count.
     * @throws NonQueryUpdateNotAllowedException When the query has no condition.
     */
    public int queryUpdate(ServiceRank serviceRank, ServiceRankCB cb) {
        return doQueryUpdate(serviceRank, cb, null);
    }

    /**
     * Delete the several entities by query. (NonExclusiveControl)
     * <pre>
     * ServiceRankCB cb = new ServiceRankCB();
     * cb.query().setFoo...(value);
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">queryDelete</span>(serviceRank, cb);
     * </pre>
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @return The deleted count.
     * @throws NonQueryDeleteNotAllowedException When the query has no condition.
     */
    public int queryDelete(ServiceRankCB cb) {
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
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * <span style="color: #3F7E5E">// if auto-increment, you don't need to set the PK value</span>
     * serviceRank.setFoo...(value);
     * serviceRank.setBar...(value);
     * InsertOption&lt;ServiceRankCB&gt; option = new InsertOption&lt;ServiceRankCB&gt;();
     * <span style="color: #3F7E5E">// you can insert by your values for common columns</span>
     * option.disableCommonColumnAutoSetup();
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">varyingInsert</span>(serviceRank, option);
     * ... = serviceRank.getPK...(); <span style="color: #3F7E5E">// if auto-increment, you can get the value after</span>
     * </pre>
     * @param serviceRank The entity of insert. (NotNull, PrimaryKeyNullAllowed: when auto-increment)
     * @param opLambda The callback for option of insert for varying requests. (NotNull)
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsert(ServiceRank serviceRank, WritableOptionCall<ServiceRankCB, InsertOption<ServiceRankCB>> opLambda) {
        doInsert(serviceRank, createInsertOption(opLambda));
    }

    /**
     * Update the entity with varying requests modified-only. (ZeroUpdateException, NonExclusiveControl) <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification), disableCommonColumnAutoSetup(). <br>
     * Other specifications are same as update(entity).
     * <pre>
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * serviceRank.setPK...(value); <span style="color: #3F7E5E">// required</span>
     * serviceRank.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// if exclusive control, the value of concurrency column is required</span>
     * serviceRank.<span style="color: #CC4747">setVersionNo</span>(value);
     * <span style="color: #70226C">try</span> {
     *     <span style="color: #3F7E5E">// you can update by self calculation values</span>
     *     UpdateOption&lt;ServiceRankCB&gt; option = new UpdateOption&lt;ServiceRankCB&gt;();
     *     option.self(new SpecifyQuery&lt;ServiceRankCB&gt;() {
     *         public void specify(ServiceRankCB cb) {
     *             cb.specify().<span style="color: #CC4747">columnXxxCount()</span>;
     *         }
     *     }).plus(1); <span style="color: #3F7E5E">// XXX_COUNT = XXX_COUNT + 1</span>
     *     <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">varyingUpdate</span>(serviceRank, option);
     * } <span style="color: #70226C">catch</span> (EntityAlreadyUpdatedException e) { <span style="color: #3F7E5E">// if concurrent update</span>
     *     ...
     * }
     * </pre>
     * @param serviceRank The entity of update. (NotNull, PrimaryKeyNotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingUpdate(ServiceRank serviceRank, WritableOptionCall<ServiceRankCB, UpdateOption<ServiceRankCB>> opLambda) {
        doUpdate(serviceRank, createUpdateOption(opLambda));
    }

    /**
     * Insert or update the entity with varying requests. (ExclusiveControl: when update) <br>
     * Other specifications are same as insertOrUpdate(entity).
     * @param serviceRank The entity of insert or update. (NotNull)
     * @param insertOpLambda The callback for option of insert for varying requests. (NotNull)
     * @param updateOpLambda The callback for option of update for varying requests. (NotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     * @throws EntityAlreadyExistsException When the entity already exists. (unique constraint violation)
     */
    public void varyingInsertOrUpdate(ServiceRank serviceRank, WritableOptionCall<ServiceRankCB, InsertOption<ServiceRankCB>> insertOpLambda, WritableOptionCall<ServiceRankCB, UpdateOption<ServiceRankCB>> updateOpLambda) {
        doInsertOrUpdate(serviceRank, createInsertOption(insertOpLambda), createUpdateOption(updateOpLambda));
    }

    /**
     * Delete the entity with varying requests. (ZeroUpdateException, NonExclusiveControl) <br>
     * Now a valid option does not exist. <br>
     * Other specifications are same as delete(entity).
     * @param serviceRank The entity of delete. (NotNull, PrimaryKeyNotNull, ConcurrencyColumnNotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @throws EntityAlreadyDeletedException When the entity has already been deleted. (not found)
     * @throws EntityDuplicatedException When the entity has been duplicated.
     */
    public void varyingDelete(ServiceRank serviceRank, WritableOptionCall<ServiceRankCB, DeleteOption<ServiceRankCB>> opLambda) {
        doDelete(serviceRank, createDeleteOption(opLambda));
    }

    // -----------------------------------------------------
    //                                          Batch Update
    //                                          ------------
    /**
     * Batch-insert the list with varying requests. <br>
     * For example, disableCommonColumnAutoSetup()
     * , disablePrimaryKeyIdentity(), limitBatchInsertLogging(). <br>
     * Other specifications are same as batchInsert(entityList).
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of insert for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchInsert(List<ServiceRank> serviceRankList, WritableOptionCall<ServiceRankCB, InsertOption<ServiceRankCB>> opLambda) {
        return doBatchInsert(serviceRankList, createInsertOption(opLambda));
    }

    /**
     * Batch-update the list with varying requests. <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), limitBatchUpdateLogging(). <br>
     * Other specifications are same as batchUpdate(entityList).
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @return The array of updated count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchUpdate(List<ServiceRank> serviceRankList, WritableOptionCall<ServiceRankCB, UpdateOption<ServiceRankCB>> opLambda) {
        return doBatchUpdate(serviceRankList, createUpdateOption(opLambda));
    }

    /**
     * Batch-delete the list with varying requests. <br>
     * For example, limitBatchDeleteLogging(). <br>
     * Other specifications are same as batchDelete(entityList).
     * @param serviceRankList The list of the entity. (NotNull, EmptyAllowed, PrimaryKeyNotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @return The array of deleted count. (NotNull, EmptyAllowed)
     */
    public int[] varyingBatchDelete(List<ServiceRank> serviceRankList, WritableOptionCall<ServiceRankCB, DeleteOption<ServiceRankCB>> opLambda) {
        return doBatchDelete(serviceRankList, createDeleteOption(opLambda));
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
    public int varyingQueryInsert(QueryInsertSetupper<ServiceRank, ServiceRankCB> manyArgLambda, WritableOptionCall<ServiceRankCB, InsertOption<ServiceRankCB>> opLambda) {
        return doQueryInsert(manyArgLambda, createInsertOption(opLambda));
    }

    /**
     * Update the several entities by query with varying requests non-strictly modified-only. {NonExclusiveControl} <br>
     * For example, self(selfCalculationSpecification), specify(updateColumnSpecification)
     * , disableCommonColumnAutoSetup(), allowNonQueryUpdate(). <br>
     * Other specifications are same as queryUpdate(entity, cb).
     * <pre>
     * <span style="color: #3F7E5E">// ex) you can update by self calculation values</span>
     * ServiceRank serviceRank = <span style="color: #70226C">new</span> ServiceRank();
     * <span style="color: #3F7E5E">// you don't need to set PK value</span>
     * <span style="color: #3F7E5E">//serviceRank.setPK...(value);</span>
     * serviceRank.setOther...(value); <span style="color: #3F7E5E">// you should set only modified columns</span>
     * <span style="color: #3F7E5E">// you don't need to set a value of concurrency column</span>
     * <span style="color: #3F7E5E">// (auto-increment for version number is valid though non-exclusive control)</span>
     * <span style="color: #3F7E5E">//serviceRank.setVersionNo(value);</span>
     * ServiceRankCB cb = <span style="color: #70226C">new</span> ServiceRankCB();
     * cb.query().setFoo...(value);
     * UpdateOption&lt;ServiceRankCB&gt; option = <span style="color: #70226C">new</span> UpdateOption&lt;ServiceRankCB&gt;();
     * option.self(new SpecifyQuery&lt;ServiceRankCB&gt;() {
     *     public void specify(ServiceRankCB cb) {
     *         cb.specify().<span style="color: #CC4747">columnFooCount()</span>;
     *     }
     * }).plus(1); <span style="color: #3F7E5E">// FOO_COUNT = FOO_COUNT + 1</span>
     * <span style="color: #0000C0">serviceRankBhv</span>.<span style="color: #CC4747">varyingQueryUpdate</span>(serviceRank, cb, option);
     * </pre>
     * @param serviceRank The entity that contains update values. (NotNull) {PrimaryKeyNotRequired}
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @param opLambda The callback for option of update for varying requests. (NotNull)
     * @return The updated count.
     * @throws NonQueryUpdateNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryUpdate(ServiceRank serviceRank, ServiceRankCB cb, WritableOptionCall<ServiceRankCB, UpdateOption<ServiceRankCB>> opLambda) {
        return doQueryUpdate(serviceRank, cb, createUpdateOption(opLambda));
    }

    /**
     * Delete the several entities by query with varying requests non-strictly. <br>
     * For example, allowNonQueryDelete(). <br>
     * Other specifications are same as batchUpdateNonstrict(entityList).
     * @param cb The condition-bean of ServiceRank. (NotNull)
     * @param opLambda The callback for option of delete for varying requests. (NotNull)
     * @return The deleted count.
     * @throws NonQueryDeleteNotAllowedException When the query has no condition (if not allowed).
     */
    public int varyingQueryDelete(ServiceRankCB cb, WritableOptionCall<ServiceRankCB, DeleteOption<ServiceRankCB>> opLambda) {
        return doQueryDelete(cb, createDeleteOption(opLambda));
    }

    // ===================================================================================
    //                                                                          OutsideSql
    //                                                                          ==========
    /**
     * Prepare the all facade executor of outside-SQL to execute it.
     * <pre>
     * <span style="color: #3F7E5E">// main style</span> 
     * serviceRankBhv.outideSql().selectEntity(pmb); <span style="color: #3F7E5E">// optional</span> 
     * serviceRankBhv.outideSql().selectList(pmb); <span style="color: #3F7E5E">// ListResultBean</span>
     * serviceRankBhv.outideSql().selectPage(pmb); <span style="color: #3F7E5E">// PagingResultBean</span>
     * serviceRankBhv.outideSql().selectPagedListOnly(pmb); <span style="color: #3F7E5E">// ListResultBean</span>
     * serviceRankBhv.outideSql().selectCursor(pmb, handler); <span style="color: #3F7E5E">// (by handler)</span>
     * serviceRankBhv.outideSql().execute(pmb); <span style="color: #3F7E5E">// int (updated count)</span>
     * serviceRankBhv.outideSql().call(pmb); <span style="color: #3F7E5E">// void (pmb has OUT parameters)</span>
     *
     * <span style="color: #3F7E5E">// traditional style</span> 
     * serviceRankBhv.outideSql().traditionalStyle().selectEntity(path, pmb, entityType);
     * serviceRankBhv.outideSql().traditionalStyle().selectList(path, pmb, entityType);
     * serviceRankBhv.outideSql().traditionalStyle().selectPage(path, pmb, entityType);
     * serviceRankBhv.outideSql().traditionalStyle().selectPagedListOnly(path, pmb, entityType);
     * serviceRankBhv.outideSql().traditionalStyle().selectCursor(path, pmb, handler);
     * serviceRankBhv.outideSql().traditionalStyle().execute(path, pmb);
     *
     * <span style="color: #3F7E5E">// options</span> 
     * serviceRankBhv.outideSql().removeBlockComment().selectList()
     * serviceRankBhv.outideSql().removeLineComment().selectList()
     * serviceRankBhv.outideSql().formatSql().selectList()
     * </pre>
     * <p>The invoker of behavior command should be not null when you call this method.</p>
     * @return The new-created all facade executor of outside-SQL. (NotNull)
     */
    public OutsideSqlBasicExecutor<ServiceRankBhv> outsideSql() {
        OutsideSqlAllFacadeExecutor<ServiceRankBhv> facadeExecutor = doOutsideSql();
        return facadeExecutor.xbasicExecutor(); // variable to resolve generic type
    }

    // ===================================================================================
    //                                                                         Type Helper
    //                                                                         ===========
    protected Class<? extends ServiceRank> typeOfSelectedEntity() { return ServiceRank.class; }
    protected Class<ServiceRank> typeOfHandlingEntity() { return ServiceRank.class; }
    protected Class<ServiceRankCB> typeOfHandlingConditionBean() { return ServiceRankCB.class; }
}
