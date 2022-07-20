package com.amirreza.ecommercenikestore.feature_auth.domain.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @SerializedName("token_name")
    val tokenName:String,

    @SerializedName("expires_in")
    val expiresTime:Int,

    @SerializedName("access_token")
    val accessToken:String,

    @SerializedName("refresh_token")
    val refreshToken:String
)
