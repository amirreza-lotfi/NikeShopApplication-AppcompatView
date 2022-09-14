package com.amirreza.ecommercenikestore.http

import com.amirreza.ecommercenikestore.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.feature_auth.domain.repository.AuthRepository
import com.amirreza.ecommercenikestore.feature_store.common.util.CLIENT_ID
import com.amirreza.ecommercenikestore.feature_store.common.util.CLIENT_SECRET
import com.google.gson.JsonObject
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Authenticator:Authenticator,KoinComponent {
    private val apiService by inject<ApiService>()
    private val authRepository by inject<AuthRepository>()

    /**
     * This method will run when response of request is 401.
     * token expired when the Tokens.token in not null. because we send token
     * but server response 401.
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        if(isTheRefreshTokenAndAccessTokenIsAvailable() && isRequestDoesNotBelongToToken(response)){
            try {
                val newToken = refreshTokenAndReturnNewToken()
                if(newToken.isEmpty())
                    return null
                return response
                    .request
                    .newBuilder()
                    .header("Authorization","Bearer ${Tokens.token}")
                    .build()
            }catch (e:Exception){

            }
        }
        return null
    }

    private fun refreshTokenAndReturnNewToken(): String {
        val request = apiService.refreshToken(
            JsonObject().apply {
                addProperty("grant_type","refresh_token")
                addProperty("refresh_token",Tokens.refreshToken)
                addProperty("client_id", CLIENT_ID)
                addProperty("client_secret", CLIENT_SECRET)
            }
        )
        val response = request.execute()
        response.body()?.let {
            Tokens.updateTokens(it.accessToken, it.refreshToken)
            authRepository.saveToken(it.accessToken,it.refreshToken)
            return it.accessToken
        }
        return ""
    }

    private fun isTheRefreshTokenAndAccessTokenIsAvailable(): Boolean {
        return Tokens.token!=null && Tokens.refreshToken!=null
    }
    private fun isRequestDoesNotBelongToToken(response: Response): Boolean {
        return !response.request.url.pathSegments.last().equals("token",false)
    }
}