package com.amirreza.ecommercenikestore.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amirreza.ecommercenikestore.R
import io.reactivex.disposables.CompositeDisposable

interface NikeView{
    val rootView:CoordinatorLayout?
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

abstract class NikeActivity:AppCompatActivity(),NikeView{}

abstract class NikeFragment: Fragment(),NikeView{
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context

}

abstract class NikeViewModel: ViewModel(){
    protected val compositeDisposable = CompositeDisposable()
    val progressBarIndicatorLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
