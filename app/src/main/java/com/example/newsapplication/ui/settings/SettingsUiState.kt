package com.example.newsapplication.ui.settings

data class SettingsUiState(
    val language: String = "en",
    val country: String = "us",
    val articleLimit: Int = 10,
    val autoDownload: Boolean = true
)
