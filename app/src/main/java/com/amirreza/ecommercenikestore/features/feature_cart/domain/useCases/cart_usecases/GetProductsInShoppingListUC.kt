package com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.CartShoppingRepository
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartResponse
import io.reactivex.Single

class GetProductsInShoppingListUC (
    private val repo: CartShoppingRepository
) {
    operator fun invoke(): Single<CartResponse> {
        return repo.getProductsInShoppingCart()
    }
}