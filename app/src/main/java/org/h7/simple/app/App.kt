/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.app

import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric
import org.h7.simple.BuildConfig


/**
 * * Created by Alexey Rogovoy (lexapublic@gmail.com) on 26.06.2018.
 */
class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initFabric()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
    private fun initFabric() {
        val crashlitics = Crashlytics.Builder()
                .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build()
        Fabric.with(this,crashlitics)
    }

}