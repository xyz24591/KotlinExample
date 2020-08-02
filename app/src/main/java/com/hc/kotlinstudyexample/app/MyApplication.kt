package com.hc.kotlinstudyexample.app

import android.app.Application
import com.hc.kotlinstudyexample.BuildConfig
import timber.log.Timber

/**
 * Created by hcw  on 2020/8/2
 * 类描述：
 * all rights reserved
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
