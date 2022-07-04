package com.example.nikeshop.feature_shop.domain.repository

import com.example.nikeshop.feature_shop.domain.entity.Banner
import io.reactivex.Single

interface BannerRepositoryI {
    fun getBanner():Single<List<Banner>>
}