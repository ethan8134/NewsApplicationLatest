package com.example.newsapplication.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SettingsUiState(
    val offlineModeEnabled: Boolean = true,
    val cacheSize: String = "50 MB"
)

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    // Later: change settings, clear cache, toggle offline mode, etc.
}
