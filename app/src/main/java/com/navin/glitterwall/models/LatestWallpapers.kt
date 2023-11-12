package com.navin.glitterwall.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatestWallpapers(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val latestWallpapers: List<LatestWallpapersModel>
) : Parcelable