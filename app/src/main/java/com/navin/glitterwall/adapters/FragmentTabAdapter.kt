package com.navin.glitterwall.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentTabAdapter(fragmentActivity: FragmentActivity, private var fragmentsList: MutableList<Fragment>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
       return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
       return fragmentsList[position]
    }
}