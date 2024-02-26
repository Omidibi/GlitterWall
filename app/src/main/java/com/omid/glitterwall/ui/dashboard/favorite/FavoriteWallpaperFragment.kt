package com.omid.glitterwall.ui.dashboard.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.databinding.FragmentFavoriteWallpaperBinding
import com.omid.glitterwall.db.RoomDBInstance

class FavoriteWallpaperFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteWallpaperBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentFavoriteWallpaperBinding.inflate(layoutInflater)
        binding.apply {

        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {

            rvFvt.adapter = FavoriteWallpaperAdapter(RoomDBInstance.roomDbInstance.dao().showAllWallpaper())
            rvFvt.layoutManager = GridLayoutManager(requireContext(),2)

            if (RoomDBInstance.roomDbInstance.dao().showAllWallpaper().isEmpty()){
                showState.visibility = View.VISIBLE
                rvFvt.visibility = View.GONE
            }else {
                showState.visibility = View.GONE
                rvFvt.visibility = View.VISIBLE
            }
        }
    }
}