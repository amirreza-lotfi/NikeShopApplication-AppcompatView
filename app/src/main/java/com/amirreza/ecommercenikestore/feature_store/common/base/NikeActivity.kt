package com.amirreza.ecommercenikestore.feature_store.common.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
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

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
