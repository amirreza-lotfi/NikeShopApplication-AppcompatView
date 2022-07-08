package com.amirreza.ecommercenikestore.domain.entity

data class Comment(
    val author: Author,
    val content: String,
    val date: String,
    val id: Int,
    val title: String
)