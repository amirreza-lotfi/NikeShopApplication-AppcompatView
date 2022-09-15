package com.amirreza.ecommercenikestore.features.feature_store.domain.repository

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Comment
import io.reactivex.Completable
import io.reactivex.Single

interface CommentRepositoryI {
    fun getAll(productId:Int): Single<List<Comment>>
    fun insertComment(comment: Comment):Completable
}