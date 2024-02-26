package com.omid.glitterwall.ui.dashboard.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.R
import com.omid.glitterwall.activities.showImageActivity.ShowImageActivity
import com.omid.glitterwall.models.models.AllVideo
import com.omid.glitterwall.utils.configuration.AppConfiguration

class FavoriteWallpaperAdapter(private val fvtList: MutableList<AllVideo>): RecyclerView.Adapter<FvtWallpaperVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FvtWallpaperVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.fvt_row, null)
        return FvtWallpaperVH(view)
    }

    override fun getItemCount(): Int {
        return fvtList.size
    }

    override fun onBindViewHolder(holder: FvtWallpaperVH, position: Int) {
        holder.apply {
            val fvtInfo = fvtList[position]
            Glide.with(AppConfiguration.getContext())
                .load(fvtInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)
            cvFvt.setOnClickListener {
                val intent = Intent(AppConfiguration.getContext(), ShowImageActivity::class.java)
                intent.putExtra("allVideo",fvtInfo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                AppConfiguration.getContext().startActivity(intent)
            }
        }
    }
}