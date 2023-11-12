package com.navin.glitterwall.activities

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.navin.glitterwall.R
import com.navin.glitterwall.adapters.FragmentTabAdapter
import com.navin.glitterwall.databinding.ActivityMainBinding
import com.navin.glitterwall.fragments.CategoriesFragment
import com.navin.glitterwall.fragments.HomeFragment
import com.navin.glitterwall.fragments.WallpapersFragment
import com.navin.glitterwall.util.Font

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentsList: MutableList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            setSupportActionBar(mainToolbar)
            Font.mainActivity(applicationContext,binding)
            fragmentsList = ArrayList()
            fragmentsList.add(HomeFragment())
            fragmentsList.add(WallpapersFragment())
            fragmentsList.add(CategoriesFragment())
            mainVp.adapter = FragmentTabAdapter(this@MainActivity, fragmentsList).apply {
                mainVp.post { mainVp.setCurrentItem(0,false) }
            }
            /** نگه داری فرگمنت ها در حافظه*/
            mainVp.offscreenPageLimit = fragmentsList.size
            mainBnv.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.home -> mainVp.currentItem = 0

                    R.id.wallpapers -> mainVp.currentItem = 1

                    R.id.categories ->  mainVp.currentItem = 2

                }
                true
            }

            mainVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            mainBnv.menu.findItem(R.id.home).isChecked = true
                            titleToolbar.text = getString(R.string.Home)
                        }

                        1 -> {
                            mainBnv.menu.findItem(R.id.wallpapers).isChecked = true
                            titleToolbar.text = getString(R.string.Wallpapers)
                        }

                        2 -> {
                            mainBnv.menu.findItem(R.id.categories).isChecked = true
                            titleToolbar.text = getString(R.string.Categories)
                        }
                    }
                }
            })
            mainVp.isUserInputEnabled = false

            val states = arrayOf(
                intArrayOf(-android.R.attr.state_checked), // حالت انتخاب نشده
                intArrayOf(android.R.attr.state_checked) // حالت انتخاب شده
            )

            val colors = intArrayOf(
               ContextCompat.getColor(this@MainActivity,R.color.gray), // رنگ برای حالت انتخاب نشده
                ContextCompat.getColor(this@MainActivity,R.color.Cerise) // رنگ برای حالت انتخاب شده
            )

            val colorStateList = ColorStateList(states, colors)
            mainBnv.itemTextColor = colorStateList
            mainBnv.itemIconTintList = colorStateList
        }
    }
}