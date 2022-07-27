package com.amirreza.ecommercenikestore.feature_store.data.repository

import com.amirreza.ecommercenikestore.feature_store.data.source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.CartItemCount
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.MessageResponse
import com.sevenlearn.nikestore.data.CartInShoppingList
import io.reactivex.Single

class CartRepositoryImpl(private val cartDataSourceI: CartDataSourceI):CartDataSourceI {
    override fun addToCart(productId: Int): Single<AddToCartResponse> {
        return cartDataSourceI.addToCart(productId)
    }

    override fun getProductsInShoppingCart(): Single<CartInShoppingList> {
        return cartDataSourceI.getProductsInShoppingCart()
    }

    override fun remove(cartItemId: Int): Single<MessageResponse> {
        return cartDataSourceI.remove(cartItemId)
    }

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> {
        return cartDataSourceI.changeCount(cartItemId,count)
    }

    override fun getItemsInTheShoppingCart(): Single<CartItemCount> {
        return cartDataSourceI.getItemsInTheShoppingCart()
    }
}