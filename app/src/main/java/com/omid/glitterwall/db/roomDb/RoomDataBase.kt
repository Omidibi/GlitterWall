package com.omid.glitterwall.db.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omid.glitterwall.models.models.AllVideo

@Database(entities = [AllVideo::class], version = 1)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun dao(): IDao
}