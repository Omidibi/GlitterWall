package com.omid.glitterwall.fragments.showImageByCatIdFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.CatByIdList
import com.omid.glitterwall.utils.configuration.AppConfiguration
import com.omid.glitterwall.utils.internetLiveData.CheckNetworkConnection
import com.omid.glitterwall.utils.networkAvailable.NetworkAvailable

class ImgCatIdViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private var catById: MutableLiveData<CatByIdList> = webServiceCaller.catById
    val checkNetworkConnection = CheckNetworkConnection(application)

    fun getImgByCatId(categoryId: String): MutableLiveData<CatByIdList> {
        webServiceCaller.getCatById(categoryId)
        return catById
    }

    fun checkNetworkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}