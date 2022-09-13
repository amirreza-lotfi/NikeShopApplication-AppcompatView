package com.amirreza.ecommercenikestore.feature_store.presentation

import android.util.Log
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.feature_cart.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeViewModel
import com.sevenlearn.nikestore.common.hasUserLoggedInAccount
import io.reactivex.SingleObserver
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