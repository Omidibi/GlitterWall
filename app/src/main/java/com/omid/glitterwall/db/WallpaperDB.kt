package com.omid.glitterwall.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class WallpaperDB(context: Context?) : SQLiteOpenHelper(context, "Wallpaper.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table tbl_wallpaper(id Integer PRIMARY KEY AUTOINCREMENT,catId Text,categoryImage Text,categoryImageThumb Text," +
                "categoryName Text,cid Text,idWallpaper Text,rateAvg Text,totalViewer Text,videoDescription Text,videoDuration Text," +
                "videoId Text,videoThumbnailB Text,videoThumbnailS Text,videoTitle Text,videoType Text,videoUrl Text)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}