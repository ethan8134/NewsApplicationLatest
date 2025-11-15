package com.example.newsapplication.data.repository

import com.example.newsapplication.data.local.ArticleDao
import com.example.newsapplication.data.local.ArticleEntity
import com.example.newsapplication.data.local.toDomain
import com.example.newsapplication.data.remote.toDomain
import com.example.newsapplication.data.remote.NewsApiService
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val api: NewsApiService,
    private val dao: ArticleDao
) : NewsRepository {

    override fun getHeadlinesStream(): Flow<List<Article>> {
        return dao.getArticles().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun refreshHeadlines() {
        val response = api.getHeadlines()

        val entities = response.articles.map { dto ->
            ArticleEntity(
                id = dto.id ?: "",
                title = dto.title ?: "",
                description = dto.description,
                content = dto.content,
                url = dto.url,
                imageUrl = dto.imageUrl,
                publishedAt = dto.publishedAt
            )
        }

        dao.clearAll()
        dao.insertArticles(entities)
    }

    override suspend fun getArticle(id: String): Article? {
        return dao.getArticleById(id)?.toDomain()
    }
}
