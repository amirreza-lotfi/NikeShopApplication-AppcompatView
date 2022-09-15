package com.example.nikeshop.feature_shop.data.source.product_data_spurce

import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(sort:Int): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addToFavorites(): Completable

    fun deleteFromFavorites(): Completable
}