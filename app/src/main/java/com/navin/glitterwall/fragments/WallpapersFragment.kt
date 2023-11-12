package com.navin.glitterwall.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.navin.glitterwall.adapters.WallpapersAdapter
import com.navin.glitterwall.api.ApiRetrofit
import com.navin.glitterwall.api.IService
import com.navin.glitterwall.databinding.FragmentWallpapersBinding
import com.navin.glitterwall.models.AllWallpapers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallpapersFragment : Fragment() {
    private lateinit var binding: FragmentWallpapersBinding
    private lateinit var iService: IService
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWallpapersBinding.inflate(layoutInflater)
        binding.apply {
            Log.e("","")
            iService = ApiRetrofit.retrofit.create(IService::class.java)
            allWallpapers()

            srl.setOnRefreshListener {
                srlAllWallpapers()
                srl.isRefreshing = false
            }
        }
        return binding.root
    }

    private fun allWallpapers(){
        binding.apply {
            pbWall.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvWallpapers.visibility = View.GONE
            iService.allWallpapers().enqueue(object : Callback<AllWallpapers>{
                override fun onResponse(call: Call<AllWallpapers>, response: Response<AllWallpapers>) {
                    pbWall.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvWallpapers.visibility = View.VISIBLE
                    Log.e("","")
                    rvWallpapers.adapter = WallpapersAdapter(requireContext(),response.body()?.allWallpapers!!)
                    rvWallpapers.layoutManager = GridLayoutManager(requireContext(),2)
                }

                override fun onFailure(call: Call<AllWallpapers>, t: Throwable) {
                    pbWall.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    rvWallpapers.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    Log.e("","")
                    btnTry.setOnClickListener {
                        tryAgainAllWallpapers()
                    }
                }

            })
        }
    }

    private fun tryAgainAllWallpapers(){
        binding.apply {
            clNoConnection.visibility = View.GONE
            pbWall.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvWallpapers.visibility = View.GONE
            iService.allWallpapers().enqueue(object : Callback<AllWallpapers>{
                override fun onResponse(call: Call<AllWallpapers>, response: Response<AllWallpapers>) {
                    pbWall.visibility = View.GONE
                    clNoConnection.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvWallpapers.visibility = View.VISIBLE
                    Log.e("","")
                    rvWallpapers.adapter = WallpapersAdapter(requireContext(),response.body()?.allWallpapers!!)
                    rvWallpapers.layoutManager = GridLayoutManager(requireContext(),2)
                }

                override fun onFailure(call: Call<AllWallpapers>, t: Throwable) {
                    pbWall.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    rvWallpapers.visibility = View.GONE
                    Log.e("","")
                }

            })
        }
    }

    private fun srlAllWallpapers(){
        binding.apply {
            pbWall.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvWallpapers.visibility = View.GONE
            iService.allWallpapers().enqueue(object : Callback<AllWallpapers>{
                override fun onResponse(call: Call<AllWallpapers>, response: Response<AllWallpapers>) {
                    pbWall.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvWallpapers.visibility = View.VISIBLE
                    Log.e("","")
                    rvWallpapers.adapter = WallpapersAdapter(requireContext(),response.body()?.allWallpapers!!)
                    rvWallpapers.layoutManager = GridLayoutManager(requireContext(),2)
                }

                override fun onFailure(call: Call<AllWallpapers>, t: Throwable) {
                    pbWall.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    rvWallpapers.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    Log.e("","")
                }

            })
        }
    }
}