package com.amirreza.ecommercenikestore.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.data.source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.domain.entity.cart.CartItemCount
import io.reactivex.Single

class GetItemsInTheShoppingCart (
    private val repo: CartDataSourceI
) {
    operator fun invoke(): Single<CartItemCount> {
        return repo.getItemsInTheShoppingCart()
    }
}