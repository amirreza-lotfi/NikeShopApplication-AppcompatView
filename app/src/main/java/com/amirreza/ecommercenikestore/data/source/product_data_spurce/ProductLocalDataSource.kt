package com.amirreza.ecommercenikestore.data.source.product_data_spurce

import com.example.nikeshop.feature_shop.data.source.product_data_spurce.ProductDataSource
import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

class ProductLocalDataSource: ProductDataSource {
    override fun getProducts(sort:Int): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFromFavorites(): Completable {
        TODO("Not yet implemented")
    }
}