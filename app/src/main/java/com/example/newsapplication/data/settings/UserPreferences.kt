package com.example.newsapplication.data.settings

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val language: String = "en",
    val country: String = "us",
    val articleLimit: Int = 10,
    val autoDownload: Boolean = true
)
