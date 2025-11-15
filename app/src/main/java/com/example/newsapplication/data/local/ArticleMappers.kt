package com.example.newsapplication.data.local

import com.example.newsapplication.domain.model.Article

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        content = content ?: "",
        imageUrl = imageUrl
    )
}

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = null,
        content = content,
        url = null,
        imageUrl = imageUrl,
        publishedAt = null
    )
}
