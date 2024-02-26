package com.omid.glitterwall.models.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity("tbl_glitterWall")
@Parcelize
data class AllVideo(
    @PrimaryKey(autoGenerate = true)
    val idGlitter: Int = 0,
    @SerializedName("cat_id")
    @ColumnInfo("catId")
    val catId: String,
    @SerializedName("category_image")
    @ColumnInfo("categoryImage")
    val categoryImage: String,
    @SerializedName("category_image_thumb")
    @ColumnInfo("categoryImageThumb")
    val categoryImageThumb: String,
    @SerializedName("category_name")
    @ColumnInfo("categoryName")
    val categoryName: String,
    @SerializedName("cid")
    @ColumnInfo("cid")
    val cid: String,
    @SerializedName("id")
    @ColumnInfo("id")
    val id: String,
    @SerializedName("rate_avg")
    @ColumnInfo("rateAvg")
    val rateAvg: String,
    @SerializedName("totel_viewer")
    @ColumnInfo("totalViewer")
    val totalViewer: String,
    @SerializedName("video_description")
    @ColumnInfo("videoDescription")
    val videoDescription: String,
    @SerializedName("video_duration")
    @ColumnInfo("videoDuration")
    val videoDuration: String,
    @SerializedName("video_id")
    @ColumnInfo("videoId")
    val videoId: String,
    @SerializedName("video_thumbnail_b")
    @ColumnInfo("videoThumbnailB")
    val videoThumbnailB: String,
    @SerializedName("video_thumbnail_s")
    @ColumnInfo("videoThumbnailS")
    val videoThumbnailS: String,
    @SerializedName("video_title")
    @ColumnInfo("videoTitle")
    val videoTitle: String,
    @SerializedName("video_type")
    @ColumnInfo("videoType")
    val videoType: String,
    @SerializedName("video_url")
    @ColumnInfo("videoUrl")
    val videoUrl: String
) : Parcelable