package com.omid.glitterwall.fragments.showImageByCatIdFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.CatByIdList
import com.omid.glitterwall.utils.internetLiveData.CheckNetworkConnection

class ImgCatIdViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private val checkNetworkConnection = CheckNetworkConnection(application)
    val catById: LiveData<CatByIdList> = webServiceCaller.catById

    init {
        checkNetworkConnection.observeForever { isConnect ->
            if (isConnect) {
                CId.cId?.let { getCatById(it) }
            }
        }
    }

    fun getCatById(categoryId: String) {
        if (checkNetworkConnection.value == true) {
            webServiceCaller.getCatById(categoryId)
        }
    }
}