package com.amirreza.ecommercenikestore.feature_store.common.base

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.feature_auth.presentation.AuthActivity
import com.amirreza.feature_store.common.base.ErrorType
import com.amirreza.feature_store.common.base.NikeException
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

interface NikeView{
    val rootView: CoordinatorLayout?
    val viewContext: Context?

    fun setProgressBarIndicator(mustShow:Boolean){
        rootView?.let{ root->
            viewContext?.let { context->
                var loadingView = root.findViewById<View>(R.id.viewLoading)

                if(loadingView == null && mustShow){
                    loadingView = LayoutInflater.from(context).inflate(R.layout.view_loading,root,false)
                    root.addView(loadingView)
                }
                loadingView?.visibility = if(mustShow) View.VISIBLE else View.GONE
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showError(nikeException: NikeException, path:Int){
        viewContext?.let { context->
            rootView?.let{
                when(nikeException.typeOfError){
                    ErrorType.SIMPLE -> showSnackbar(nikeException.errorMessage)
                    ErrorType.AUTH -> {
                        val intent = Intent(context, AuthActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }


    fun showSnackbar(message:String, duration:Int = Snackbar.LENGTH_SHORT){
        rootView?.let {
            Snackbar.make(it,message, duration).show()
        }
    }
}
