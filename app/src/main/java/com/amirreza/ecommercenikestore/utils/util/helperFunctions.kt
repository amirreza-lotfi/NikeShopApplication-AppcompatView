package com.amirreza.ecommercenikestore.utils.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.features.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

fun convertDpToPixel(dp: Float, context: Context?): Float {
    return if (context != null) {
        val resources = context.resources
        val metrics = resources.displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    } else {
        val metrics = Resources.getSystem().displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

fun increaseCartBadgeCount(count:Int){
    val countOfCartItem = EventBus.getDefault().getStickyEvent(ProductCountInShoppingCart::class.java)
    countOfCartItem?.let {
        it.count += count
        EventBus.getDefault().postSticky(it)
    }
}
fun decreaseCartBadgeCount(count:Int){
    val countOfCartItem = EventBus.getDefault().getStickyEvent(ProductCountInShoppingCart::class.java)
    countOfCartItem?.let {
        it.count -= count
        EventBus.getDefault().postSticky(it)
    }
}

fun formatPrice(
    price: Number,
    label:String = ""
): String {
    var counter = 0
    val priceToString = price.toString()
    var price = ""
    var index = priceToString.length - 1

    while (index >= 0) {
        if (counter == 3) {
            price += ","
            price += priceToString[index]
            counter = 1
        } else {
            price += priceToString[index]
            counter++
        }
        index--
    }
    return price.reversed() + label
}

fun hasUserLoggedInAccount(): Boolean {
    return !Tokens.token.isNullOrBlank()
}


fun View.implementSpringAnimationTrait() {
    val scaleXAnim = SpringAnimation(this, DynamicAnimation.SCALE_X, 0.90f)
    val scaleYAnim = SpringAnimation(this, DynamicAnimation.SCALE_Y, 0.90f)

    setOnTouchListener { v, event ->
        Timber.i(event.action.toString())
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                scaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                scaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                scaleXAnim.start()

                scaleYAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                scaleYAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                scaleYAnim.start()

            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                scaleXAnim.cancel()
                scaleYAnim.cancel()
                val reverseScaleXAnim = SpringAnimation(this, DynamicAnimation.SCALE_X, 1f)
                reverseScaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                reverseScaleXAnim.start()

                val reverseScaleYAnim = SpringAnimation(this, DynamicAnimation.SCALE_Y, 1f)
                reverseScaleYAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleYAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                reverseScaleYAnim.start()


            }
        }

        false
    }
}

fun <T> Single<T>.asyncIoNetworkCall() : Single<T>{
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
fun Completable.asyncIoNetworkCall():Completable{
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
fun getVerticalLinearLayoutManager(context: Context?): RecyclerView.LayoutManager {
    return LinearLayoutManager(context,RecyclerView.VERTICAL,false)
}
fun getGridLayoutManager(context: Context?): GridLayoutManager {
    return GridLayoutManager(context, 2 )
}