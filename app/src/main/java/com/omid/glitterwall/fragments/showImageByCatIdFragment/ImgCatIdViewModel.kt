package com.omid.glitterwall.fragments.showImageByCatIdFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.CatByIdList

class ImgCatIdViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private var catById: MutableLiveData<CatByIdList> = webServiceCaller.catById

    fun getImgByCatId(categoryId: String): MutableLiveData<CatByIdList> {
        webServiceCaller.getCatById(categoryId)
        return catById
    }
}