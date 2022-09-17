package com.amirreza.ecommercenikestore.features.feature_home.domain.useCases.comment_usecases

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.CommentRepositoryI
import io.reactivex.Single

class GetComments(private val commentRepositoryI: CommentRepositoryI) {
    operator fun invoke(productId:Int): Single<List<Comment>> {
        return commentRepositoryI.getAll(productId)
    }
}