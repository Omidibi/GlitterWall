package com.omid.glitterwall.ui.dashboard.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.omid.glitterwall.R
import com.omid.glitterwall.models.BannerModel
import com.omid.glitterwall.utils.configuration.AppConfiguration

class BannerAdapter(private var bannerList: List<BannerModel>) : PagerAdapter() {
    override fun getCount(): Int {
        return bannerList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.banner_row, null)
        val img = view.findViewById<AppCompatImageView>(R.id.img_banner)
        container.addView(view, 0)
        val bannerImage = bannerList[position]
        Glide.with(AppConfiguration.getContext())
            .load(bannerImage.bannerImage)
            .placeholder(R.drawable.coming)
            .error(R.drawable.error2)
            .into(img)
        return view
    }
}