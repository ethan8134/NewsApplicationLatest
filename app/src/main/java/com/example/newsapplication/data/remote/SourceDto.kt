package com.example.newsapplication.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String? = null,
    val name: String? = null,
    val url: String? = null,
    val country: String? = null
)