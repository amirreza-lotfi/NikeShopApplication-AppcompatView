package com.amirreza.ecommercenikestore.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.domain.repository.ProductRepositoryI
import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

class GetFavoriteProductsUC(
    private val repo: ProductRepositoryI
)  {
    operator fun invoke(): Single<List<Product>> {
        return repo.getFavoriteProducts()
    }
}