package com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.CartShoppingRepository
import io.reactivex.Single

class GetItemsInTheShoppingCart (
    private val repo: CartShoppingRepository
) {
    operator fun invoke(): Single<ProductCountInShoppingCart> {
        return repo.getCountsOfItemsInCart()
    }
}