package com.navin.glitterwall.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.navin.glitterwall.adapters.CategoriesAdapter
import com.navin.glitterwall.api.ApiRetrofit
import com.navin.glitterwall.api.IService
import com.navin.glitterwall.databinding.FragmentCategoriesBinding
import com.navin.glitterwall.models.Categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var iService: IService
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        categoryList()
        srlStatusInFragment()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        binding.apply {
            iService = ApiRetrofit.retrofit.create(IService::class.java)
        }
    }

    private fun srlStatusInFragment() {
        binding.apply {
            srl.setOnRefreshListener {
                srlCategoryList()
                srl.isRefreshing = false
            }
        }
    }

    private fun categoryList() {
        binding.apply {
            pbCat.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvCategories.visibility = View.GONE
            iService.categoriesList().enqueue(object : Callback<Categories> {
                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    if (isAdded) {
                        pbCat.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvCategories.visibility = View.VISIBLE
                        Log.e("", "")
                        rvCategories.adapter =
                            CategoriesAdapter(requireContext(), response.body()?.categories!!)
                        rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                    }
                }

                override fun onFailure(call: Call<Categories>, t: Throwable) {
                    if (isAdded) {
                        pbCat.visibility = View.VISIBLE
                        srl.visibility = View.GONE
                        rvCategories.visibility = View.GONE
                        clNoConnection.visibility = View.VISIBLE
                        Log.e("", "")
                        btnTry.setOnClickListener {
                            tryAgainCategoryList()
                        }
                    }
                }

            })
        }
    }

    private fun tryAgainCategoryList() {
        binding.apply {
            clNoConnection.visibility = View.GONE
            pbCat.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvCategories.visibility = View.GONE
            iService.categoriesList().enqueue(object : Callback<Categories> {
                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    pbCat.visibility = View.GONE
                    clNoConnection.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCategories.visibility = View.VISIBLE
                    Log.e("", "")
                    rvCategories.adapter =
                        CategoriesAdapter(requireContext(), response.body()?.categories!!)
                    rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                }

                override fun onFailure(call: Call<Categories>, t: Throwable) {
                    pbCat.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    rvCategories.visibility = View.GONE
                    Log.e("", "")
                }

            })
        }
    }

    private fun srlCategoryList() {
        binding.apply {
            pbCat.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvCategories.visibility = View.GONE
            iService.categoriesList().enqueue(object : Callback<Categories> {
                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    pbCat.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCategories.visibility = View.VISIBLE
                    Log.e("", "")
                    rvCategories.adapter =
                        CategoriesAdapter(requireContext(), response.body()?.categories!!)
                    rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                }

                override fun onFailure(call: Call<Categories>, t: Throwable) {
                    pbCat.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    rvCategories.visibility = View.GONE
                    Log.e("", "")
                }

            })
        }
    }
}