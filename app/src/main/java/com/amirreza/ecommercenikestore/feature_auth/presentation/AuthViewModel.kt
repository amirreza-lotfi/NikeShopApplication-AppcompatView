package com.amirreza.ecommercenikestore.feature_auth.presentation

import com.amirreza.ecommercenikestore.feature_auth.domain.repository.AuthRepository
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeCompletable
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeViewModel
import com.sevenlearn.nikestore.common.asyncIoNetworkCall
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
        return authRepository.login(email, password).doFinally {
            showProgressBarWhenInBackgroundThread(false)
        }
    }
}