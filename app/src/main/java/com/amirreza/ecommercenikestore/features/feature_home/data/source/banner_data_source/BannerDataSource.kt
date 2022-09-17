package com.example.nikeshop.feature_shop.data.source.banner_data_source

import com.example.nikeshop.feature_shop.domain.entity.Banner
import io.reactivex.Single

interface BannerDataSource {
    fun getBanners():Single<List<Banner>>
}