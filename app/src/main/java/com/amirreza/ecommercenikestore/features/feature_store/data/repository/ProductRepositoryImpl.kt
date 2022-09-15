package com.amirreza.ecommercenikestore.features.feature_store.data.repository

import com.amirreza.ecommercenikestore.features.feature_store.data.source.product_data_spurce.ProductDataSource
import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ProductRepositoryI
import io.reactivex.Single

class ProductRepositoryImpl(
    private val remoteDataSource: ProductDataSource,
    private val localDataSource: ProductDataSource,
): ProductRepositoryI {

    override fun getProducts(sort:Int): Single<List<Product>>{
        return remoteDataSource.getProducts(sort)
    }
}