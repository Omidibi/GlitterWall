package com.navin.glitterwall.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllWallpapers(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val allWallpapers: List<AllWallpapersModel>
) : Parcelable