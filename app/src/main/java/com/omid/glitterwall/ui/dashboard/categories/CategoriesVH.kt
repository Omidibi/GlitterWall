package com.omid.glitterwall.ui.dashboard.categories

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.glitterwall.R

class CategoriesVH(itemView: View): RecyclerView.ViewHolder(itemView){
    val cvCat = itemView.findViewById<CardView>(R.id.cv_cat)!!
    val imgCat = itemView.findViewById<AppCompatImageView>(R.id.img_cat)!!
    val txtTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_title)!!
}