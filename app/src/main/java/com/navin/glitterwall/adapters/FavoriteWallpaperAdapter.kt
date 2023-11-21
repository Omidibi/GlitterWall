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

class FavoriteWallpaperAdapter(private val context: Context, private val fvtList: MutableList<AllVideo>): RecyclerView.Adapter<FavoriteWallpaperAdapter.FvtWallpaperVH>() {

    class FvtWallpaperVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvFvt = itemView.findViewById<CardView>(R.id.cv_fvt)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FvtWallpaperVH {
        val view = LayoutInflater.from(context).inflate(R.layout.fvt_row, null)
        return FvtWallpaperVH(view)
    }

    override fun getItemCount(): Int {
        return fvtList.size
    }

    override fun onBindViewHolder(holder: FvtWallpaperVH, position: Int) {
        holder.apply {
            val fvtInfo = fvtList[position]
            Glide.with(context)
                .load(fvtInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)
            cvFvt.setOnClickListener {
                val intent = Intent(context,ShowImageActivity::class.java)
                intent.putExtra("allVideo",fvtInfo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}