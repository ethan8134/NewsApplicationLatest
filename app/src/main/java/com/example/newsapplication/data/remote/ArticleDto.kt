package com.example.newsapplication.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("id") val id: String?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("content") val content: String?,
    @SerialName("url") val url: String?,
    @SerialName("image") val imageUrl: String?,
    @SerialName("publishedAt") val publishedAt: String?,
    @SerialName("lang") val lang: String?,
    @SerialName("source") val source: SourceDto?
)
