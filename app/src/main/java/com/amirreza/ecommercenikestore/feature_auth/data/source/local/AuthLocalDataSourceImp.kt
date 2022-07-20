package com.amirreza.ecommercenikestore.feature_auth.data.source.local

import android.content.SharedPreferences
import com.amirreza.ecommercenikestore.feature_auth.data.source.Tokens

class AuthLocalDataSourceImp(private val sharedPreferences: SharedPreferences):
    AuthLocalDataSourceI {
    override fun loadToken() {
        Tokens.updateTokens(
            sharedPreferences.getString("access_token", null),
            sharedPreferences.getString("refresh_token", null),
        )
    }

    override fun saveToken(token: String, refreshToken: String) {
        sharedPreferences.edit().apply{
            putString("access_token",token)
            putString("refresh_token",refreshToken)
        }.apply()
    }

}