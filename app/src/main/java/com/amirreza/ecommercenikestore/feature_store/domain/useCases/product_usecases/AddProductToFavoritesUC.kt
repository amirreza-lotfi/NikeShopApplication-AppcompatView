package com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.feature_store.domain.repository.ProductRepositoryI
import io.reactivex.Completable

class AddProductToFavoritesUC(
    private val repo: ProductRepositoryI
)  {
    operator fun invoke(): Completable{
        return repo.addProductToFavorites()
    }
}