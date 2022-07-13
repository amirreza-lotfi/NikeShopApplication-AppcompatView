package com.amirreza.ecommercenikestore.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.data.source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.domain.entity.cart.AddToCartResponse
import io.reactivex.Single

class ChangeCountUC(
    private val repo: CartDataSourceI
) {
    operator fun invoke(cartItemId: Int, count:Int): Single<AddToCartResponse> {
        return repo.changeCount(cartItemId,count)
    }
}