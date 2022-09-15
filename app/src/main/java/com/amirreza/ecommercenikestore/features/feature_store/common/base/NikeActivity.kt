package com.amirreza.ecommercenikestore.features.feature_store.common.base

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import org.greenrobot.eventbus.EventBus

abstract class NikeActivity: AppCompatActivity(),NikeView{
    override val viewContext: Context?
        get() = this
    override val rootView: CoordinatorLayout?
        get()  {
            val rootView = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if(rootView is CoordinatorLayout)
                return rootView
            else{
                rootView.children.forEach {
                    if(it is CoordinatorLayout)
                        return it
                }
                throw IllegalStateException("The root must be Coordinator Layout")
            }
        }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        Log.i("","")
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
