package com.omid.glitterwall.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeWallpaper(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val homeWallpaper: Home
) : Parcelable