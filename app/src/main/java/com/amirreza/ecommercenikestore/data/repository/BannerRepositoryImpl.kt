package com.example.nikeshop.feature_shop.data.repository

import com.amirreza.ecommercenikestore.data.source.banner_data_source.BannerRemoteDataSource
import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import io.reactivex.Single

class BannerRepositoryImpl(private val bannerRemoteDataSource: BannerRemoteDataSource):BannerRepositoryI {
    override fun getBanner(): Single<List<Banner>> {
        return bannerRemoteDataSource.getBanners()
    }
}