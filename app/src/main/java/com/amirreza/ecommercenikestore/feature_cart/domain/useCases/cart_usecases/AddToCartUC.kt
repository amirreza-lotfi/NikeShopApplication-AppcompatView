package com.amirreza.ecommercenikestore.feature_store.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.feature_store.data.source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.AddToCartResponse
import io.reactivex.Single

class AddToCartUC(
    private val repo:CartDataSourceI
) {
    operator fun invoke(productId:Int): Single<AddToCartResponse> {
        return repo.addToCart(productId)
    }
}