package com.omid.glitterwall.activities.showImageByCatIdActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.listener.IListener
import com.omid.glitterwall.models.models.CatByIdList
import retrofit2.Call

class ImgCatIdViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val catById = MutableLiveData<CatByIdList>()
    val errorCatById = MutableLiveData<Boolean>()

    init {
        CId.cId?.let { getCatById(it) }
    }

    fun getCatById(categoryId: String) {
        webServiceCaller.getCatById(categoryId, object : IListener<CatByIdList> {
            override fun onSuccess(call: Call<CatByIdList>, response: CatByIdList) {
                catById.postValue(response)
                errorCatById.postValue(false)
            }

            override fun onFailure(call: Call<CatByIdList>, t: Throwable, errorResponse: String) {
                errorCatById.postValue(true)
            }

        })
    }
}