package com.wassim.showcase

import com.wassim.showcase.di.imageLoader
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModulesInstrumentationTest : KoinTest {

    @Test
    fun checkAllModules() = checkModules {
        modules(imageLoader)
    }
}