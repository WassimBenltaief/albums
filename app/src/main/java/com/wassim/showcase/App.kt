package com.wassim.showcase

import android.app.Application
import com.wassim.showcase.di.appModule
import com.wassim.showcase.di.imageLoader
import com.wassim.showcase.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
                imageLoader
            )
        }
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}