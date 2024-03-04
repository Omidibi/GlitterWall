package com.omid.glitterwall.ui.dashboard.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.listener.IListener
import com.omid.glitterwall.models.models.Banner
import com.omid.glitterwall.models.models.HomeWallpaper
import retrofit2.Call

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val homeWallpaper = MutableLiveData<HomeWallpaper>()
    val errorHomeWallpaper = MutableLiveData<Boolean>()
    val homeBanner = MutableLiveData<Banner>()
    val errorBanner = MutableLiveData<Boolean>()

    init {
        getBanner()
        getHomeWallpaper()
    }

    fun getBanner() {
        webServiceCaller.getBanner(object : IListener<Banner> {
            override fun onSuccess(call: Call<Banner>, response: Banner) {
                homeBanner.postValue(response)
                errorBanner.postValue(false)
            }

            override fun onFailure(call: Call<Banner>, t: Throwable, errorResponse: String) {
                errorBanner.postValue(true)
            }

        })
    }

    fun getHomeWallpaper() {
        webServiceCaller.getHomeWallpaper(object : IListener<HomeWallpaper> {
            override fun onSuccess(call: Call<HomeWallpaper>, response: HomeWallpaper) {
                homeWallpaper.postValue(response)
                errorHomeWallpaper.postValue(false)
            }

            override fun onFailure(call: Call<HomeWallpaper>, t: Throwable, errorResponse: String) {
                errorHomeWallpaper.postValue(true)
            }

        })
    }
}