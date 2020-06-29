package com.wassim.showcase

import com.wassim.showcase.di.appModule
import com.wassim.showcase.di.databaseModule
import com.wassim.showcase.di.imageLoader
import com.wassim.showcase.di.networkModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModulesInstrumentationTest : KoinTest {

    @Test
    fun checkAllModules() = checkModules {
        modules(
            appModule,
            networkModule,
            databaseModule,
            imageLoader
        )
    }
}