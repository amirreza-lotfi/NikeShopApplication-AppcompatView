package com.amirreza.ecommercenikestore.feature_auth.data.source.local

interface AuthLocalDataSourceI {
    fun loadToken()
    fun saveToken(token:String, refreshToken:String)
}