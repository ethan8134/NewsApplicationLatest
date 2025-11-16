package com.example.newsapplication.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.data.settings.userPreferencesDataStore
import android.content.Context

class SettingsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(
                dataStore = context.userPreferencesDataStore
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
