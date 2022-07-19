package com.amirreza.ecommercenikestore.feature_store.domain.useCases.banner_usecase

import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import io.reactivex.Single

class GetBannerUC(
    private val repo:BannerRepositoryI
) {
    operator fun invoke(): Single<List<Banner>> {
        return repo.getBanner()
    }
}