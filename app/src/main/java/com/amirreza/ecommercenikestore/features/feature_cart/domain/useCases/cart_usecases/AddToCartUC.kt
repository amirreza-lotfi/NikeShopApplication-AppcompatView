package com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.CartShoppingRepository
import io.reactivex.Single

class AddToCartUC(
    private val repo: CartShoppingRepository
) {
    operator fun invoke(productId:Int): Single<AddToCartResponse> {
        return repo.addToCart(productId)
    }
}