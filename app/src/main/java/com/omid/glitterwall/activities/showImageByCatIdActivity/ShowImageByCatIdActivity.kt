package com.omid.glitterwall.activities.showImageByCatIdActivity

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.databinding.ActivityShowImageByCatIdBinding
import com.omid.glitterwall.models.models.Category

class ShowImageByCatIdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowImageByCatIdBinding
    private lateinit var owner: LifecycleOwner
    private lateinit var imgCatIdViewModel: ImgCatIdViewModel
    private lateinit var catById: Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientationPortrait()
        getData()
        setupBindingAndViewModel()
        setupObservers()
        srlStatusInFragment()

    }

    private fun screenOrientationPortrait() {
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    private fun getData() {
        catById = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("categoriesInfo", Category::class.java)!!
        } else {
            intent.getParcelableExtra("categoriesInfo")!!
        }
        CId.cId = catById.cid
    }

    private fun setupBindingAndViewModel() {
        binding = ActivityShowImageByCatIdBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            owner = this@ShowImageByCatIdActivity
            imgCatIdViewModel = ViewModelProvider(this@ShowImageByCatIdActivity)[ImgCatIdViewModel::class.java]
        }
    }

    private fun setupObservers() {
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            imgCatIdViewModel.catById.observe(this@ShowImageByCatIdActivity) { catByIdList ->
                pb.visibility = View.GONE
                srl.visibility = View.VISIBLE
                rvCatById.adapter = CatByIdAdapter(catByIdList.catByIdList)
                rvCatById.layoutManager = GridLayoutManager(applicationContext, 2)
            }

            imgCatIdViewModel.errorCatById.observe(this@ShowImageByCatIdActivity) { errorCatById ->
                if (errorCatById) {
                    pb.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        pb.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        clNoConnection.visibility = View.GONE
                        imgCatIdViewModel.getCatById(catById.cid)
                    }
                }
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

    private fun srlCatById() {
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            imgCatIdViewModel.getCatById(catById.cid)
        }
    }
}