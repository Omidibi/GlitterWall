package com.omid.glitterwall.api

import androidx.lifecycle.MutableLiveData
import com.omid.glitterwall.models.Banner
import com.omid.glitterwall.models.CatByIdList
import com.omid.glitterwall.models.Categories
import com.omid.glitterwall.models.HomeWallpaper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WebServiceCaller {

    private val iService = ApiRetrofit.retrofit.create(IService::class.java)
    val catById = MutableLiveData<CatByIdList>()
    val category = MutableLiveData<Categories>()

    fun getCatById(categoryId: String) {
        CompositeDisposable().apply {
            val disposable = iService.wallByCatId(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ catByIdList ->
                    catById.postValue(catByIdList)
                }, { error ->

                })
            this.add(disposable)
        }
    }

    fun getCategoryList() {
        CompositeDisposable().apply {
            val disposable = iService.categoriesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ categories ->
                    category.postValue(categories)
                }, { error ->

                })
            this.add(disposable)
        }
    }

    suspend fun getHomeWallpaper(): HomeWallpaper? {
        iService.home().apply { return if (this.isSuccessful) this.body() else null }
    }

    suspend fun getBanner(): Banner? {
        iService.banner().apply { return if (this.isSuccessful) this.body() else null }
    }
}