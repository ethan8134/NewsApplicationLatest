package com.example.newsapplication.domain.repository

import com.example.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getHeadlinesStream(): Flow<List<Article>>

    suspend fun refreshHeadlines()

    suspend fun getArticle(id: String): Article?
}
