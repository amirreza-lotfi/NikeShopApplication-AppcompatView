package com.amirreza.ecommercenikestore.feature_auth.domain.repository

import io.reactivex.Completable

interface AuthRepository {
    fun login(username:String, password:String):Completable
    fun signUp(username:String, password:String):Completable
    fun loadToken()
    fun saveToken(token:String, refreshToken:String)
}