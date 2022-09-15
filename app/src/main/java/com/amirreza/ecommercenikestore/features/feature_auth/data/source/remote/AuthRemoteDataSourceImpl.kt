package com.amirreza.ecommercenikestore.features.feature_auth.data.source.remote

import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.TokenResponse
import com.amirreza.ecommercenikestore.features.feature_store.common.util.CLIENT_ID
import com.amirreza.ecommercenikestore.features.feature_store.common.util.CLIENT_SECRET
import com.amirreza.ecommercenikestore.http.ApiService
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.MessageResponse
import com.google.gson.JsonObject
import io.reactivex.Single


class AuthRemoteDataSourceImpl(private val apiService: ApiService): AuthRemoteDataSourceI {
    override fun login(username: String, password: String): Single<TokenResponse> {
        return apiService.login(
            JsonObject().apply {
                addProperty("username",username)
                addProperty("password",password)
                addProperty("grant_type","password")
                addProperty("client_id", CLIENT_ID)
                addProperty("client_secret", CLIENT_SECRET)
            }
        )
    }
    override fun signUp(username: String, password: String): Single<MessageResponse> {
        return apiService.signUp(
            JsonObject().apply {
                addProperty("email",username)
                addProperty("password",password)
            }
        )
    }
}