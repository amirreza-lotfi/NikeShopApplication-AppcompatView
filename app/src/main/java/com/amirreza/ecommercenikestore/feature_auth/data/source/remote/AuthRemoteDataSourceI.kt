package com.amirreza.ecommercenikestore.feature_auth.data.source.remote

import com.amirreza.ecommercenikestore.feature_auth.domain.model.TokenResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.MessageResponse
import io.reactivex.Single

interface AuthRemoteDataSourceI {
    fun login(username:String, password:String): Single<TokenResponse>
    fun signUp(username:String, password:String): Single<MessageResponse>
}