package com.amirreza.ecommercenikestore.data.repository

import com.amirreza.ecommercenikestore.data.http.ApiService
import com.amirreza.ecommercenikestore.data.source.comment_data_source.CommentDataSource
import com.amirreza.ecommercenikestore.domain.entity.Comment
import com.amirreza.ecommercenikestore.domain.repository.CommentRepositoryI
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