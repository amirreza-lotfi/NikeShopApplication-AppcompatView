package com.amirreza.ecommercenikestore.features.feature_home.data.repository

import com.amirreza.ecommercenikestore.features.feature_home.data.source.product_data_spurce.ProductDataSource
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ProductRepositoryI
import io.reactivex.Single

class ProductRepositoryImpl(
    private val remoteDataSource: ProductDataSource,
    private val localDataSource: ProductDataSource,
): ProductRepositoryI {

    override fun getProducts(sort:Int): Single<List<Product>>{
        return remoteDataSource.getProducts(sort)
    }
}