package com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.comment_usecases

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.CommentRepositoryI
import io.reactivex.Single

class GetComments(private val commentRepositoryI: CommentRepositoryI) {
    operator fun invoke(productId:Int): Single<List<Comment>> {
        return commentRepositoryI.getAll(productId)
    }
}