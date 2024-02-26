package com.omid.glitterwall.db

import android.content.Context

class WallpaperDBAdapter(context: Context) : WallpaperDB(context) {

   /* fun insertWallpaper(allVideo: AllVideo): Long {
        val db: SQLiteDatabase = writableDatabase
        val content: ContentValues = ContentValues()
        content.put("catId", allVideo.catId)
        content.put("categoryImage", allVideo.categoryImage)
        content.put("categoryImageThumb", allVideo.categoryImageThumb)
        content.put("categoryName", allVideo.categoryName)
        content.put("cid", allVideo.cid)
        content.put("idWallpaper", allVideo.id)
        content.put("rateAvg", allVideo.rateAvg)
        content.put("totalViewer", allVideo.totalViewer)
        content.put("videoDescription", allVideo.videoDescription)
        content.put("videoDuration", allVideo.videoDuration)
        content.put("videoId", allVideo.videoId)
        content.put("videoThumbnailB", allVideo.videoThumbnailB)
        content.put("videoThumbnailS", allVideo.videoThumbnailS)
        content.put("videoTitle", allVideo.videoTitle)
        content.put("videoType", allVideo.videoType)
        content.put("videoUrl", allVideo.videoUrl)
        return db.insert("tbl_wallpaper", null, content)
    }

    fun showWallpaper(): MutableList<AllVideo> {
        val db: SQLiteDatabase = readableDatabase
        val query = "select * from tbl_wallpaper"
        val wallpapers = mutableListOf<AllVideo>()
        val cursor: Cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val catId = cursor.getString(1)
            val categoryImage = cursor.getString(2)
            val categoryImageThumb = cursor.getString(3)
            val categoryName = cursor.getString(4)
            val cid = cursor.getString(5)
            val idWallpaper = cursor.getString(6)
            val rateAvg = cursor.getString(7)
            val totalViewer = cursor.getString(8)
            val videoDescription = cursor.getString(9)
            val videoDuration = cursor.getString(10)
            val videoId = cursor.getString(11)
            val videoThumbnailB = cursor.getString(12)
            val videoThumbnailS = cursor.getString(13)
            val videoTitle = cursor.getString(14)
            val videoType = cursor.getString(15)
            val videoUrl = cursor.getString(16)
            wallpapers.add(
                AllVideo(catId, categoryImage, categoryImageThumb, categoryName, cid,
                    idWallpaper, rateAvg, totalViewer, videoDescription, videoDuration, videoId,
                    videoThumbnailB, videoThumbnailS, videoTitle, videoType, videoUrl)
            )
        }
        return wallpapers
    }

    fun deleteWallpaper(wallpaperId : Int): Int{
        val db : SQLiteDatabase = writableDatabase
        return db.delete("tbl_wallpaper","idWallpaper=$wallpaperId",null)
    }

    fun search(id : Int): MutableList<AllVideo>{
        val db : SQLiteDatabase = readableDatabase
        val query = "select * from tbl_wallpaper where idWallpaper like '%$id%'"
        val result = mutableListOf<AllVideo>()
        val cursor : Cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val catId = cursor.getString(1)
            val categoryImage = cursor.getString(2)
            val categoryImageThumb = cursor.getString(3)
            val categoryName = cursor.getString(4)
            val cid = cursor.getString(5)
            val idWallpaper = cursor.getString(6)
            val rateAvg = cursor.getString(7)
            val totalViewer = cursor.getString(8)
            val videoDescription = cursor.getString(9)
            val videoDuration = cursor.getString(10)
            val videoId = cursor.getString(11)
            val videoThumbnailB = cursor.getString(12)
            val videoThumbnailS = cursor.getString(13)
            val videoTitle = cursor.getString(14)
            val videoType = cursor.getString(15)
            val videoUrl = cursor.getString(16)
            result.add(
                AllVideo(catId, categoryImage, categoryImageThumb, categoryName, cid,
                    idWallpaper, rateAvg, totalViewer, videoDescription, videoDuration, videoId,
                    videoThumbnailB, videoThumbnailS, videoTitle, videoType, videoUrl)
            )
        }
        return result
    }*/
}