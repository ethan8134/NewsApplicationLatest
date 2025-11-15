package com.example.newsapplication.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?,
    @SerialName("country") val country: String?
)
