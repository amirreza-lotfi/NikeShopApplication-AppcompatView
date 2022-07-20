package com.amirreza.ecommercenikestore.feature_auth.data.repository

import com.amirreza.ecommercenikestore.feature_auth.data.source.Tokens
import com.amirreza.ecommercenikestore.feature_auth.data.source.local.AuthLocalDataSourceI
import com.amirreza.ecommercenikestore.feature_auth.data.source.remote.AuthRemoteDataSourceI
import com.amirreza.ecommercenikestore.feature_auth.domain.model.TokenResponse
import com.amirreza.ecommercenikestore.feature_auth.domain.repository.AuthRepository
import io.reactivex.Completable

class AuthRepositoryImpl(
    private val localDataSourceI: AuthLocalDataSourceI,
    private val remoteDataSourceI: AuthRemoteDataSourceI
):AuthRepository{
    override fun login(username: String, password: String): Completable {
        return remoteDataSourceI.login(username,password).doOnSuccess {
            onSuccessLogin(it)
        }.ignoreElement()
    }

    override fun signUp(username: String, password: String): Completable {
        return remoteDataSourceI.signUp(username,password)
            .flatMap {
                remoteDataSourceI.login(username, password)
            }.doOnSuccess {
                onSuccessLogin(it)
            }.ignoreElement()
    }

    override fun loadToken() {
        return localDataSourceI.loadToken()
    }

    override fun saveToken(token: String, refreshToken: String) {
        return localDataSourceI.saveToken(token,refreshToken)
    }

    private fun onSuccessLogin(tokenResponce:TokenResponse){
        Tokens.updateTokens(tokenResponce.accessToken,tokenResponce.refreshToken)
        localDataSourceI.saveToken(tokenResponce.accessToken,tokenResponce.refreshToken)
    }
}