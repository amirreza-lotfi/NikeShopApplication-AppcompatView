package com.amirreza.ecommercenikestore.feature_auth.domain.model

object Tokens {
    var token:String? = null
        private set
    var refreshToken:String? = null
        private set

    fun updateTokens(token: String?, refreshToken: String?){
        Tokens.token = token
        Tokens.refreshToken = refreshToken
    }
}