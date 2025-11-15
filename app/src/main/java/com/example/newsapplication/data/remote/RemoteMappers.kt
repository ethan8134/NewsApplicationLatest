package com.example.newsapplication.data.remote

import com.example.newsapplication.domain.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        id = id ?: "",
        title = title ?: "No title",
        content = content ?: "No content",
        imageUrl = imageUrl
    )
}
