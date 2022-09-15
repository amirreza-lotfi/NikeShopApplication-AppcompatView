package com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.CartShoppingRepository
import io.reactivex.Single

class ChangeCountUC(
    private val repo: CartShoppingRepository
) {
    operator fun invoke(cartItemId: Int, count:Int): Single<AddToCartResponse> {
        return repo.changeCount(cartItemId,count)
    }
}