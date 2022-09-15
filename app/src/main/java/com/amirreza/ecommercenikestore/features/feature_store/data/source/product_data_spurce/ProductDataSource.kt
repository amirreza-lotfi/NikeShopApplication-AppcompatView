package com.amirreza.ecommercenikestore.features.feature_store.data.source.product_data_spurce

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(sort:Int): Single<List<Product>>
}