package com.omid.glitterwall.api

import com.omid.glitterwall.models.models.Banner
import com.omid.glitterwall.models.models.CatByIdList
import com.omid.glitterwall.models.models.Categories
import com.omid.glitterwall.models.models.HomeWallpaper
import com.omid.glitterwall.models.listener.IListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebServiceCaller {
    private val iService = ApiRetrofit.retrofit.create(IService::class.java)

    fun getCatById(categoryId: String, iListener: IListener<CatByIdList>) {
        iService.wallByCatId(categoryId).enqueue(object : Callback<CatByIdList> {
            override fun onResponse(call: Call<CatByIdList>, response: Response<CatByIdList>) {
                iListener.onSuccess(call, response.body()!!)
            }

            override fun onFailure(call: Call<CatByIdList>, t: Throwable) {
                iListener.onFailure(call, t, "Error")
            }

        })
    }

    fun getCategoryList(iListener: IListener<Categories>) {
        iService.categoriesList().enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                iListener.onSuccess(call, response.body()!!)
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                iListener.onFailure(call, t, "Error")
            }

        })
    }

    fun getHomeWallpaper(iListener: IListener<HomeWallpaper>) {
        iService.home().enqueue(object : Callback<HomeWallpaper> {
            override fun onResponse(call: Call<HomeWallpaper>, response: Response<HomeWallpaper>) {
                iListener.onSuccess(call, response.body()!!)
            }

            override fun onFailure(call: Call<HomeWallpaper>, t: Throwable) {
                iListener.onFailure(call, t, "Error")
            }

        })
    }

    fun getBanner(iListener: IListener<Banner>) {
        iService.banner().enqueue(object : Callback<Banner> {
            override fun onResponse(call: Call<Banner>, response: Response<Banner>) {
                iListener.onSuccess(call, response.body()!!)
            }

            override fun onFailure(call: Call<Banner>, t: Throwable) {
                iListener.onFailure(call, t, "Error")
            }

        })
    }
}