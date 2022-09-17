package com.amirreza.ecommercenikestore.features.feature_profile.presentation.profile_fragment

import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.CartShoppingRepository
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.ProfileRepository
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import org.greenrobot.eventbus.EventBus

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val cartRepository: CartShoppingRepository,
): NikeViewModel() {
    var isSignUpped = Tokens.isTokenAvailable()
    var username:String = profileRepository.getUserName()

    fun getCartItemsNumber(numberOfItems:(Int)->Unit){
        if(Tokens.isTokenAvailable()) {
            cartRepository.getCountsOfItemsInCart()
                .asyncIoNetworkCall()
                .subscribe(object :
                    NikeSingleObserver<ProductCountInShoppingCart>(compositeDisposable) {
                    override fun onSuccess(t: ProductCountInShoppingCart) {
                        numberOfItems(t.count)
                    }
                })
        }
    }
    fun logOut(){
        profileRepository.logOut()
        isSignUpped = Tokens.isTokenAvailable()
        EventBus.getDefault().postSticky("")
    }
}