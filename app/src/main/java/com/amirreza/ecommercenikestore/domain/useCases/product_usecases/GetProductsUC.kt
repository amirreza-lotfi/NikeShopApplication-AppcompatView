package com.amirreza.ecommercenikestore.domain.useCases.product_usecases

import com.example.nikeshop.feature_shop.domain.entity.Product
import com.amirreza.ecommercenikestore.domain.repository.ProductRepositoryI
import io.reactivex.Single

class GetProductsUC(
    private val repo: ProductRepositoryI
) {
    operator fun invoke(sort:Int): Single<List<Product>> {
        return repo.getProducts(sort)
    }
}