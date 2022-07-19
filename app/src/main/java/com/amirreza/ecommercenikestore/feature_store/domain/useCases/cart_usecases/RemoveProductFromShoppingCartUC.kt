package com.amirreza.ecommercenikestore.feature_store.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.feature_store.data.source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.MessageResponse
import io.reactivex.Single

class RemoveProductFromShoppingCartUC (
    private val repo: CartDataSourceI
) {
    operator fun invoke(productId:Int): Single<MessageResponse> {
        return repo.remove(productId)
    }
}