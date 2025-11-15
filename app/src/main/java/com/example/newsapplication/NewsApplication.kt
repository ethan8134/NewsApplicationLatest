package com.example.newsapplication

import android.app.Application
import androidx.room.Room
import com.example.newsapplication.data.AppContainer
import com.example.newsapplication.data.local.NewsDatabase

class NewsApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(this)
    }
}
