package com.omid.glitterwall.api

import com.omid.glitterwall.models.Banner
import com.omid.glitterwall.models.CatByIdList
import com.omid.glitterwall.models.Categories
import com.omid.glitterwall.models.HomeWallpaper
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("api.php?home_banner")
    suspend fun banner(): Response<Banner>

    @GET("api.php?home_videos")
    suspend fun home(): Response<HomeWallpaper>

    @GET("api.php?cat_list")
    fun categoriesList(): Observable<Categories>

    @GET("api.php?")
    fun wallByCatId(@Query("cat_id") id: String): Observable<CatByIdList>
}