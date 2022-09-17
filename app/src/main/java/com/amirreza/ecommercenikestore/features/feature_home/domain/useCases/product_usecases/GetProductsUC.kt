package com.amirreza.ecommercenikestore.features.feature_home.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ProductRepositoryI
import io.reactivex.Single

class GetProductsUC(
    private val repo: ProductRepositoryI
) {
    operator fun invoke(sort:Int): Single<List<Product>> {
        return repo.getProducts(sort)
    }
}