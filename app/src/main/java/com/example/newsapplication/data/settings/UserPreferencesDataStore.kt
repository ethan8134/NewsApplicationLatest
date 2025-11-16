package com.example.newsapplication.data.settings

import android.content.Context
import androidx.datastore.dataStore



val Context.userPreferencesDataStore by dataStore(
    fileName = "user_prefs.json",
    serializer = UserPreferencesSerializer
)
