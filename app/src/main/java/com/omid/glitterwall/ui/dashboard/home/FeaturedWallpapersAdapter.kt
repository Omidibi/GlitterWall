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

class FeaturedWallpapersAdapter(private val featuredList : List<AllVideo>) : RecyclerView.Adapter<FeaturedVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.featured_row,null)
        return FeaturedVH(view)
    }

    override fun getItemCount(): Int {
        return featuredList.size
    }

    override fun onBindViewHolder(holder: FeaturedVH, position: Int) {
        holder.apply {
            val featuredInfo = featuredList[position]
            Glide.with(AppConfiguration.getContext())
                .load(featuredInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)
            cvImg.setOnClickListener {
                val intent = Intent(AppConfiguration.getContext(), ShowImageActivity::class.java)
                intent.putExtra("allVideo",featuredInfo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                AppConfiguration.getContext().startActivity(intent)
            }
        }
    }
}