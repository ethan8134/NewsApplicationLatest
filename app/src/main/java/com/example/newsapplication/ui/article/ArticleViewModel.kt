package com.example.newsapplication.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ArticleUiState(
    val isLoading: Boolean = true,
    val article: Article? = null,
    val error: String? = null
)

class ArticleViewModel(
    private val repository: NewsRepository,
    private val articleId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()

    init {
        loadArticle()
    }

    private fun loadArticle() {
        viewModelScope.launch {
            try {
                val result = repository.getArticle(articleId)
                _uiState.value = ArticleUiState(
                    isLoading = false,
                    article = result
                )
            } catch (e: Exception) {
                _uiState.value = ArticleUiState(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}
