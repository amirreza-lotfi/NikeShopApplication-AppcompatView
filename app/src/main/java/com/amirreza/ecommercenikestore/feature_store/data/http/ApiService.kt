package com.amirreza.ecommercenikestore.feature_store.data.http

import com.amirreza.ecommercenikestore.feature_store.domain.entity.Comment
import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.AddToCartResponse
import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("product/list")
    fun getProducts(@Query("sort") sort: String): Single<List<Product>>

    @GET("banner/slider")
    fun getBanner():Single<List<Banner>>

    @GET("comment/list")
    fun getComments(@Query("product_id") productId:Int):Single<List<Comment>>

    @POST("cart/add")
    fun addToCart(@Body jsonObject: JsonObject):Single<AddToCartResponse>

}

fun createInstanceOfApiService(): ApiService {
    val retrofit =  Retrofit.Builder()
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}