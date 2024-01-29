package com.omid.glitterwall.ui.main.home

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.glitterwall.R

class FeaturedVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
    val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
}