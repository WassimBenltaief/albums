package com.wassim.showcase

import androidx.test.rule.ActivityTestRule
import com.wassim.showcase.features.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun numberOne() {
    }
}
