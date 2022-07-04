package com.amirreza.ecommercenikestore.domain.useCases.product_usecases

import com.amirreza.ecommercenikestore.domain.repository.ProductRepositoryI
import io.reactivex.Completable

class AddProductToFavoritesUC(
    private val repo: ProductRepositoryI
)  {
    operator fun invoke(): Completable{
        return repo.addProductToFavorites()
    }
}