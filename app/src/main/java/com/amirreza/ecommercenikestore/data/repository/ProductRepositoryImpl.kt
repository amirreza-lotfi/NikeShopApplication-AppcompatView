package com.example.nikeshop.feature_shop.data.repository

import com.example.nikeshop.feature_shop.data.source.product_data_spurce.ProductDataSource
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.example.nikeshop.feature_shop.domain.repository.ProductRepositoryI
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryImpl(
    private val remoteDataSource: ProductDataSource,
    private val localDataSource: ProductDataSource,
):ProductRepositoryI{

    override fun getProducts(sort:Int): Single<List<Product>>{
        return remoteDataSource.getProducts(sort)
    }

    override fun getFavoriteProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun addProductToFavorites(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteProductFromFavorites(): Completable {
        TODO("Not yet implemented")
    }

}