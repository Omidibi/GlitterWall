package com.omid.glitterwall.ui.main.home

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.databinding.FragmentHomeBinding
import com.omid.glitterwall.model.listener.IListener
import com.omid.glitterwall.model.models.Banner
import com.omid.glitterwall.model.models.HomeWallpaper
import com.omid.glitterwall.util.customViews.ui.CustomUI
import retrofit2.Call
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val webServiceCaller = WebServiceCaller()
    private var currentPage = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        latestWallpapers()
        featuredWallpapers()
        banner()
        setupPagerBanner()
        srlStatusInFragment()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            CustomUI.customUI(this@HomeFragment, binding)
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
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, 3000, 3000)

            pagerBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    // این متد هر زمان که کاربر صفحه را می‌چرخاند فراخوانی می‌شود
                }

                override fun onPageSelected(position: Int) {
                    // این متد هر زمان که یک صفحه جدید انتخاب می‌شود فراخوانی می‌شود
                    currentPage = position
                }

                override fun onPageScrollStateChanged(state: Int) {
                    // این متد هر زمان که وضعیت پیمایش صفحه تغییر می‌کند فراخوانی می‌شود
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
                srlLatestWallpapers()
                srlFeaturedWallpapers()
                srlBanner()
                currentPage = 0
                srl.isRefreshing = false
            }
        }
    }

    private fun latestWallpapers() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
                override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                    if (isAdded) {
                        pbHome.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvLatest.adapter = LatestWallpapersAdapter(response.homeWallpaper.latestWallpaper)
                        rvLatest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                        Log.e("", "")
                    }
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        clNoConnection.visibility = View.VISIBLE
                        btnTry.setOnClickListener {
                            tryAgainLatestWallpapers()
                            tryAgainFeaturedWallpapers()
                            tryAgainBanner()
                        }
                    }
                }
            })
        }
    }

    private fun featuredWallpapers() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
                override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvFeatured.adapter = FeaturedWallpapersAdapter(response.homeWallpaper.allWallpaper)
                        rvFeatured.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    }
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        clNoConnection.visibility = View.VISIBLE
                        btnTry.setOnClickListener {
                            tryAgainLatestWallpapers()
                            tryAgainFeaturedWallpapers()
                            tryAgainBanner()
                        }
                    }
                }
            })
        }
    }

    private fun banner() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getBanner(object : IListener<Banner> {
                override fun onSuccess(call: Call<Banner>, response: Banner) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        pagerBanner.adapter = BannerAdapter(response.banner)
                    }
                }

                override fun onFailure(call: Call<Banner>, t: Throwable, errorResponse: String) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        clNoConnection.visibility = View.VISIBLE
                        btnTry.setOnClickListener {
                            tryAgainLatestWallpapers()
                            tryAgainFeaturedWallpapers()
                            tryAgainBanner()
                        }
                    }
                }
            })
        }
    }

    private fun tryAgainLatestWallpapers() {
        binding.apply {
            clNoConnection.visibility = View.GONE
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
                override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                    clNoConnection.visibility = View.GONE
                    pbHome.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    nsv.visibility = View.VISIBLE
                    rvLatest.adapter = LatestWallpapersAdapter(response.homeWallpaper.latestWallpaper)
                    rvLatest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    Log.e("", "")
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                    pbHome.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun tryAgainFeaturedWallpapers() {
        binding.apply {
            clNoConnection.visibility = View.GONE
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
                override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                    clNoConnection.visibility = View.GONE
                    pbHome.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    nsv.visibility = View.VISIBLE
                    rvFeatured.adapter = FeaturedWallpapersAdapter(response.homeWallpaper.allWallpaper)
                    rvFeatured.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                    pbHome.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun tryAgainBanner() {
        binding.apply {
            clNoConnection.visibility = View.GONE
            pbHome.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getBanner(object : IListener<Banner> {
                override fun onSuccess(call: Call<Banner>, response: Banner) {
                    Log.e("", "")
                    clNoConnection.visibility = View.GONE
                    pbHome.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    nsv.visibility = View.VISIBLE
                    pagerBanner.adapter = BannerAdapter(response.banner)
                }

                override fun onFailure(call: Call<Banner>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                    pbHome.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun srlLatestWallpapers() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            nsv.visibility = View.GONE
            webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
                override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                    pbHome.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    rvLatest.adapter = LatestWallpapersAdapter(response.homeWallpaper.latestWallpaper)
                    rvLatest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    Log.e("", "")
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                    pbHome.visibility = View.VISIBLE
                    nsv.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        tryAgainLatestWallpapers()
                        tryAgainFeaturedWallpapers()
                        tryAgainBanner()
                    }
                }
            })
        }
    }

    private fun srlFeaturedWallpapers() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            nsv.visibility = View.GONE
            webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
                override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                    Log.e("", "")
                    pbHome.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    rvFeatured.adapter = FeaturedWallpapersAdapter(response.homeWallpaper.allWallpaper)
                    rvFeatured.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                    pbHome.visibility = View.VISIBLE
                    nsv.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        tryAgainLatestWallpapers()
                        tryAgainFeaturedWallpapers()
                        tryAgainBanner()
                    }
                }
            })
        }
    }

    private fun srlBanner() {
        binding.apply {
            pbHome.visibility = View.VISIBLE
            nsv.visibility = View.GONE
            webServiceCaller.getBanner(object : IListener<Banner> {
                override fun onSuccess(call: Call<Banner>, response: Banner) {
                    Log.e("", "")
                    pbHome.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    pagerBanner.adapter = BannerAdapter(response.banner)
                }

                override fun onFailure(call: Call<Banner>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                    pbHome.visibility = View.VISIBLE
                    nsv.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        tryAgainLatestWallpapers()
                        tryAgainFeaturedWallpapers()
                        tryAgainBanner()
                    }
                }
            })
        }
    }
}