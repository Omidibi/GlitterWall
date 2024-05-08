package com.omid.glitterwall.fragments.showImageByCatIdFragment

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.glitterwall.activity.HomeWidget
import com.omid.glitterwall.databinding.FragmentShowImageByCatIdBinding
import com.omid.glitterwall.models.Category

class ShowImageByCatIdFragment : Fragment() {

    private lateinit var binding: FragmentShowImageByCatIdBinding
    private lateinit var owner: LifecycleOwner
    private lateinit var imgCatIdViewModel: ImgCatIdViewModel
    private lateinit var catById: Category

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        screenOrientationPortrait()
        getData()
        setupBindingAndViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkAvailable()
        catByIdObserver()
        srlStatusInFragment()
        clickEvents()
        setWidget()
    }

    private fun screenOrientationPortrait() {
        requireActivity().requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    private fun getData() {
        catById = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable("categoriesInfo", Category::class.java)!!
        } else {
            requireArguments().getParcelable("categoriesInfo")!!
        }
    }

    private fun setupBindingAndViewModel() {
        binding = FragmentShowImageByCatIdBinding.inflate(layoutInflater)
        imgCatIdViewModel = ViewModelProvider(this@ShowImageByCatIdFragment)[ImgCatIdViewModel::class.java]
    }

    private fun networkAvailable() {
        binding.apply {
            if (imgCatIdViewModel.checkNetworkAvailable()) {
                pb.visibility = View.GONE
                srl.visibility = View.VISIBLE
                liveClNoConnection.visibility = View.GONE
            } else {
                pb.visibility = View.GONE
                srl.visibility = View.GONE
                liveClNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun catByIdObserver() {
        binding.apply {
            pb.visibility = View.VISIBLE
            srl.visibility = View.GONE
            liveClNoConnection.visibility = View.GONE
            clNoConnection.visibility = View.GONE
            imgCatIdViewModel.checkNetworkConnection.observe(owner) { isConnect ->
                if (isConnect) {
                    imgCatIdViewModel.getImgByCatId(catById.cid).observe(owner) { catByIdList ->
                        pb.visibility = View.GONE
                        srl.visibility = View.VISIBLE
                        liveClNoConnection.visibility = View.GONE
                        clNoConnection.visibility = View.GONE
                        rvCatById.adapter = CatByIdAdapter(this@ShowImageByCatIdFragment, catByIdList.catByIdList)
                        rvCatById.layoutManager = GridLayoutManager(requireContext(), 2)
                    }
                } else {
                    pb.visibility = View.GONE
                    srl.visibility = View.GONE
                    liveClNoConnection.visibility = View.VISIBLE
                    clNoConnection.visibility = View.GONE
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

    private fun clickEvents() {
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                findNavController().popBackStack()
                HomeWidget.bnv.visibility = View.VISIBLE
                HomeWidget.toolbar.visibility = View.VISIBLE
            }
        }
    }

    private fun setWidget() {
        if (HomeWidget.bnv.visibility == View.VISIBLE) {
            HomeWidget.bnv.visibility = View.GONE
        }
        if (HomeWidget.toolbar.visibility == View.VISIBLE) {
            HomeWidget.toolbar.visibility = View.GONE
        }
    }

    private fun srlCatById() {
        catByIdObserver()
    }
}