package com.omid.glitterwall.ui.dashboard.favorite

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

class FavoriteWallpaperAdapter(private val fragment: Fragment, private val fvtList: MutableList<AllVideo>) : RecyclerView.Adapter<FavoriteWallpaperAdapter.FvtWallpaperVH>() {

    inner class FvtWallpaperVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvFvt = itemView.findViewById<CardView>(R.id.cv_fvt)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FvtWallpaperVH {
        return FvtWallpaperVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.fvt_row, null))
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
                bundle.putParcelable("allVideo", fvtInfo)
                fragment.findNavController().navigate(R.id.action_favoriteWallpaperFragment_to_showImageFragment, bundle)
                HomeWidget.bnv.visibility = View.GONE
                HomeWidget.toolbar.visibility = View.GONE
            }
        }
    }
}