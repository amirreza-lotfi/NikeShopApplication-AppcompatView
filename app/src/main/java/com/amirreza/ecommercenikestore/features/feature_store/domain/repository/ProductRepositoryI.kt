package com.amirreza.ecommercenikestore.features.feature_store.domain.repository

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import io.reactivex.Single

interface ProductRepositoryI {
    fun getProducts(sort:Int): Single<List<Product>>
}