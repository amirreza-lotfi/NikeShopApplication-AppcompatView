package com.amirreza.ecommercenikestore.presebtation.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentHomeBinding
import com.amirreza.ecommercenikestore.presebtation.home_fragment.banner_util.BannerSliderHomeFragmentAdapter
import com.amirreza.ecommercenikestore.presebtation.home_fragment.product_list_util.ProductListAdapter
import com.sevenlearn.nikestore.common.convertDpToPixel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment:NikeFragment() {

    lateinit var binding: FragmentHomeBinding

    private val homeFragmentViewModel:HomeFragmentViewModel by viewModel()
    private val productListAdapter:ProductListAdapter by inject()
    private val popularListAdapter:ProductListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBarSetUp()
        bannerSliderSetUp()
        latestProductsRecyclerViewSetUp()
        popularProductRecyclerViewSetUp()
    }


    private fun progressBarSetUp(){
        homeFragmentViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){ mustShow->
           setProgressBarIndicator(mustShow)
        }
    }
    private fun bannerSliderSetUp(){
        homeFragmentViewModel.bannerLiveData.observe(viewLifecycleOwner){ bannerList->
            val bannerSliderHomeFragmentAdapter = BannerSliderHomeFragmentAdapter(this,bannerList)
            val bannerSliderViewPager = binding.bannerSlider
            bannerSliderViewPager.adapter = bannerSliderHomeFragmentAdapter

            val viewPagerHeight = (((bannerSliderViewPager.measuredWidth - convertDpToPixel(
                32f,
                requireContext()
            )) * 173) / 328).toInt()

            val layoutParams = bannerSliderViewPager.layoutParams
            layoutParams.height = viewPagerHeight
            bannerSliderViewPager.layoutParams = layoutParams
        }
    }

    private fun latestProductsRecyclerViewSetUp(){
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        val latestRecyclerView = binding.latestProductsRv
        latestRecyclerView.layoutManager = layoutManager

        homeFragmentViewModel.latestProductsLiveData.observe(viewLifecycleOwner){
            productListAdapter.products = it
            latestRecyclerView.adapter = productListAdapter
        }
    }

    private fun popularProductRecyclerViewSetUp(){
        val popularRecyclerView = binding.popularProductsRv
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        popularRecyclerView.layoutManager = layoutManager

        homeFragmentViewModel.popularProductsLiveData.observe(viewLifecycleOwner){
            popularListAdapter.products = it
            popularRecyclerView.adapter = popularListAdapter
        }
    }
}