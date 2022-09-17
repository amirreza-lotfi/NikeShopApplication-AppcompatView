package com.amirreza.ecommercenikestore.features.feature_cart.data.data_source.cart_data_source;

import com.amirreza.ecommercenikestore.utils.http.ApiService;
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.MessageResponse
import com.google.gson.JsonObject
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartResponse
import io.reactivex.Single

class CartRemoteDataSource(private val apiService: ApiService):CartDataSourceI{
    override fun addToCart(productId: Int): Single<AddToCartResponse> {
        return apiService.addToCart(
            JsonObject().apply {
                addProperty("product_id",productId)
            }
        )
    }

    override fun getProductsInShoppingCart(): Single<CartResponse> = apiService.getProductsInShoppingCart()

    override fun remove(cartItemId: Int): Single<MessageResponse> {
        return apiService.remove(
            JsonObject().apply {
                addProperty("cart_item_id",cartItemId)
            }
        )
    }

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> {
        return apiService.changeCountOfProductInShoppingCart(
            JsonObject().apply {
                addProperty("cart_item_id",cartItemId)
                addProperty("count",count)
            }
        )
    }

    override fun getCountOfProductsInShoppingCart(): Single<ProductCountInShoppingCart> = apiService.getCountOfProductsInShoppingCart()

}
