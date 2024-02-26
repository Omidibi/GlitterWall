package com.omid.glitterwall.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.omid.glitterwall.models.models.AllVideo

@Dao
interface IDao {

    @Insert
    fun insert(vararg allVideo: AllVideo)

    @Query("Select * From tbl_glitterWall")
    fun showAllWallpaper(): MutableList<AllVideo>

    @Delete
    fun delete(allVideo: AllVideo)

    @Query("Delete From tbl_glitterWall Where id Like :id")
    fun deleteGW(id: String): Int

    @Query("Select * From tbl_glitterWall Where id Like :id")
    fun search(id: String): MutableList<AllVideo>

}