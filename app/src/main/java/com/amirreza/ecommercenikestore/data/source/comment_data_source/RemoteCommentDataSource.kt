package com.amirreza.ecommercenikestore.data.source.comment_data_source

import android.annotation.TargetApi
import com.amirreza.ecommercenikestore.data.http.ApiService
import com.amirreza.ecommercenikestore.domain.entity.Comment
import io.reactivex.Completable
import io.reactivex.Single

class RemoteCommentDataSource(val apiService: ApiService):CommentDataSource {
    override fun getAll(productId:Int): Single<List<Comment>> {
        return apiService.getComments(productId)
    }

    override fun insertComment(comment: Comment): Completable {
        TODO("Not yet implemented")
    }
}