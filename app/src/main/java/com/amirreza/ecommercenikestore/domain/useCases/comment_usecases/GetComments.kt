package com.amirreza.ecommercenikestore.domain.useCases.comment_usecases

import com.amirreza.ecommercenikestore.domain.entity.Comment
import com.amirreza.ecommercenikestore.domain.repository.CommentRepositoryI
import io.reactivex.Single

class GetComments(private val commentRepositoryI: CommentRepositoryI) {
    operator fun invoke(productId:Int): Single<List<Comment>> {
        return commentRepositoryI.getAll(productId)
    }
}