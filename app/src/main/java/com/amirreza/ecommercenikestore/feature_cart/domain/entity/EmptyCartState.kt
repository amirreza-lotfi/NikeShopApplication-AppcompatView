package com.amirreza.ecommercenikestore.feature_cart.domain.entity

import androidx.annotation.StringRes
import com.amirreza.ecommercenikestore.R

data class EmptyCartState(
    val mustShow:Boolean,
    @StringRes val messageResId:Int = 0,
    val mustShowActionButton: Boolean = false
)

class EmptyCartStateFactory{
    companion object{
        fun cartIsEmpty(): EmptyCartState {
            return EmptyCartState(true, R.string.cartIsEmpty,false)
        }
        fun cartIsNotEmpty(): EmptyCartState {
            return EmptyCartState(false)
        }
        fun userHasNotLoggedIn(): EmptyCartState {
            return EmptyCartState(true,R.string.pleaseLogInAccount,true)
        }
        fun userHasLoggedIn():EmptyCartState{
            return EmptyCartState(false)
        }
    }
}
