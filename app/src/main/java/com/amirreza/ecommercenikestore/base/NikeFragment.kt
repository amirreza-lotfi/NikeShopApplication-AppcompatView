package com.amirreza.ecommercenikestore.base

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment

abstract class NikeFragment: Fragment(),NikeView{
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context

}