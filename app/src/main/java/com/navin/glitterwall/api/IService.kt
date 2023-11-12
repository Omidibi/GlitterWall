package com.navin.glitterwall.api

import com.navin.glitterwall.models.AllWallpapers
import com.navin.glitterwall.models.Banner
import com.navin.glitterwall.models.CatByIdList
import com.navin.glitterwall.models.Categories
import com.navin.glitterwall.models.HomeWallpaper
import com.navin.glitterwall.models.LatestWallpapers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("api.php?home_banner")
    fun banner() : Call<Banner>

    @GET("api.php?home_videos")
    fun home(): Call<HomeWallpaper>

    @GET("api.php?cat_list")
    fun categoriesList(): Call<Categories>

    @GET("api.php?")
    fun wallByCatId(@Query("cat_id") id: String): Call<CatByIdList>

    @GET("api.php?All_videos")
    fun allWallpapers(): Call<AllWallpapers>

    @GET("api.php?latest_video")
    fun latestWallpaper() : Call<LatestWallpapers>
}