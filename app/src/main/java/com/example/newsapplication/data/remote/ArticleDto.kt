package com.example.newsapplication.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: String,
    val title: String,
    val description: String? = null,
    val content: String? = null,
    val url: String? = null,
    @SerialName("image") val imageUrl: String? = null,
    val publishedAt: String? = null,
    val lang: String? = null,
    val source: SourceDto
)






