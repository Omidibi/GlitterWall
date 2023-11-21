package com.navin.glitterwall.activities

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.navin.glitterwall.adapters.CatByIdAdapter
import com.navin.glitterwall.api.ApiRetrofit
import com.navin.glitterwall.api.IService
import com.navin.glitterwall.databinding.ActivityShowImageByCatIdBinding
import com.navin.glitterwall.models.CatByIdList
import com.navin.glitterwall.models.CategoriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowImageByCatIdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowImageByCatIdBinding
    private lateinit var iService: IService
    private lateinit var catById : CategoriesModel
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowImageByCatIdBinding.inflate(layoutInflater)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(binding.root)
        binding.apply {
            iService = ApiRetrofit.retrofit.create(IService::class.java)
            bundle = intent.extras!!
            catById = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("categoriesInfo",CategoriesModel::class.java)!!
            }else {
                intent.getParcelableExtra("categoriesInfo")!!
            }
             catById()

            srl.setOnRefreshListener {
                srlCatById()
                srl.isRefreshing = false
            }
        }
    }

    private fun catById(){
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            iService.wallByCatId(catById.cid).enqueue(object : Callback<CatByIdList>{
                override fun onResponse(call: Call<CatByIdList>, response: Response<CatByIdList>) {
                    pb.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCatById.adapter = CatByIdAdapter(applicationContext,response.body()?.catByIdList!!)
                    rvCatById.layoutManager = GridLayoutManager(applicationContext,2)
                }

                override fun onFailure(call: Call<CatByIdList>, t: Throwable) {
                    pb.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        tryAgainCatById()
                    }
                }

            })
        }
    }

    private fun tryAgainCatById(){
        binding.apply {
            clNoConnection.visibility = View.GONE
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            iService.wallByCatId(catById.cid).enqueue(object : Callback<CatByIdList>{
                override fun onResponse(call: Call<CatByIdList>, response: Response<CatByIdList>) {
                    pb.visibility = View.GONE
                    clNoConnection.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCatById.adapter = CatByIdAdapter(applicationContext,response.body()?.catByIdList!!)
                    rvCatById.layoutManager = GridLayoutManager(applicationContext,2)
                }

                override fun onFailure(call: Call<CatByIdList>, t: Throwable) {
                    pb.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }

            })
        }
    }

    private fun srlCatById(){
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            iService.wallByCatId(catById.cid).enqueue(object : Callback<CatByIdList>{
                override fun onResponse(call: Call<CatByIdList>, response: Response<CatByIdList>) {
                    pb.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCatById.adapter = CatByIdAdapter(applicationContext,response.body()?.catByIdList!!)
                    rvCatById.layoutManager = GridLayoutManager(applicationContext,2)
                }

                override fun onFailure(call: Call<CatByIdList>, t: Throwable) {
                    pb.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }

            })
        }
    }
}