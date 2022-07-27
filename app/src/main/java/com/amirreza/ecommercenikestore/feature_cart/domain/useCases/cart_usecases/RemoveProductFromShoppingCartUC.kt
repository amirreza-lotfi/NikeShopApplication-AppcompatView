package com.amirreza.ecommercenikestore.feature_cart.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.MessageResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.repository.CartShoppingRepository
import io.reactivex.Single

class RemoveProductFromShoppingCartUC (
    private val repo: CartShoppingRepository
) {
    operator fun invoke(productId:Int): Single<MessageResponse> {
        return repo.remove(productId)
    }
}