package com.navin.glitterwall.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.navin.glitterwall.R
import com.navin.glitterwall.models.BannerModel

class BannerAdapter(private var context: Context, private var bannerList: List<BannerModel>) : PagerAdapter() {
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
        val view = LayoutInflater.from(context).inflate(R.layout.banner_row, null)
        val imgNews = view.findViewById<AppCompatImageView>(R.id.img_banner)
        container.addView(view, 0)
        val bannerImage = bannerList[position]
        Glide.with(context).load(bannerImage.bannerImageThumb).into(imgNews)
        return view
    }
}