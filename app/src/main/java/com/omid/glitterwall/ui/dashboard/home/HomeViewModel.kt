package com.omid.glitterwall.ui.dashboard.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.Banner
import com.omid.glitterwall.models.HomeWallpaper
import com.omid.glitterwall.utils.internetLiveData.CheckNetworkConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private val networkConnection = CheckNetworkConnection(application)
    val homeWallpaper = MutableLiveData<HomeWallpaper>()
    val homeBanner = MutableLiveData<Banner>()

    init {
        networkConnection.observeForever { isConnected ->
            if (isConnected) {
                getBanner()
                getHomeWallpaper()
            }
        }
    }

    fun getBanner() {
        if (networkConnection.value == true) {
            CoroutineScope(Dispatchers.IO).launch {
                webServiceCaller.getBanner().apply {
                    homeBanner.postValue(this)
                }
            }
        }
    }

    fun getHomeWallpaper() {
        if (networkConnection.value == true) {
            CoroutineScope(Dispatchers.IO).launch {
                webServiceCaller.getHomeWallpaper().apply {
                    homeWallpaper.postValue(this)
                }
            }
        }
    }
}