package com.example.newsapplication.data.local

import com.example.newsapplication.data.local.ArticleEntity
import com.example.newsapplication.domain.model.Article

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        description = description,
        content = content,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        lang = lang
    )
}
