package com.amirreza.ecommercenikestore.features.feature_auth.data.source.local

interface AuthLocalDataSourceI {
    fun loadToken()
    fun saveToken(token:String, refreshToken:String)
    fun saveUserName(userName:String)
}