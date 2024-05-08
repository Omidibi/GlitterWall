package com.omid.glitterwall.ui.dashboard.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.activity.HomeWidget
import com.omid.glitterwall.R
import com.omid.glitterwall.models.Category
import com.omid.glitterwall.utils.configuration.AppConfiguration
import javax.inject.Inject

class CategoriesAdapter @Inject constructor(private val fragment: Fragment, private val categoryList: List<Category>) : RecyclerView.Adapter<CategoriesVH>() {

    private lateinit var bundle: Bundle

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_row, null)
        return CategoriesVH(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.apply {
            val category = categoryList[position]
            txtTitle.text = category.categoryName
            bundle = Bundle()

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