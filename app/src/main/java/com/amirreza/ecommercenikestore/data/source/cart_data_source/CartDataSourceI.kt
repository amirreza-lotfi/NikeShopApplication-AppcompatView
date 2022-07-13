package com.amirreza.ecommercenikestore.data.source.cart_data_source

import com.amirreza.ecommercenikestore.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.domain.entity.cart.CartItemCount
import com.amirreza.ecommercenikestore.domain.entity.cart.MessageResponse
import com.sevenlearn.nikestore.data.CartInShoppingList
import io.reactivex.Single

interface CartDataSourceI {
    fun addToCart(productId:Int): Single<AddToCartResponse>
    fun getProductsInShoppingCart(): Single<CartInShoppingList>
    fun remove(cartItemId:Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count:Int): Single<AddToCartResponse>
    fun getItemsInTheShoppingCart(): Single<CartItemCount>
}