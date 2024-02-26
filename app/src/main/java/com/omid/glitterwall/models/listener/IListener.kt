package com.omid.glitterwall.models.listener

import retrofit2.Call

interface IListener<T> {

    fun onSuccess(call: Call<T>, response: T)

    fun onFailure(call: Call<T>, t: Throwable, errorResponse: String)

}