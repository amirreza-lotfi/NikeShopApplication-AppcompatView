
package com.amirreza.ecommercenikestore.http

import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.TokenResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.OrderDetail
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.OrderResult
import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.MessageResponse
import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import com.google.gson.JsonObject
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartResponse
import com.amirreza.ecommercenikestore.features.feature_profile.domain.entities.OrderHistoryItem
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    /**
     * Product feature requests
     */
    @GET("product/list")
    fun getProducts(@Query("sort") sort: String): Single<List<Product>>

    @GET("banner/slider")
    fun getBanner():Single<List<Banner>>

    @GET("comment/list")
    fun getComments(@Query("product_id") productId:Int):Single<List<Comment>>


    /**
     * Shopping Cart feature requests
     */
    @POST("cart/add")
    fun addToCart(@Body jsonObject: JsonObject):Single<AddToCartResponse>

    @POST("cart/remove")
    fun remove(@Body jsonObject: JsonObject):Single<MessageResponse>

    @GET("cart/list")
    fun getProductsInShoppingCart():Single<CartResponse>

    @POST("cart/changeCount")
    fun changeCountOfProductInShoppingCart(@Body jsonObject: JsonObject):Single<AddToCartResponse>

    @GET("cart/count")
    fun getCountOfProductsInShoppingCart():Single<ProductCountInShoppingCart>

    /**
     * Auth feature requests
     */
    @POST("auth/token")
    fun login(@Body jsonObject: JsonObject):Single<TokenResponse>

    @POST("user/register")
    fun signUp(@Body jsonObject: JsonObject):Single<MessageResponse>

    @POST("auth/token")
    fun refreshToken(@Body jsonObject: JsonObject):Call<TokenResponse>

    /**
     * Order
     */
    @POST("order/submit")
    fun registerOrder(@Body jsonObject: JsonObject): Single<OrderResult>

    @GET("order/checkout")
    fun getOrderDetail(@Query("order_id") orderId: Int): Single<OrderDetail>

    @GET("order/list")
    fun orders():Single<List<OrderHistoryItem>>
}

fun createInstanceOfApiService(): ApiService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val previousRequest = it.request()
            val newRequest = previousRequest.newBuilder()
            if(Tokens.token!=null)
                newRequest.addHeader("Authorization","Bearer ${Tokens.token}")
            newRequest.addHeader("Accept","application/json")
            newRequest.method(previousRequest.method, previousRequest.body)
            return@addInterceptor it.proceed(newRequest.build())
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    val retrofit =  Retrofit.Builder()
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}