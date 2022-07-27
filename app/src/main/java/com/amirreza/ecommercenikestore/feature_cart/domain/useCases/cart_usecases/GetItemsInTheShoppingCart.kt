package com.amirreza.ecommercenikestore.feature_store.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.feature_store.data.source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.CartItemCount
import io.reactivex.Single

class GetItemsInTheShoppingCart (
    private val repo: CartDataSourceI
) {
    operator fun invoke(): Single<CartItemCount> {
        return repo.getItemsInTheShoppingCart()
    }
}