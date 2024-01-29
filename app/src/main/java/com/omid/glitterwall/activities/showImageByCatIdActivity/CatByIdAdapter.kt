package com.omid.glitterwall.activities.showImageByCatIdActivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.R
import com.omid.glitterwall.activities.showImageActivity.ShowImageActivity
import com.omid.glitterwall.model.models.AllVideo
import com.omid.glitterwall.util.configuration.AppConfiguration

class CatByIdAdapter(private val categoriesModel: List<AllVideo>) : RecyclerView.Adapter<CatByIdVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatByIdVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.cat_by_id_row,null)
        return CatByIdVH(view)
    }

    override fun getItemCount(): Int {
        return categoriesModel.size
    }

    override fun onBindViewHolder(holder: CatByIdVH, position: Int) {
        holder.apply {
            val categoriesModelInfo = categoriesModel[position]
            Glide.with(AppConfiguration.getContext())
                .load(categoriesModelInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)
            cvCat.setOnClickListener {
                val intent = Intent(AppConfiguration.getContext(), ShowImageActivity::class.java)
                intent.putExtra("allVideo",categoriesModelInfo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                AppConfiguration.getContext().startActivity(intent)
            }
        }
    }
}