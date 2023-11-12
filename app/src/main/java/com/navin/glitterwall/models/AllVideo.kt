package com.navin.glitterwall.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllVideo(
    @SerializedName("cat_id")
    val catId: String,
    @SerializedName("category_image")
    val categoryImage: String,
    @SerializedName("category_image_thumb")
    val categoryImageThumb: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("cid")
    val cid: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("rate_avg")
    val rateAvg: String,
    @SerializedName("totel_viewer")
    val totalViewer: String,
    @SerializedName("video_description")
    val videoDescription: String,
    @SerializedName("video_duration")
    val videoDuration: String,
    @SerializedName("video_id")
    val videoId: String,
    @SerializedName("video_thumbnail_b")
    val videoThumbnailB: String,
    @SerializedName("video_thumbnail_s")
    val videoThumbnailS: String,
    @SerializedName("video_title")
    val videoTitle: String,
    @SerializedName("video_type")
    val videoType: String,
    @SerializedName("video_url")
    val videoUrl: String
) : Parcelable