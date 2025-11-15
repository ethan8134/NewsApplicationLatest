package com.example.newsapplication.ui.article

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ArticleUiState(
    val title: String = "",
    val content: String = ""
)

class ArticleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState = _uiState.asStateFlow()

    fun loadFakeArticle() {
        _uiState.value = ArticleUiState(
            title = "Sample Article",
            content = "This is the content of your article..."
        )
    }
}
