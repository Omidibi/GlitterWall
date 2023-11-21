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
import com.navin.glitterwall.models.AllVideo

class LatestWallpapersAdapter(private val context: Context, private val latest: List<AllVideo>) : RecyclerView.Adapter<LatestWallpapersAdapter.LatestWallpapersVH>() {

    class LatestWallpapersVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
        val cvImg = itemView.findViewById<CardView>(R.id.cv_img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestWallpapersVH {
        val view = LayoutInflater.from(context).inflate(R.layout.latest_row,null)
        return LatestWallpapersVH(view)
    }

    override fun getItemCount(): Int {
        return latest.size
    }

    override fun onBindViewHolder(holder: LatestWallpapersVH, position: Int) {
        holder.apply {
            val latestInfo = latest[position]
            Glide.with(context)
                .load(latestInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)

            cvImg.setOnClickListener {
                val intent = Intent(context,ShowImageActivity::class.java)
                intent.putExtra("allVideo",latestInfo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}