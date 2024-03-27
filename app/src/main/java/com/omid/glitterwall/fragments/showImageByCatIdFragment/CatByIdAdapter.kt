package com.omid.glitterwall.fragments.showImageByCatIdFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.glitterwall.HomeWidget
import com.omid.glitterwall.R
import com.omid.glitterwall.models.AllVideo
import com.omid.glitterwall.utils.configuration.AppConfiguration

class CatByIdAdapter(private val fragment: Fragment, private val categoriesModel: List<AllVideo>) : RecyclerView.Adapter<CatByIdVH>() {

    private lateinit var bundle: Bundle

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
            bundle = Bundle()

            Glide.with(AppConfiguration.getContext())
                .load(categoriesModelInfo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)

            cvCat.setOnClickListener {
                bundle.putParcelable("allVideo",categoriesModelInfo)
                fragment.findNavController().navigate(R.id.action_showImageByCatIdFragment3_to_showImageFragment,bundle)
                HomeWidget.bnv.visibility = View.GONE
                HomeWidget.toolbar.visibility = View.GONE
            }
        }
    }
}