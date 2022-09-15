package com.amirreza.ecommercenikestore.features.feature_cart.data.data_source.cart_data_source

import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.MessageResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartResponse
import io.reactivex.Single

interface CartDataSourceI {
    fun addToCart(productId:Int): Single<AddToCartResponse>
    fun getProductsInShoppingCart(): Single<CartResponse>
    fun remove(cartItemId:Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count:Int): Single<AddToCartResponse>
    fun getCountOfProductsInShoppingCart(): Single<ProductCountInShoppingCart>
}