package com.example.newsapplication.data

import android.content.Context
import com.example.newsapplication.data.local.NewsDatabase
import com.example.newsapplication.data.remote.NewsApiService
import com.example.newsapplication.data.repository.NewsRepositoryImpl
import com.example.newsapplication.domain.repository.NewsRepository

class AppContainer(context: Context) {

    private val apiService = NewsApiService(
        apiKey = "93aad5d61ee380c2d59f2e6d6f864f33"
    )

    private val database = NewsDatabase.getInstance(context)

    val repository: NewsRepository by lazy {
        NewsRepositoryImpl(
            api = apiService,
            dao = database.articleDao()
        )
    }
}
