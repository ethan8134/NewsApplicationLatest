package com.example.newsapplication

import android.app.Application
import androidx.room.Room
import com.example.newsapplication.data.local.NewsDatabase

class NewsApplication : Application() {

    lateinit var database: NewsDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }
}
