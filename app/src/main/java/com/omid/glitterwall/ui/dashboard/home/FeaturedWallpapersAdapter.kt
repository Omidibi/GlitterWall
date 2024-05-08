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

class FeaturedWallpapersAdapter(private val fragment: Fragment, private val featuredList: List<AllVideo>) : RecyclerView.Adapter<FeaturedWallpapersAdapter.FeaturedVH>() {

    inner class FeaturedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedVH {
        return FeaturedVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.featured_row, null))
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
                bundle.putParcelable("allVideo", featuredInfo)
                fragment.findNavController().navigate(R.id.action_homeFragment_to_showImageFragment, bundle)
                HomeWidget.bnv.visibility = View.GONE
                HomeWidget.toolbar.visibility = View.GONE
            }
        }
    }
}