package com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ProductRepositoryI
import io.reactivex.Single

class GetProductsUC(
    private val repo: ProductRepositoryI
) {
    operator fun invoke(sort:Int): Single<List<Product>> {
        return repo.getProducts(sort)
    }
}