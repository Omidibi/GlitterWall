package com.omid.glitterwall.ui.dashboard.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.databinding.FragmentFavoriteWallpaperBinding

class FavoriteWallpaperFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteWallpaperBinding
    private lateinit var fvtViewModel: FvtViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBindingAndViewModel()
        return binding.root
    }

    private fun setupBindingAndViewModel() {
        binding = FragmentFavoriteWallpaperBinding.inflate(layoutInflater)
        fvtViewModel = ViewModelProvider(this)[FvtViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            rvFvt.adapter = FavoriteWallpaperAdapter(this@FavoriteWallpaperFragment, fvtViewModel.showAllWallpaper())
            rvFvt.layoutManager = GridLayoutManager(requireContext(), 2)
            if (fvtViewModel.isEmptyShowAllWallpaper()) {
                showState.visibility = View.VISIBLE
                rvFvt.visibility = View.GONE
            } else {
                showState.visibility = View.GONE
                rvFvt.visibility = View.VISIBLE
            }
        }
    }
}