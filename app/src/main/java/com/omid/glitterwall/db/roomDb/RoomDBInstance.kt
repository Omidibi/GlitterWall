package com.omid.glitterwall.db.roomDb

import androidx.room.Room
import com.omid.glitterwall.utils.configuration.AppConfiguration

object RoomDBInstance {

    val roomDbInstance = Room.databaseBuilder(AppConfiguration.getContext(), RoomDataBase::class.java, "tbl_glitterWall")
        .allowMainThreadQueries()
        .build()
}