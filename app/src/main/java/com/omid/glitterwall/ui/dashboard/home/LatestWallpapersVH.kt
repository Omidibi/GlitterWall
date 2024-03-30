package com.omid.glitterwall.ui.dashboard.home

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.glitterwall.R

class LatestWallpapersVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
}