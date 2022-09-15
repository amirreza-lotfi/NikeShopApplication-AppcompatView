package com.amirreza.ecommercenikestore.features.feature_store.data.source.banner_data_source

import com.amirreza.ecommercenikestore.http.ApiService
import com.example.nikeshop.feature_shop.data.source.banner_data_source.BannerDataSource
import com.example.nikeshop.feature_shop.domain.entity.Banner
import io.reactivex.Single

class BannerRemoteDataSource(private val apiService: ApiService): BannerDataSource {
    override fun getBanners(): Single<List<Banner>> = apiService.getBanner()
}