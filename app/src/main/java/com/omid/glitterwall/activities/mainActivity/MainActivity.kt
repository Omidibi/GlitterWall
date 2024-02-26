package com.omid.glitterwall.activities.mainActivity

import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.omid.glitterwall.R
import com.omid.glitterwall.databinding.ActivityMainBinding
import com.omid.glitterwall.ui.dashboard.categories.CategoriesFragment
import com.omid.glitterwall.ui.dashboard.favorite.FavoriteWallpaperFragment
import com.omid.glitterwall.ui.dashboard.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentsList: MutableList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        bottomNavigationViewStateInActivity()
        fragmentsStateInActivity()
        viewPager2StateInActivity()
    }

    private fun setupBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        binding.apply {
            setContentView(root)
            setSupportActionBar(mainToolbar)
        }
    }

    private fun fragmentsStateInActivity(){
        binding.apply {
            fragmentsList = ArrayList()
            fragmentsList.add(HomeFragment())
            fragmentsList.add(CategoriesFragment())
            fragmentsList.add(FavoriteWallpaperFragment())
            mainVp.adapter = FragmentTabAdapter(this@MainActivity, fragmentsList).apply {
                mainVp.post { mainVp.setCurrentItem(0,false) }
            }
            /** نگه داری فرگمنت ها در حافظه*/
            mainVp.offscreenPageLimit = fragmentsList.size
        }
    }

    private fun bottomNavigationViewStateInActivity(){
        binding.apply {
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            )

            val colors = intArrayOf(
                ContextCompat.getColor(this@MainActivity,R.color.gray),
                ContextCompat.getColor(this@MainActivity,R.color.Cerise)
            )

            val colorStateList = ColorStateList(states, colors)
            mainBnv.itemTextColor = colorStateList
            mainBnv.itemIconTintList = colorStateList

            mainBnv.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.home -> mainVp.currentItem = 0

                    R.id.categories ->  mainVp.currentItem = 1

                    R.id.fvtWall -> mainVp.currentItem = 2

                }
                true
            }
        }
    }

    private fun viewPager2StateInActivity(){
        binding.apply {
            mainVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            mainBnv.menu.findItem(R.id.home).isChecked = true
                            titleToolbar.text = getString(R.string.app_name)
                        }

                        1 -> {
                            mainBnv.menu.findItem(R.id.categories).isChecked = true
                            titleToolbar.text = getString(R.string.Categories)
                        }

                        2 -> {
                            mainBnv.menu.findItem(R.id.fvtWall).isChecked = true
                            titleToolbar.text = getString(R.string.favorites)
                        }
                    }
                }
            })
            mainVp.isUserInputEnabled = false
        }
    }
}