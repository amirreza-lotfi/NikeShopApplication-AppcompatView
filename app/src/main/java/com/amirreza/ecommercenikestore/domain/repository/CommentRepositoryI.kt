package com.amirreza.ecommercenikestore.domain.repository

import com.amirreza.ecommercenikestore.domain.entity.Comment
import io.reactivex.Completable
import io.reactivex.Single

interface CommentRepositoryI {
    fun getAll(productId:Int): Single<List<Comment>>
    fun insertComment(comment: Comment):Completable
}