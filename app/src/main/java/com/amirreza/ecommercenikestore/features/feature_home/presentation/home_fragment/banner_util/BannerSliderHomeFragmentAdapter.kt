package com.amirreza.ecommercenikestore.features.feature_home.presentation.home_fragment.banner_util

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nikeshop.feature_shop.domain.entity.Banner

class BannerSliderHomeFragmentAdapter(private val fragment: Fragment, private val bannerList:List<Banner>):FragmentStateAdapter(fragment) {
    override fun getItemCount() = bannerList.size

    override fun createFragment(position: Int): Fragment {
        return BannerFragment.newInstance(bannerList[position])
    }
}