package com.navin.glitterwall.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.navin.glitterwall.adapters.LatestWallpaperAdapter
import com.navin.glitterwall.api.ApiRetrofit
import com.navin.glitterwall.api.IService
import com.navin.glitterwall.databinding.ActivityLatestWallpaperBinding
import com.navin.glitterwall.models.LatestWallpapers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LatestWallpaperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLatestWallpaperBinding
    private lateinit var iService: IService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            iService = ApiRetrofit.retrofit.create(IService::class.java)
            latestWall()

            srl.setOnRefreshListener {
                srlLatestWall()
                srl.isRefreshing = false
            }
        }
    }

    private fun latestWall(){
        binding.apply {
            pbLatest.visibility = View.VISIBLE
            srl.visibility = View.GONE
            iService.latestWallpaper().enqueue(object : Callback<LatestWallpapers>{
                override fun onResponse(call: Call<LatestWallpapers>, response: Response<LatestWallpapers>) {
                    Log.e("","")
                    pbLatest.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvLatestWall.adapter = LatestWallpaperAdapter(applicationContext,response.body()?.latestWallpapers!!)
                    rvLatestWall.layoutManager = GridLayoutManager(applicationContext,2)
                }

                override fun onFailure(call: Call<LatestWallpapers>, t: Throwable) {
                    Log.e("","")
                    pbLatest.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        tryAgainLatestWall()
                    }
                }

            })
        }
    }

    private fun tryAgainLatestWall(){
        binding.apply {
            clNoConnection.visibility = View.GONE
            pbLatest.visibility = View.VISIBLE
            srl.visibility = View.GONE
            iService.latestWallpaper().enqueue(object : Callback<LatestWallpapers>{
                override fun onResponse(call: Call<LatestWallpapers>, response: Response<LatestWallpapers>) {
                    Log.e("","")
                    clNoConnection.visibility = View.GONE
                    pbLatest.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvLatestWall.adapter = LatestWallpaperAdapter(applicationContext,response.body()?.latestWallpapers!!)
                    rvLatestWall.layoutManager = GridLayoutManager(applicationContext,2)
                }

                override fun onFailure(call: Call<LatestWallpapers>, t: Throwable) {
                    Log.e("","")
                    pbLatest.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }

            })
        }
    }

    private fun srlLatestWall(){
        binding.apply {
            pbLatest.visibility = View.VISIBLE
            srl.visibility = View.GONE
            iService.latestWallpaper().enqueue(object : Callback<LatestWallpapers>{
                override fun onResponse(call: Call<LatestWallpapers>, response: Response<LatestWallpapers>) {
                    Log.e("","")
                    pbLatest.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvLatestWall.adapter = LatestWallpaperAdapter(applicationContext,response.body()?.latestWallpapers!!)
                    rvLatestWall.layoutManager = GridLayoutManager(applicationContext,2)
                }

                override fun onFailure(call: Call<LatestWallpapers>, t: Throwable) {
                    Log.e("","")
                    pbLatest.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }

            })
        }
    }


}