package com.amirreza.ecommercenikestore.features.feature_profile.domain.repo

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteRepository {
    fun getFavoriteProducts(): Single<List<Product>>
    fun addProductToFavorites(product: Product): Completable
    fun deleteProductToFavorites(product: Product): Completable
}