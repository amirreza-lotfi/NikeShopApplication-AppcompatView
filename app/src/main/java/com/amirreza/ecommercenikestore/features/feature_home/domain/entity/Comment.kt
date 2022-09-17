package com.amirreza.ecommercenikestore.features.feature_home.domain.entity

data class Comment(
    val author: Author,
    val content: String,
    val date: String,
    val id: Int,
    val title: String
)