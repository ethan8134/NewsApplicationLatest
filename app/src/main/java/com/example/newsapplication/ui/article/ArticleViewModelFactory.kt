package com.example.newsapplication.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.domain.repository.NewsRepository

class ArticleViewModelFactory(
    private val repository: NewsRepository,
    private val articleId: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(repository, articleId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
