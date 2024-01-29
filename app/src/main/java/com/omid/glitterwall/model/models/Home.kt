package com.omid.glitterwall.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Home(
    @SerializedName("all_video")
    val allWallpaper: List<AllVideo>,
    @SerializedName("category")
    val categoryWallpaper: List<Category>,
    @SerializedName("featured_video")
    val featuredWallpaper: List<AllVideo>,
    @SerializedName("latest_video")
    val latestWallpaper: List<AllVideo>
) : Parcelable