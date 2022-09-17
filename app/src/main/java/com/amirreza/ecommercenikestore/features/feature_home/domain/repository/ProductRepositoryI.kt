package com.amirreza.ecommercenikestore.features.feature_home.domain.repository

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import io.reactivex.Single

interface ProductRepositoryI {
    fun getProducts(sort:Int): Single<List<Product>>
}