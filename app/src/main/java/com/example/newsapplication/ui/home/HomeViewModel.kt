package com.example.newsapplication.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val isLoading: Boolean = false,
    val articles: List<String> = emptyList(),
    val error: String? = null
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // later: load articles from repository
        fakeLoad()
    }

    private fun fakeLoad() {
        _uiState.value = HomeUiState(
            isLoading = false,
            articles = listOf("Title 1", "Title 2", "Title 3")
        )
    }
}
