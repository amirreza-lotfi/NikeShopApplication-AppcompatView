package com.amirreza.ecommercenikestore.common.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.amirreza.ecommercenikestore.R

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
}
