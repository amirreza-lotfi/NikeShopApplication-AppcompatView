package com.amirreza.ecommercenikestore.features.feature_store.data.source.product_data_spurce

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import io.reactivex.Single

class ProductLocalDataSource: ProductDataSource {
    override fun getProducts(sort:Int): Single<List<Product>> {
        TODO("Not yet implemented")
    }
}