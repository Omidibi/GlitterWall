package com.omid.glitterwall.ui.main.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.api.WebServiceCaller
import com.omid.glitterwall.databinding.FragmentCategoriesBinding
import com.omid.glitterwall.model.models.Categories
import com.omid.glitterwall.model.listener.IListener
import retrofit2.Call

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private val webServiceCaller = WebServiceCaller()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        categoryList()
        srlStatusInFragment()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        binding.apply {

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
            webServiceCaller.getCategoryList(object : IListener<Categories> {
                override fun onSuccess(call: Call<Categories>, response: Categories) {
                    if (isAdded) {
                        pbCat.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvCategories.visibility = View.VISIBLE
                        Log.e("", "")
                        rvCategories.adapter = CategoriesAdapter(response.categories)
                        rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                    }
                }

                override fun onFailure(call: Call<Categories>, t: Throwable, errorResponse: String) {
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
            webServiceCaller.getCategoryList(object : IListener<Categories> {
                override fun onSuccess(call: Call<Categories>, response: Categories) {
                    pbCat.visibility = View.GONE
                    clNoConnection.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCategories.visibility = View.VISIBLE
                    Log.e("", "")
                    rvCategories.adapter = CategoriesAdapter(response.categories)
                    rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                }

                override fun onFailure(call: Call<Categories>, t: Throwable, errorResponse: String) {
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
            webServiceCaller.getCategoryList(object : IListener<Categories> {
                override fun onSuccess(call: Call<Categories>, response: Categories) {
                    pbCat.visibility = View.GONE
                    srl.visibility = View.VISIBLE
                    rvCategories.visibility = View.VISIBLE
                    Log.e("", "")
                    rvCategories.adapter = CategoriesAdapter(response.categories)
                    rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                }

                override fun onFailure(call: Call<Categories>, t: Throwable, errorResponse: String) {
                    pbCat.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    rvCategories.visibility = View.GONE
                    Log.e("", "")
                    btnTry.setOnClickListener {
                        tryAgainCategoryList()
                    }
                }
            })
        }
    }
}