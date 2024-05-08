package com.omid.glitterwall.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.activity.HomeWidget
import com.omid.glitterwall.R
import com.omid.glitterwall.models.AllVideo
import com.omid.glitterwall.utils.configuration.AppConfiguration

class LatestWallpapersAdapter(private val fragment: Fragment, private val latest: List<AllVideo>) : RecyclerView.Adapter<LatestWallpapersAdapter.LatestWallpapersVH>() {

    inner class LatestWallpapersVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
        val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestWallpapersVH {
        return LatestWallpapersVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.latest_row, null))
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
                bundle.putParcelable("allVideo", latestInfo)
                fragment.findNavController().navigate(R.id.action_homeFragment_to_showImageFragment, bundle)
                HomeWidget.bnv.visibility = View.GONE
                HomeWidget.toolbar.visibility = View.GONE
            }
        }
    }
}