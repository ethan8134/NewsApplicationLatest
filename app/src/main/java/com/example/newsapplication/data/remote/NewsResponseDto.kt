package com.example.newsapplication.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    @SerialName("totalArticles") val totalArticles: Int,
    @SerialName("articles") val articles: List<ArticleDto>
)
