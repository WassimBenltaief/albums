package com.wassim.showcase.features

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.wassim.showcase.R
import com.wassim.showcase.utils.KeepStateNavigator
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
                navHostFragment.childFragmentManager,
                R.id.navHostFragment
            )
            navigatorProvider += navigator
            setGraph(R.navigation.app_nav_graph)
        }
    }
}
