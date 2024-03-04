package com.omid.glitterwall.ui.dashboard.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.omid.glitterwall.databinding.FragmentHomeBinding
import com.omid.glitterwall.ui.customViews.customUI.CustomUI
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var owner: LifecycleOwner
    private lateinit var homeViewModel: HomeViewModel
    private var currentPage = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        homeObservers()
        setupPagerBanner()
        srlStatusInFragment()
        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        CustomUI.customUI(this@HomeFragment, binding)
    }

    private fun homeObservers() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            nsv.visibility = View.GONE
            homeViewModel.homeBanner.observe(viewLifecycleOwner) { banner ->
                pbHome.visibility = View.GONE
                srl.visibility = View.VISIBLE
                nsv.visibility = View.VISIBLE
                pagerBanner.adapter = BannerAdapter(banner.banner)
            }
            homeViewModel.errorBanner.observe(viewLifecycleOwner) { hasErrorBanner ->
                if (hasErrorBanner) {
                    pbHome.visibility = View.GONE
                    srl.visibility = View.GONE
                    nsv.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        pbHome.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        nsv.visibility = View.GONE
                        clNoConnection.visibility = View.GONE
                        homeViewModel.getBanner()
                        homeViewModel.getHomeWallpaper()
                    }
                }
            }
            homeViewModel.homeWallpaper.observe(viewLifecycleOwner) { homeWallpaper ->
                pbHome.visibility = View.GONE
                srl.visibility = View.VISIBLE
                nsv.visibility = View.VISIBLE
                rvLatest.adapter = LatestWallpapersAdapter(homeWallpaper.homeWallpaper.latestWallpaper)
                rvLatest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvFeatured.adapter = FeaturedWallpapersAdapter(homeWallpaper.homeWallpaper.allWallpaper)
                rvFeatured.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            homeViewModel.errorHomeWallpaper.observe(viewLifecycleOwner) { haseErrorHomeWallpaper ->
                if (haseErrorHomeWallpaper){
                    pbHome.visibility = View.GONE
                    srl.visibility = View.GONE
                    nsv.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        pbHome.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        nsv.visibility = View.GONE
                        clNoConnection.visibility = View.GONE
                        homeViewModel.getBanner()
                        homeViewModel.getHomeWallpaper()
                    }
                }
            }
        }
    }

    private fun setupPagerBanner() {
        binding.apply {
            val handler = Handler(Looper.getMainLooper())
            val update = Runnable {
                if (currentPage == (pagerBanner.adapter?.count ?: 0)) {
                    currentPage = 0
                }
                pagerBanner.setCurrentItem(currentPage++, true)
            }
            Timer().schedule(object : TimerTask() { override fun run() { handler.post(update) }
            }, 3000, 3000)

            pagerBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    currentPage = position
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                pageIndicatorView.elevation = 1000f
            }
        }
    }

    private fun srlStatusInFragment() {
        binding.apply {
            srl.setOnRefreshListener {
                srlObservers()
                currentPage = 0
                srl.isRefreshing = false
            }
        }
    }

    private fun srlObservers() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            nsv.visibility = View.GONE
            homeViewModel.getBanner()
            homeViewModel.getHomeWallpaper()
        }
    }
}