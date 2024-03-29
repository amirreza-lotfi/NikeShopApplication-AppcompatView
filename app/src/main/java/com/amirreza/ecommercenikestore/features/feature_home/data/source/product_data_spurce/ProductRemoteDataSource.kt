package com.amirreza.ecommercenikestore.features.feature_home.data.source.product_data_spurce

import com.amirreza.ecommercenikestore.utils.http.ApiService
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import io.reactivex.Single

class ProductRemoteDataSource(private val apiService: ApiService) : ProductDataSource {
    override fun getProducts(sort:Int): Single<List<Product>> = apiService.getProducts(sort.toString())
}