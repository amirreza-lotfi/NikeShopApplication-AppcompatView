package com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.feature_store.domain.repository.ProductRepositoryI
import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Single

class GetFavoriteProductsUC(
    private val repo: ProductRepositoryI
)  {
    operator fun invoke(): Single<List<Product>> {
        return repo.getFavoriteProducts()
    }
}