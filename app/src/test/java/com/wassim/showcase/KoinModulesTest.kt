package com.wassim.showcase

import com.wassim.testutils.MainCoroutineRule
import com.wassim.showcase.di.appModule
import com.wassim.showcase.di.networkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.test.check.checkModules

class KoinModulesTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesTestRule = MainCoroutineRule()

    @Test
    fun checkAllModules() = checkModules {
        modules(
            appModule,
            networkModule
        )
    }
}