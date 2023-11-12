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
import com.navin.glitterwall.models.LatestWallpapersModel

class LatestWallpaperAdapter(private val context: Context, private val latestWall: List<LatestWallpapersModel>): RecyclerView.Adapter<LatestWallpaperAdapter.LatestWallVH>() {

    class LatestWallVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestWallVH {
        val view = LayoutInflater.from(context).inflate(R.layout.latest_act_row,null)
        return LatestWallVH(view)
    }

    override fun getItemCount(): Int {
        return latestWall.size
    }

    override fun onBindViewHolder(holder: LatestWallVH, position: Int) {
        holder.apply {
            val latestInfo  = latestWall[position]
            Glide.with(context).load(latestInfo.videoUrl).into(img)
            cvImg.setOnClickListener {
                val intent = Intent(context,ShowImageActivity::class.java)
                intent.putExtra("wall",latestInfo.videoThumbnailB)
                intent.putExtra("id",latestInfo.id)
                intent.putExtra("wallUrl",latestInfo.videoUrl)
                intent.putExtra("title",latestInfo.videoTitle)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}