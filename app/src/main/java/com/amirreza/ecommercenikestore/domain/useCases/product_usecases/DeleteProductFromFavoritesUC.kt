package com.amirreza.ecommercenikestore.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.domain.repository.ProductRepositoryI
import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Completable

class DeleteProductFromFavoritesUC(
    private val repo: ProductRepositoryI
)  {
    operator fun invoke(product: Product): Completable {
        return repo.deleteProductFromFavorites(product)
    }
}