package com.example.newsapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val articles: List<String> = emptyList(),
    val error: String? = null
)

class HomeViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = repository
        .getHeadlinesStream()
        .map { list -> HomeUiState(articles = list) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            HomeUiState()
        )

    fun refresh() {
        viewModelScope.launch {
            repository.refreshHeadlines()
        }
    }
}

