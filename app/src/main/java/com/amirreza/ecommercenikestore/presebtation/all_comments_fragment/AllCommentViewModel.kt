package com.amirreza.ecommercenikestore.presebtation.all_comments_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.domain.entity.Comment
import com.amirreza.ecommercenikestore.domain.useCases.CommentUseCase
import com.sevenlearn.nikestore.common.asyncIoNetworkCall

class AllCommentViewModel(private val productId:Int, private val commentUseCase: CommentUseCase) :NikeViewModel(){
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