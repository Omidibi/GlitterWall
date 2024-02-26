package com.omid.glitterwall.api

import com.omid.glitterwall.models.models.Banner
import com.omid.glitterwall.models.models.CatByIdList
import com.omid.glitterwall.models.models.Categories
import com.omid.glitterwall.models.models.HomeWallpaper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("api.php?home_banner")
    fun banner(): Call<Banner>

    @GET("api.php?home_videos")
    fun home(): Call<HomeWallpaper>

    @GET("api.php?cat_list")
    fun categoriesList(): Call<Categories>

    @GET("api.php?")
    fun wallByCatId(@Query("cat_id") id: String): Call<CatByIdList>
}