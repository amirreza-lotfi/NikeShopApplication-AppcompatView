package com.amirreza.ecommercenikestore.features.feature_home.data.source.banner_data_source

import com.amirreza.ecommercenikestore.utils.http.ApiService
import com.example.nikeshop.feature_shop.data.source.banner_data_source.BannerDataSource
import com.example.nikeshop.feature_shop.domain.entity.Banner
import io.reactivex.Single

class BannerRemoteDataSource(private val apiService: ApiService): BannerDataSource {
    override fun getBanners(): Single<List<Banner>> = apiService.getBanner()
}