package com.amirreza.ecommercenikestore.features.feature_profile.data

import android.content.SharedPreferences
import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.ProfileRepository

class ProfileRepositoryImpl(val sharedPreferences: SharedPreferences): ProfileRepository {
    override fun getUserName(): String {
        return sharedPreferences.getString("username","")?:""
    }

    override fun logOut() {
        sharedPreferences.edit().apply {
            clear()
        }.apply()
        Tokens.userLogOut()
    }
}