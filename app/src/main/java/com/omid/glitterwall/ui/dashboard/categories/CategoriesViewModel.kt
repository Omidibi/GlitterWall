package com.omid.glitterwall.ui.dashboard.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.listener.IListener
import com.omid.glitterwall.models.models.Categories
import retrofit2.Call

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val errorCategories = MutableLiveData<Boolean>()
    val category = MutableLiveData<Categories>()

    init {
        getCategoryList()
    }

    fun getCategoryList() {
        webServiceCaller.getCategoryList(object : IListener<Categories> {
            override fun onSuccess(call: Call<Categories>, response: Categories) {
                category.postValue(response)
                errorCategories.postValue(false)

            }

            override fun onFailure(call: Call<Categories>, t: Throwable, errorResponse: String) {
                errorCategories.postValue(true)
            }

        })
    }
}