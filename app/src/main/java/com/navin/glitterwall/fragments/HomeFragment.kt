package com.navin.glitterwall.fragments

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
import com.navin.glitterwall.adapters.BannerAdapter
import com.navin.glitterwall.adapters.FeaturedWallpapersAdapter
import com.navin.glitterwall.adapters.LatestWallpapersAdapter
import com.navin.glitterwall.api.ApiRetrofit
import com.navin.glitterwall.api.IService
import com.navin.glitterwall.databinding.FragmentHomeBinding
import com.navin.glitterwall.models.Banner
import com.navin.glitterwall.models.HomeWallpaper
import com.navin.glitterwall.util.ui.CustomUI
import com.navin.glitterwall.util.font.Font
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var iService: IService
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
            iService = ApiRetrofit.retrofit.create(IService::class.java)
            Font.homeFragment(requireContext(), binding)
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
            iService.home().enqueue(object : Callback<HomeWallpaper> {
                override fun onResponse(
                    call: Call<HomeWallpaper>,
                    response: Response<HomeWallpaper>
                ) {
                    if (isAdded) {
                        pbHome.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvLatest.adapter = LatestWallpapersAdapter(
                            requireContext(),
                            response.body()?.homeWallpaper?.latestWallpaper!!
                        )
                        rvLatest.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        Log.e("", "")
                    }
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
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
            iService.home().enqueue(object : Callback<HomeWallpaper> {
                override fun onResponse(
                    call: Call<HomeWallpaper>,
                    response: Response<HomeWallpaper>
                ) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvFeatured.adapter = FeaturedWallpapersAdapter(
                            requireContext(),
                            response.body()?.homeWallpaper?.allWallpaper!!
                        )
                        rvFeatured.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    }
                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
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
            iService.banner().enqueue(object : Callback<Banner> {
                override fun onResponse(call: Call<Banner>, response: Response<Banner>) {
                    if (isAdded) {
                        Log.e("", "")
                        pbHome.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        pagerBanner.adapter = BannerAdapter(requireContext(), response.body()?.banner!!)
                    }
                }

                override fun onFailure(call: Call<Banner>, t: Throwable) {
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
            iService.home().enqueue(object : Callback<HomeWallpaper> {
                override fun onResponse(
                    call: Call<HomeWallpaper>,
                    response: Response<HomeWallpaper>
                ) {
                    clNoConnection.visibility = View.GONE
                    pbHome.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    nsv.visibility = View.VISIBLE
                    rvLatest.adapter = LatestWallpapersAdapter(
                        requireContext(),
                        response.body()?.homeWallpaper?.latestWallpaper!!
                    )
                    rvLatest.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    Log.e("", "")

                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
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
            iService.home().enqueue(object : Callback<HomeWallpaper> {
                override fun onResponse(
                    call: Call<HomeWallpaper>,
                    response: Response<HomeWallpaper>
                ) {
                    Log.e("", "")
                    clNoConnection.visibility = View.GONE
                    pbHome.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    nsv.visibility = View.VISIBLE
                    rvFeatured.adapter = FeaturedWallpapersAdapter(
                        requireContext(),
                        response.body()?.homeWallpaper?.allWallpaper!!
                    )
                    rvFeatured.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
                    Log.e("", "")
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
            iService.banner().enqueue(object : Callback<Banner> {
                override fun onResponse(call: Call<Banner>, response: Response<Banner>) {
                    Log.e("", "")
                    clNoConnection.visibility = View.GONE
                    pbHome.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    nsv.visibility = View.VISIBLE
                    pagerBanner.adapter = BannerAdapter(requireContext(), response.body()?.banner!!)
                }

                override fun onFailure(call: Call<Banner>, t: Throwable) {
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
            iService.home().enqueue(object : Callback<HomeWallpaper> {
                override fun onResponse(
                    call: Call<HomeWallpaper>,
                    response: Response<HomeWallpaper>
                ) {
                    pbHome.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    rvLatest.adapter = LatestWallpapersAdapter(
                        requireContext(),
                        response.body()?.homeWallpaper?.latestWallpaper!!
                    )
                    rvLatest.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    Log.e("", "")

                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
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
            iService.home().enqueue(object : Callback<HomeWallpaper> {
                override fun onResponse(
                    call: Call<HomeWallpaper>,
                    response: Response<HomeWallpaper>
                ) {
                    Log.e("", "")
                    pbHome.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    rvFeatured.adapter = FeaturedWallpapersAdapter(
                        requireContext(),
                        response.body()?.homeWallpaper?.allWallpaper!!
                    )
                    rvFeatured.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                }

                override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
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
            iService.banner().enqueue(object : Callback<Banner> {
                override fun onResponse(call: Call<Banner>, response: Response<Banner>) {
                    Log.e("", "")
                    pbHome.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    pagerBanner.adapter = BannerAdapter(requireContext(), response.body()?.banner!!)
                }

                override fun onFailure(call: Call<Banner>, t: Throwable) {
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