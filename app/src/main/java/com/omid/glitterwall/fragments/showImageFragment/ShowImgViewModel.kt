package com.omid.glitterwall.fragments.showImageFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omid.glitterwall.db.roomDb.RoomDBInstance
import com.omid.glitterwall.models.AllVideo

class ShowImgViewModel(application: Application) : AndroidViewModel(application) {

    fun search(id: String): Boolean {
        return RoomDBInstance.roomDbInstance.dao().search(id).isNotEmpty()
    }

    fun saveOrDelete(allVideo: AllVideo) {
        if (search(allVideo.id)) {
            RoomDBInstance.roomDbInstance.dao().deleteGW(allVideo.id)
        } else {
            RoomDBInstance.roomDbInstance.dao().insert(allVideo)
        }
    }
}