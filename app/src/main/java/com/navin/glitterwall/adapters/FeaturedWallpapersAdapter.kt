package com.navin.glitterwall.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.navin.glitterwall.R
import com.navin.glitterwall.activities.ShowImageActivity
import com.navin.glitterwall.models.FeaturedVideo

class FeaturedWallpapersAdapter(private val context: Context, private val featuredList : List<FeaturedVideo>) : RecyclerView.Adapter<FeaturedWallpapersAdapter.FeaturedVH>() {

    class FeaturedVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedVH {
        val view = LayoutInflater.from(context).inflate(R.layout.featured_row,null)
        return FeaturedVH(view)
    }

    override fun getItemCount(): Int {
        return featuredList.size
    }

    override fun onBindViewHolder(holder: FeaturedVH, position: Int) {
        holder.apply {
            val featuredInfo = featuredList[position]
            Glide.with(context).load(featuredInfo.videoThumbnailB).into(img)
            cvImg.setOnClickListener {
                val intent = Intent(context,ShowImageActivity::class.java)
                intent.putExtra("wall",featuredInfo.videoThumbnailB)
                intent.putExtra("id",featuredInfo.id)
                intent.putExtra("wallUrl",featuredInfo.videoUrl)
                intent.putExtra("title",featuredInfo.videoTitle)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}