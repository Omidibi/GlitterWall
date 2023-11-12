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
import com.navin.glitterwall.models.AllWallpapersModel

class WallpapersAdapter(private val context: Context,private val allWallpapers : List<AllWallpapersModel>) : RecyclerView.Adapter<WallpapersAdapter.WallpapersVH>() {

    class WallpapersVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersVH {
        val view = LayoutInflater.from(context).inflate(R.layout.all_wallpapers_row,null)
        return  WallpapersVH(view)
    }

    override fun getItemCount(): Int {
        return allWallpapers.size
    }

    override fun onBindViewHolder(holder: WallpapersVH, position: Int) {
        holder.apply {
            val allInfo = allWallpapers[position]
            Glide.with(context).load(allInfo.videoThumbnailB).into(img)
            cvImg.setOnClickListener {
                val intent = Intent(context, ShowImageActivity::class.java)
                intent.putExtra("wall",allInfo.videoThumbnailB)
                intent.putExtra("id",allInfo.id)
                intent.putExtra("wallUrl",allInfo.videoUrl)
                intent.putExtra("title",allInfo.videoTitle)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}