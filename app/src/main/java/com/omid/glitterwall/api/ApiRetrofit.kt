package com.omid.glitterwall.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {
    val retrofit : Retrofit = Retrofit.Builder().baseUrl("http://mobilemasters.ir/apps/glitterwall/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}