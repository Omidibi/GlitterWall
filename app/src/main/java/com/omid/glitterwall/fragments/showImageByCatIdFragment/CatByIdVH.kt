package com.omid.glitterwall.fragments.showImageByCatIdFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.glitterwall.R

class CatByIdVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val cvCat = itemView.findViewById<CardView>(R.id.cv_cat)!!
    val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
}