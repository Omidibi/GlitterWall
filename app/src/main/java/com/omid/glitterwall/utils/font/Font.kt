package com.omid.glitterwall.utils.font

import android.graphics.Typeface
import com.omid.glitterwall.databinding.ActivityMainBinding
import com.omid.glitterwall.databinding.ActivityShowImageBinding
import com.omid.glitterwall.databinding.FragmentHomeBinding
import com.omid.glitterwall.ui.dashboard.categories.CategoriesVH
import com.omid.glitterwall.utils.configuration.AppConfiguration

class Font {
    companion object{
        fun homeFragment(binding: FragmentHomeBinding){
            binding.apply {
                val typeface = Typeface.createFromAsset(AppConfiguration.getContext().assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                txtShow.typeface = typeface
                txtLatest.typeface = typeface
                txtFeatured.typeface = typeface
            }
        }

        fun mainActivity(binding: ActivityMainBinding){
            binding.apply {
                val typeface = Typeface.createFromAsset(AppConfiguration.getContext().assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                titleToolbar.typeface = typeface
            }
        }

        fun categoryAdapter(holder : CategoriesVH){
            holder.apply {
                val typeface = Typeface.createFromAsset(AppConfiguration.getContext().assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                txtTitle.typeface = typeface
            }
        }

        fun showImageActivity(binding: ActivityShowImageBinding){
            binding.apply {
                val typeface = Typeface.createFromAsset(AppConfiguration.getContext().assets,"Fonts/IRANSans/iran_sans_mobile.ttf")
                btnDownload.typeface = typeface
                btnSetWallpaper.typeface = typeface
            }
        }
    }
}