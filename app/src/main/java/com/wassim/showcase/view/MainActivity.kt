package com.wassim.showcase.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.Navigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wassim.showcase.R
import com.wassim.showcase.core.utils.KeepStateNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    @SuppressLint("RestrictedApi")
    private fun setupNavigation() {
        val host: NavHostFragment = navHostFragment as NavHostFragment? ?: return
        with(host.navController) {
            bottomNavigationView.setupWithNavController(this)
            val navigator = KeepStateNavigator(
                applicationContext,
                host.childFragmentManager,
                R.id.navHostFragment
            )
            navigatorProvider.addNavigator(navigator as Navigator<out NavDestination>)
            setGraph(R.navigation.app_nav_graph)
        }
    }
}
