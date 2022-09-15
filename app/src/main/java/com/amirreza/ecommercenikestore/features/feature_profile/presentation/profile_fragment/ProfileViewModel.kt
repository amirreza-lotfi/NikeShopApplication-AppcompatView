package com.amirreza.ecommercenikestore.features.feature_profile.presentation.profile_fragment

import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.features.feature_profile.domain.ProfileRepository
import com.amirreza.ecommercenikestore.features.feature_store.common.base.NikeViewModel
import org.greenrobot.eventbus.EventBus

class ProfileViewModel(
    private val profileRepository: ProfileRepository
):NikeViewModel() {
    val isSignUpped = Tokens.isTokenAvailable()
    var username:String = profileRepository.getUserName()

    fun logOut(){
        profileRepository.logOut()
        EventBus.getDefault().postSticky("")
    }
}