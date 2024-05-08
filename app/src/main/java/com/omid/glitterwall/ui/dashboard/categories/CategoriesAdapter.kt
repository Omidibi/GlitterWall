package com.omid.glitterwall.ui.dashboard.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.activity.HomeWidget
import com.omid.glitterwall.R
import com.omid.glitterwall.models.Category
import com.omid.glitterwall.utils.configuration.AppConfiguration
import javax.inject.Inject

class CategoriesAdapter @Inject constructor(private val fragment: Fragment, private val categoryList: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesVH>() {

    inner class CategoriesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCat = itemView.findViewById<CardView>(R.id.cv_cat)!!
        val imgCat = itemView.findViewById<AppCompatImageView>(R.id.img_cat)!!
        val txtTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_title)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        return CategoriesVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_row, null))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.apply {
            val category = categoryList[position]
            txtTitle.text = category.categoryName
            Glide.with(AppConfiguration.getContext())
                .load(category.categoryImageThumb)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(imgCat)

            cvCat.setOnClickListener {
                bundle.putParcelable("categoriesInfo", category)
                fragment.findNavController().navigate(R.id.action_categoriesFragment_to_showImageByCatIdFragment, bundle)
                HomeWidget.bnv.visibility = View.GONE
                HomeWidget.toolbar.visibility = View.GONE
            }
        }
    }
}