package com.omid.glitterwall.activity

import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.omid.glitterwall.R
import com.omid.glitterwall.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        bottomNavigationViewStateInActivity()
    }

    private fun setupBinding() {
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            HomeWidget.bnv = mainBnv
            HomeWidget.toolbar = mainToolbar
            titleToolbar.text = applicationContext.resources.getText(R.string.app_name)
            navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHost.navController
            NavigationUI.setupWithNavController(mainBnv, navController)
        }
    }

    private fun bottomNavigationViewStateInActivity() {
        binding.apply {
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            )

            val colors = intArrayOf(
                ContextCompat.getColor(applicationContext, R.color.gray),
                ContextCompat.getColor(applicationContext, R.color.Cerise)
            )

            val colorStateList = ColorStateList(states, colors)
            mainBnv.itemTextColor = colorStateList
            mainBnv.itemIconTintList = colorStateList

            mainBnv.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.home -> {
                        navController.navigate(R.id.homeFragment)
                        titleToolbar.text = applicationContext.resources.getText(R.string.app_name)
                    }

                    R.id.categories -> {
                        navController.navigate(R.id.categoriesFragment)
                        titleToolbar.text = applicationContext.resources.getText(R.string.categories)
                    }

                    R.id.fvtWall -> {
                        navController.navigate(R.id.favoriteWallpaperFragment)
                        titleToolbar.text = applicationContext.resources.getText(R.string.favorites)
                    }
                }
                true
            }
        }
    }
}