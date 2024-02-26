package com.omid.glitterwall.models.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerModel(
    @SerializedName("banner_image")
    val bannerImage: String,
    @SerializedName("banner_image_thumb")
    val bannerImageThumb: String,
    @SerializedName("banner_name")
    val bannerName: String,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("id")
    val id: String
) : Parcelable