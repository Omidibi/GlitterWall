package com.omid.glitterwall.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.HomeWidget
import com.omid.glitterwall.R
import com.omid.glitterwall.models.AllVideo
import com.omid.glitterwall.utils.configuration.AppConfiguration

class LatestWallpapersAdapter(private val fragment: Fragment,private val latest: List<AllVideo>) : RecyclerView.Adapter<LatestWallpapersVH>() {

    private lateinit var bundle: Bundle

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
            bundle = Bundle()

            Glide.with(AppConfiguration.getContext())
                .load(latestInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)

            cvImg.setOnClickListener {
                bundle.putParcelable("allVideo",latestInfo)
                fragment.findNavController().navigate(R.id.action_homeFragment_to_showImageFragment,bundle)
                HomeWidget.bnv.visibility = View.GONE
                HomeWidget.toolbar.visibility = View.GONE
            }
        }
    }
}