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

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var owner: LifecycleOwner
    private lateinit var categoriesViewModel: CategoriesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        setupObservers()
        srlStatusInFragment()
        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
    }

    private fun setupObservers() {
        binding.apply {
            categoriesViewModel.category.observe(viewLifecycleOwner) { categories ->
                pbCat.visibility = View.GONE
                srl.visibility = View.VISIBLE
                rvCategories.visibility = View.VISIBLE
                rvCategories.adapter = CategoriesAdapter(categories.categories)
                rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
            }

            categoriesViewModel.errorCategories.observe(viewLifecycleOwner) { hasErrorCategories ->
                if (hasErrorCategories) {
                    pbCat.visibility = View.GONE
                    srl.visibility = View.GONE
                    rvCategories.visibility = View.GONE
                    clNoConnection.visibility = View.VISIBLE
                    btnTry.setOnClickListener {
                        pbCat.visibility = View.VISIBLE
                        clNoConnection.visibility = View.GONE
                        categoriesViewModel.getCategoryList()
                    }
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