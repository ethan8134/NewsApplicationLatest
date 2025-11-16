package com.example.newsapplication.ui.settings

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.settings.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val dataStore: DataStore<UserPreferences>) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> =
        dataStore.data.map { prefs ->
            SettingsUiState(
                language = prefs.language,
                country = prefs.country,
                articleLimit = prefs.articleLimit,
                autoDownload = prefs.autoDownload
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, SettingsUiState())

    fun setLanguage(lang: String) = viewModelScope.launch {
        dataStore.updateData { it.copy(language = lang) }
    }

    fun setCountry(country: String) = viewModelScope.launch {
        dataStore.updateData { it.copy(country = country) }
    }

    fun setArticleLimit(limit: Int) = viewModelScope.launch {
        dataStore.updateData { it.copy(articleLimit = limit) }
    }

    fun setAutoDownload(enabled: Boolean) = viewModelScope.launch {
        dataStore.updateData { it.copy(autoDownload = enabled) }
    }
}

