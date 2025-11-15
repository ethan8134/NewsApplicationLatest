package com.example.newsapplication.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NewsApiService(
    private val apiKey: String
) {

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getHeadlines(): NewsResponseDto {
        return client.get("https://gnews.io/api/v4/top-headlines") {
            url {
                parameters.append("category", "general")
                parameters.append("lang", "en")
                parameters.append("apikey", apiKey)
            }
        }.body()
    }
}
