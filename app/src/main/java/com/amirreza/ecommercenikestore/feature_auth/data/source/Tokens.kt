package com.amirreza.ecommercenikestore.feature_auth.data.source

object Tokens {
    var token:String? = null
        private set
    var refreshToken:String? = null
        private set

    fun updateTokens(token: String?, refreshToken: String?){
        this.token = token
        this.refreshToken = refreshToken
    }
}