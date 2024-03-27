package com.omid.glitterwall.ui.dashboard.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.databinding.FragmentCategoriesBinding
import com.omid.glitterwall.utils.internetLiveData.CheckNetworkConnection
import com.omid.glitterwall.utils.networkAvailable.NetworkAvailable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var owner: LifecycleOwner
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var checkNetworkConnection : CheckNetworkConnection

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        checkNetwork()
        setupObservers()
        srlStatusInFragment()
        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        categoriesViewModel = ViewModelProvider(this@CategoriesFragment)[CategoriesViewModel::class.java]
        checkNetworkConnection = CheckNetworkConnection(requireActivity().application)
    }

    private fun checkNetwork(){
        binding.apply {
            if (NetworkAvailable.isNetworkAvailable(requireContext())) {
                pbCat.visibility = View.GONE
                srl.visibility = View.VISIBLE
                rvCategories.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            }else {
                pbCat.visibility = View.GONE
                srl.visibility = View.GONE
                rvCategories.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun setupObservers() {
        binding.apply {
            checkNetworkConnection.observe(viewLifecycleOwner) { isConnect ->
                if (isConnect) {
                    categoriesViewModel.category.observe(viewLifecycleOwner) { categories ->
                        pbCat.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        rvCategories.visibility = View.VISIBLE
                        liveNoConnection.visibility = View.GONE
                        rvCategories.adapter = CategoriesAdapter(this@CategoriesFragment,categories.categories)
                        rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                    }
                } else {
                    pbCat.visibility = View.GONE
                    srl.visibility = View.GONE
                    rvCategories.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun srlStatusInFragment() {
        binding.apply {
            srl.setOnRefreshListener {
                srlObservers()
                srl.isRefreshing = false
            }
        }
    }

    private fun srlObservers() {
        binding.apply {
            pbCat.visibility = View.VISIBLE
            srl.visibility = View.GONE
            rvCategories.visibility = View.GONE
            categoriesViewModel.getCategoryList()
        }
    }
}