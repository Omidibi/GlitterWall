package com.navin.glitterwall.util.font

import android.content.Context
import android.graphics.Typeface
import com.navin.glitterwall.adapters.CategoriesAdapter
import com.navin.glitterwall.databinding.ActivityMainBinding
import com.navin.glitterwall.databinding.ActivityShowImageBinding
import com.navin.glitterwall.databinding.FragmentHomeBinding

class Font {
    companion object{
        fun homeFragment(context: Context,binding: FragmentHomeBinding){
            binding.apply {
                val typeface = Typeface.createFromAsset(context.assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                txtShow.typeface = typeface
                txtLatest.typeface = typeface
                txtFeatured.typeface = typeface
            }
        }

        fun mainActivity(context: Context,binding: ActivityMainBinding){
            binding.apply {
                val typeface = Typeface.createFromAsset(context.assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                titleToolbar.typeface = typeface
            }
        }

        fun categoryAdapter(context: Context, holder : CategoriesAdapter.CategoriesVH){
            holder.apply {
                val typeface = Typeface.createFromAsset(context.assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                txtTitle.typeface = typeface
            }
        }

        fun showImageActivity(context: Context,binding: ActivityShowImageBinding){
            binding.apply {
                val typeface = Typeface.createFromAsset(context.assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                btnDownload.typeface = typeface
                btnSetWallpaper.typeface = typeface
            }
        }
    }
}