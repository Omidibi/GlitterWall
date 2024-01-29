package com.omid.glitterwall.activities.showImageByCatIdActivity

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.databinding.ActivityShowImageByCatIdBinding
import com.omid.glitterwall.model.models.CatByIdList
import com.omid.glitterwall.model.listener.IListener
import com.omid.glitterwall.model.models.Category
import retrofit2.Call

class ShowImageByCatIdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowImageByCatIdBinding
    private lateinit var catById: Category
    private lateinit var bundle: Bundle
    private val webServiceCaller = WebServiceCaller()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindingAndInitializeCatById()
        catById()
        srlStatusInFragment()

    }

    private fun setupBindingAndInitializeCatById() {
        binding = ActivityShowImageByCatIdBinding.inflate(layoutInflater)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        binding.apply {
            setContentView(root)
            bundle = intent.extras!!
            catById = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("categoriesInfo", Category::class.java)!!
            } else {
                intent.getParcelableExtra("categoriesInfo")!!
            }
        }
    }

    private fun srlStatusInFragment() {
        binding.apply {
            srl.setOnRefreshListener {
                srlCatById()
                srl.isRefreshing = false
            }
        }
    }

    private fun catById() {
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getCatById(catById.cid, object : IListener<CatByIdList> {
                override fun onSuccess(call: Call<CatByIdList>, response: CatByIdList) {
                    pb.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCatById.adapter = CatByIdAdapter(response.catByIdList)
                    rvCatById.layoutManager = GridLayoutManager(applicationContext, 2)
                }

                override fun onFailure(call: Call<CatByIdList>, t: Throwable, errorResponse: String) {
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

    private fun tryAgainCatById() {
        binding.apply {
            clNoConnection.visibility = View.GONE
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getCatById(catById.cid, object : IListener<CatByIdList> {
                override fun onSuccess(call: Call<CatByIdList>, response: CatByIdList) {
                    pb.visibility = View.GONE
                    clNoConnection.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCatById.adapter = CatByIdAdapter(response.catByIdList)
                    rvCatById.layoutManager = GridLayoutManager(applicationContext, 2)
                }

                override fun onFailure(call: Call<CatByIdList>, t: Throwable, errorResponse: String) {
                    pb.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun srlCatById() {
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            webServiceCaller.getCatById(catById.cid, object : IListener<CatByIdList> {
                override fun onSuccess(call: Call<CatByIdList>, response: CatByIdList) {
                    pb.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCatById.adapter = CatByIdAdapter(response.catByIdList)
                    rvCatById.layoutManager = GridLayoutManager(applicationContext, 2)
                }

                override fun onFailure(call: Call<CatByIdList>, t: Throwable, errorResponse: String) {
                    pb.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                }
            })
        }
    }
}