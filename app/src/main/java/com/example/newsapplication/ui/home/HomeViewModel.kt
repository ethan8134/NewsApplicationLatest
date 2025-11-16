package com.example.newsapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val errorMessage: String? = null
)

class HomeViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeLocalArticles()
    }

    private fun observeLocalArticles() {
        viewModelScope.launch {
            repository
                .getHeadlinesStream()
                .collect { list ->
                    _uiState.update { current ->
                        current.copy(
                            articles = list,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                repository.refreshHeadlines()
            } catch (e: Exception) {
                e.printStackTrace()
                println("API ERROR: ${e.message}")
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        errorMessage = "Failed to refresh news"
                    )
                }
            }
        }
    }
}
