package com.example.newsapplication.domain.model

data class Article(
    val id: String,
    val title: String,
    val content: String,
    val imageUrl: String?
)
