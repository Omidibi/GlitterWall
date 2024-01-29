package com.omid.glitterwall.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.databinding.FragmentFavoriteWallpaperBinding
import com.omid.glitterwall.db.WallpaperDB
import com.omid.glitterwall.db.WallpaperDBAdapter

class FavoriteWallpaperFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteWallpaperBinding
    private lateinit var wallpaperDB: WallpaperDB
    private lateinit var wallpaperDBAdapter: WallpaperDBAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentFavoriteWallpaperBinding.inflate(layoutInflater)
        binding.apply {
            wallpaperDB = WallpaperDB(requireContext())
            wallpaperDBAdapter = WallpaperDBAdapter(requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            rvFvt.adapter = FavoriteWallpaperAdapter(wallpaperDBAdapter.showWallpaper())
            rvFvt.layoutManager = GridLayoutManager(requireContext(), 2)

            if (wallpaperDBAdapter.showWallpaper().isEmpty()) {
                showState.visibility = View.VISIBLE
                rvFvt.visibility = View.GONE
            } else {
                showState.visibility = View.GONE
                rvFvt.visibility = View.VISIBLE
            }
        }
    }
}