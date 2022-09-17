package com.amirreza.ecommercenikestore.features.feature_home.data.source.product_data_spurce

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(sort:Int): Single<List<Product>>
}