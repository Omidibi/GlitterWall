package com.omid.glitterwall.ui.main.categories

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.R
import com.omid.glitterwall.activities.showImageByCatIdActivity.ShowImageByCatIdActivity
import com.omid.glitterwall.model.models.Category
import com.omid.glitterwall.util.configuration.AppConfiguration

class CategoriesAdapter(private val categoriesList : List<Category>): RecyclerView.Adapter<CategoriesVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
       val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_row,null)
        return CategoriesVH(view)
    }

    override fun getItemCount(): Int {
      return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.apply {
            val categoriesInfo = categoriesList[position]
            Glide.with(AppConfiguration.getContext())
                .load(categoriesInfo.categoryImageThumb)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(imgCat)
            txtTitle.text = categoriesInfo.categoryName
            cvCat.setOnClickListener {
                val intent = Intent(AppConfiguration.getContext(), ShowImageByCatIdActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("categoriesInfo",categoriesInfo)
                AppConfiguration.getContext().startActivity(intent)
            }
        }
    }
}