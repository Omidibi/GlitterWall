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
import com.navin.glitterwall.models.CatByIdListModel

class CatByIdAdapter(private val context: Context, private val categoriesModel: List<CatByIdListModel>) : RecyclerView.Adapter<CatByIdAdapter.CatByIdVH>() {

    class CatByIdVH (itemView: View) : RecyclerView.ViewHolder(itemView){
        val cvCat = itemView.findViewById<CardView>(R.id.cv_cat)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatByIdVH {
        val view = LayoutInflater.from(context).inflate(R.layout.cat_by_id_row,null)
        return CatByIdVH(view)
    }

    override fun getItemCount(): Int {
        return categoriesModel.size
    }

    override fun onBindViewHolder(holder: CatByIdVH, position: Int) {
        holder.apply {
            val categoriesModelInfo = categoriesModel[position]
            Glide.with(context).load(categoriesModelInfo.videoThumbnailB).into(img)
            cvCat.setOnClickListener {
                val intent = Intent(context, ShowImageActivity::class.java)
                intent.putExtra("wall",categoriesModelInfo.videoThumbnailB)
                intent.putExtra("id",categoriesModelInfo.id)
                intent.putExtra("wallUrl",categoriesModelInfo.videoUrl)
                intent.putExtra("title",categoriesModelInfo.videoTitle)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}