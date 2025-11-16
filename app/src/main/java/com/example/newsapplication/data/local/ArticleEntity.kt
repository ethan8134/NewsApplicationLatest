package com.example.newsapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val content: String?,
    val url: String?,
    val imageUrl: String?,
    val publishedAt: String?,
    val lang: String?
)
