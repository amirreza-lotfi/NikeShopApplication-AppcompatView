package com.amirreza.ecommercenikestore.features.feature_profile.domain

interface ProfileRepository {
    fun getUserName():String
    fun logOut()
}