package com.amirreza.ecommercenikestore.features.feature_store.data.source.comment_data_source

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Comment
import io.reactivex.Completable
import io.reactivex.Single

interface CommentDataSource {
    fun getAll(productId:Int): Single<List<Comment>>
    fun insertComment(comment: Comment): Completable
}