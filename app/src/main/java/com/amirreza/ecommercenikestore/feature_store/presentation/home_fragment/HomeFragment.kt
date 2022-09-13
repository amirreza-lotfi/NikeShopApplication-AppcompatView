package com.amirreza.ecommercenikestore.feature_store.presentation.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_SORT_TYPE
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_VIEW_TYPE
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentHomeBinding
import com.amirreza.ecommercenikestore.feature_store.presentation.all_product_fragment.product_recycler_view.VIEW_TYPE_GRID
import com.amirreza.ecommercenikestore.feature_store.presentation.all_product_fragment.product_recycler_view.VIEW_TYPE_LARG_VERTICAL
import com.amirreza.ecommercenikestore.feature_store.presentation.home_fragment.banner_util.BannerSliderHomeFragmentAdapter
import com.amirreza.ecommercenikestore.feature_store.presentation.home_fragment.product_list_util.ItemClickListener
import com.amirreza.ecommercenikestore.feature_store.presentation.home_fragment.product_list_util.ProductListAdapter
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.example.nikeshop.feature_shop.domain.entity.SORT_NEWEST
import com.example.nikeshop.feature_shop.domain.entity.SORT_POPULAR
import com.amirreza.ecommercenikestore.feature_store.common.util.convertDpToPixel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment:NikeFragment(), ItemClickListener {

    lateinit var binding: FragmentHomeBinding

    private val homeFragmentViewModel:HomeFragmentViewModel by viewModel()
    private val latestProductAdapter:ProductListAdapter by inject()
    private val popularListAdapter:ProductListAdapter by inject()
    private var viewPagerHeight = 0

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
        onSeeAllLatestProductsClick()
        onSeeAllPopularProductsClick()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun progressBarSetUp(){
        homeFragmentViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){ mustShow->
           setProgressBarIndicator(mustShow)
        }
    }
    private fun bannerSliderSetUp(){
        homeFragmentViewModel.bannerLiveData.observe(viewLifecycleOwner){ bannerList->
            val bannerSliderHomeFragmentAdapter = BannerSliderHomeFragmentAdapter(this,bannerList)

            binding.bannerSlider.post {
                val bannerSliderViewPager = binding.bannerSlider
                bannerSliderViewPager.adapter = bannerSliderHomeFragmentAdapter

                viewPagerHeight = (((bannerSliderViewPager.measuredWidth - convertDpToPixel(
                    32f,
                    requireContext()
                )) * 173) / 328).toInt()

                val layoutParams = bannerSliderViewPager.layoutParams
                layoutParams.height = viewPagerHeight
                bannerSliderViewPager.layoutParams = layoutParams
            }


        }
    }

    private fun latestProductsRecyclerViewSetUp(){
        latestProductAdapter.itemClickListener = this
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        val latestRecyclerView = binding.latestProductsRv
        latestRecyclerView.layoutManager = layoutManager

        homeFragmentViewModel.latestProductsLiveData.observe(viewLifecycleOwner){
            latestProductAdapter.products = it as ArrayList<Product> /* = java.util.ArrayList<com.example.nikeshop.feature_shop.domain.entity.Product> */
            latestRecyclerView.adapter = latestProductAdapter
        }
    }

    private fun popularProductRecyclerViewSetUp(){
        popularListAdapter.itemClickListener = this
        val popularRecyclerView = binding.popularProductsRv
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        popularRecyclerView.layoutManager = layoutManager

        homeFragmentViewModel.popularProductsLiveData.observe(viewLifecycleOwner){
            popularListAdapter.products = it as ArrayList<Product> /* = java.util.ArrayList<com.example.nikeshop.feature_shop.domain.entity.Product> */
            popularRecyclerView.adapter = popularListAdapter
        }
    }

    private fun onSeeAllLatestProductsClick(){
        binding.seeAllLatestProducts.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt(EXTRA_SORT_TYPE, SORT_NEWEST)
            bundle.putInt(EXTRA_VIEW_TYPE, VIEW_TYPE_GRID)
            findNavController().navigate(R.id.action_navigation_home_to_allProductFragment,bundle)
        }
    }
    private fun onSeeAllPopularProductsClick(){
        binding.seeAllPopularProducts.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt(EXTRA_SORT_TYPE, SORT_POPULAR)
            bundle.putInt(EXTRA_VIEW_TYPE, VIEW_TYPE_LARG_VERTICAL)
            findNavController().navigate(R.id.action_navigation_home_to_allProductFragment,bundle)
        }
    }
    override fun onClick(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL,product)
        findNavController().navigate(R.id.action_navigation_home_to_productDetailFragment,bundle)
    }
}