package com.omid.glitterwall.ui.dashboard.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.R
import com.omid.glitterwall.activities.showImageActivity.ShowImageActivity
import com.omid.glitterwall.models.models.AllVideo
import com.omid.glitterwall.utils.configuration.AppConfiguration

class LatestWallpapersAdapter(private val latest: List<AllVideo>) : RecyclerView.Adapter<LatestWallpapersVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestWallpapersVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.latest_row,null)
        return LatestWallpapersVH(view)
    }

    override fun getItemCount(): Int {
        return latest.size
    }

    override fun onBindViewHolder(holder: LatestWallpapersVH, position: Int) {
        holder.apply {
            val latestInfo = latest[position]
            Glide.with(AppConfiguration.getContext())
                .load(latestInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)

            cvImg.setOnClickListener {
                val intent = Intent(AppConfiguration.getContext(), ShowImageActivity::class.java)
                intent.putExtra("allVideo",latestInfo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                AppConfiguration.getContext().startActivity(intent)
            }
        }
    }
}