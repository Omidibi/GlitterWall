package com.omid.glitterwall.models.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatByIdList(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val catByIdList: List<AllVideo>
) : Parcelable