package com.navin.glitterwall.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.navin.glitterwall.R
import com.navin.glitterwall.activities.ShowImageByCatIdActivity
import com.navin.glitterwall.models.CategoriesModel
import com.navin.glitterwall.util.Font

class CategoriesAdapter(private val context: Context, private val categoriesList : List<CategoriesModel>): RecyclerView.Adapter<CategoriesAdapter.CategoriesVH>() {

    class CategoriesVH(itemView: View): RecyclerView.ViewHolder(itemView){
        val cvCat = itemView.findViewById<CardView>(R.id.cv_cat)!!
        val imgCat = itemView.findViewById<AppCompatImageView>(R.id.img_cat)!!
        val txtTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_title)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
       val view = LayoutInflater.from(context).inflate(R.layout.category_row,null)
        return CategoriesVH(view)
    }

    override fun getItemCount(): Int {
      return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.apply {
            val categoriesInfo = categoriesList[position]
            Glide.with(context).load(categoriesInfo.categoryImageThumb).into(imgCat)
            txtTitle.text = categoriesInfo.categoryName
            Font.categoryAdapter(context,holder)
            cvCat.setOnClickListener {
                val intent = Intent(context,ShowImageByCatIdActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("categoriesInfo",categoriesInfo)
                context.startActivity(intent)
            }
        }
    }
}