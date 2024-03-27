package com.omid.glitterwall.ui.dashboard.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.models.Categories
import com.omid.glitterwall.utils.internetLiveData.CheckNetworkConnection

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private val networkConnection = CheckNetworkConnection(application)
    val category: LiveData<Categories> = webServiceCaller.category

    init {
        networkConnection.observeForever { isConnect ->
            if (isConnect) {
                getCategoryList()
            }
        }
    }

    fun getCategoryList() {
        if (networkConnection.value == true) {
            webServiceCaller.getCategoryList()
        }
    }
}