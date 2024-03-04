package com.omid.glitterwall.ui.dashboard.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omid.glitterwall.db.roomDb.RoomDBInstance
import com.omid.glitterwall.models.models.AllVideo

class FvtViewModel(application: Application) : AndroidViewModel(application) {

    fun isEmptyShowAllWallpaper(): Boolean {
        return RoomDBInstance.roomDbInstance.dao().showAllWallpaper().isEmpty()
    }

    fun showAllWallpaper(): MutableList<AllVideo> {
        return RoomDBInstance.roomDbInstance.dao().showAllWallpaper()
    }
}