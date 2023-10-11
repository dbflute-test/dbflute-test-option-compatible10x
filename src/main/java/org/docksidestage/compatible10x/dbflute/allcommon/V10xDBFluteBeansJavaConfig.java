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
package org.docksidestage.compatible10x.dbflute.allcommon;

import org.dbflute.bhv.core.context.logmask.BehaviorLogMaskProvider;
import org.dbflute.bhv.core.context.logmask.parts.AlreadyUpdatedBeanPKOnlyMaskMan;
import org.dbflute.exception.EntityAlreadyUpdatedException.AlreadyUpdatedBeanMaskMan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jflute
 */
// #thinking jflute need it, but unneeded in case of SpringBoot, why? (2023/10/11)
@ComponentScan(value = "org.docksidestage.compatible10x.dbflute.exbhv", lazyInit = true)
public class V10xDBFluteBeansJavaConfig extends DBFluteBeansJavaConfig {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /**
     * DBFluteのBehaviorで出力するログ(例外メッセージなど)の個人情報を含む可能性のある箇所をマスクするかどうか？ <br>
     * 開発環境ではデバッグのためにfalseにして、本番もしくは本番に相当する環境のみでtrueにすると良い。
     */
    // #thinking jflute how to read application.properties (2023/10/11)
    //@Value("${dbflute.behavior.log.mask}")
    //private boolean needsBehaviorLogMask;
    private boolean needsBehaviorLogMask = true;

    // ===================================================================================
    //                                                                        Introduction
    //                                                                        ============
    /**
     * アプリ独自のDBFluteConfigを設定するためのオーバーライド。<br>
     * 環境依存など動的に値が変わるような設定は、Spring起動からリクエスト受付開始するまでの間にすると良い。<br>
     * ゆえに、DBFluteの初期化タイミングであるこのメソッドで実装する。<br>
     * (今後、他にもDBFluteConfigを使うことがあったらここに追記していく)
     */
    @Override
    protected void hookInitialization() {
        super.hookInitialization();

        initializeBehaviorLogMark();
    }

    private void initializeBehaviorLogMark() {
        if (!needsBehaviorLogMask) { // e.g. 開発環境だったら
            return;
        }
        // e.g. 本番環境だったら
        DBFluteConfig config = DBFluteConfig.getInstance();

        // DBFluteConfigはロックが掛かっているので外す
        // (DBFluteConfigのロックはDBFluteの初期化終了時に自動的にロックされるので戻さなくて良い)
        config.unlock();

        // まさしくここがBehaviorのログをマスクする設定
        config.setBehaviorLogMaskProvider(new BehaviorLogMaskProvider() {
            @Override
            public AlreadyUpdatedBeanMaskMan provideAlreadyUpdatedBeanMaskMan() {
                // 本番でもデバッグのためにPKだけは出すようにする
                // (PKはDB設計のポリシー的にIDだけなので出力しても問題ないと判断)
                return new AlreadyUpdatedBeanPKOnlyMaskMan();
            }
        });
    }
}
