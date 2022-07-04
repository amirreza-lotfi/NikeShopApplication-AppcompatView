package com.amirreza.ecommercenikestore.domain.repository

import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepositoryI {

    fun getProducts(sort:Int): Single<List<Product>>

    fun getFavoriteProducts():Single<List<Product>>

    fun addProductToFavorites(): Completable

    fun deleteProductFromFavorites(product:Product): Completable
}