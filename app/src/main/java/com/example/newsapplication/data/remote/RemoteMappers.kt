package com.example.newsapplication.data.remote

import com.example.newsapplication.data.local.ArticleEntity
import com.example.newsapplication.data.remote.ArticleDto

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
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
