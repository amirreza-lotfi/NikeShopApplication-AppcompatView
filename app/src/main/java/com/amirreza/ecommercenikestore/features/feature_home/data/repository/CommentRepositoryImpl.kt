package com.amirreza.ecommercenikestore.features.feature_home.data.repository

import com.amirreza.ecommercenikestore.features.feature_home.data.source.comment_data_source.CommentDataSource
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.CommentRepositoryI
import io.reactivex.Completable
import io.reactivex.Single

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource):CommentRepositoryI {
    override fun getAll(productId:Int): Single<List<Comment>> {
        return commentDataSource.getAll(productId)
    }

    override fun insertComment(comment: Comment): Completable {
        return commentDataSource.insertComment(comment)
    }
}