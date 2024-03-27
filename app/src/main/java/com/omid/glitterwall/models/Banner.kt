package com.omid.glitterwall.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val banner: List<BannerModel>
) : Parcelable