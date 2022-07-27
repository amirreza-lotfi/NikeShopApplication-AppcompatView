package com.amirreza.ecommercenikestore.feature_cart.domain.repository

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.MessageResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartResponse
import io.reactivex.Single

interface CartShoppingRepository {
    fun addToCart(productId:Int): Single<AddToCartResponse>
    fun getProductsInShoppingCart():Single<CartResponse>
    fun remove(cartItemId:Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count:Int):Single<AddToCartResponse>
    fun getItemsInTheShoppingCart():Single<ProductCountInShoppingCart>
}