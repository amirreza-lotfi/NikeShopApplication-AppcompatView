package com.amirreza.ecommercenikestore.features.feature_profile.domain.repo

interface ProfileRepository {
    fun getUserName():String
    fun logOut()
}