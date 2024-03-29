package com.amirreza.ecommercenikestore.features.feature_home.presentation

import android.util.Log
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.utils.util.hasUserLoggedInAccount
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class MainActivityViewModel(
    private val cartUseCase: CartUseCase
) : NikeViewModel() {

    fun getCartItemsCount(){
        if(hasUserLoggedInAccount()){
            cartUseCase.getItemsCount()
                .subscribeOn(Schedulers.io())
                .subscribe(object : NikeSingleObserver<ProductCountInShoppingCart>(compositeDisposable){
                    override fun onSuccess(t: ProductCountInShoppingCart) {
                        Log.i("mainActivity","eventBusss")
                        EventBus.getDefault().postSticky(t)
                    }
                })
        }
    }
}