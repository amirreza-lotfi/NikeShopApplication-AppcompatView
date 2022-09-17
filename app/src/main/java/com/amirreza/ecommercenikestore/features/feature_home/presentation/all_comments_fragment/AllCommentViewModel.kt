package com.amirreza.ecommercenikestore.features.feature_home.presentation.all_comments_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_home.domain.useCases.CommentUseCase
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall

class AllCommentViewModel(private val productId:Int, private val commentUseCase: CommentUseCase) :
    NikeViewModel(){
    private val _commentsLiveData = MutableLiveData<List<Comment>>()
    val commentsLiveData: LiveData<List<Comment>> = _commentsLiveData

    init{
        getComments()
    }

    private fun getComments(){
        showProgressBar(true)
        commentUseCase.getAll(productId)
            .asyncIoNetworkCall()
            .doFinally {showProgressBar(false)}
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable){
                override fun onSuccess(t: List<Comment>) {
                    _commentsLiveData.value = t
                }
            })
    }


}