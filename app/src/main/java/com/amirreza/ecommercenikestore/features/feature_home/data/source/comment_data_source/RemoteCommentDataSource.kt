package com.amirreza.ecommercenikestore.features.feature_home.data.source.comment_data_source

import com.amirreza.ecommercenikestore.utils.http.ApiService
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import io.reactivex.Completable
import io.reactivex.Single

class RemoteCommentDataSource(private val apiService: ApiService):CommentDataSource {
    override fun getAll(productId:Int): Single<List<Comment>> {
        return apiService.getComments(productId)
    }

    override fun insertComment(comment: Comment): Completable {
        return Completable.complete()
    }
}