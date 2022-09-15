package com.amirreza.ecommercenikestore.features.feature_auth.presentation

import com.amirreza.ecommercenikestore.features.feature_auth.domain.repository.AuthRepository
import com.amirreza.ecommercenikestore.features.feature_store.common.base.NikeViewModel
import io.reactivex.Completable

class AuthViewModel(private val authRepository: AuthRepository): NikeViewModel() {
    fun login(email:String, password:String):Completable {
        showProgressBarWhenInBackgroundThread(true)
        return authRepository.login(email, password).doFinally{
            showProgressBarWhenInBackgroundThread(false)
        }
    }
    fun signUp(email:String, password:String):Completable {
        showProgressBarWhenInBackgroundThread(true)
        return authRepository.signUp(email, password).doFinally {
            showProgressBarWhenInBackgroundThread(false)
        }
    }
}