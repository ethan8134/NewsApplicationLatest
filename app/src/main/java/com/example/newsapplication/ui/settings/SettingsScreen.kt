package com.example.newsapplication.ui.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.ui.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current.applicationContext

    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context)
    )

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Language", style = MaterialTheme.typography.titleMedium)
            DropdownMenuSetting(
                current = state.language,
                options = listOf("en", "fr", "es", "it"),
                onSelect = { viewModel.setLanguage(it) }
            )

            Spacer(Modifier.height(16.dp))
            Text("Country", style = MaterialTheme.typography.titleMedium)
            DropdownMenuSetting(
                current = state.country,
                options = listOf("us", "fr", "gb", "ca"),
                onSelect = { viewModel.setCountry(it) }
            )

            Spacer(Modifier.height(16.dp))
            Text("Articles limit: ${state.articleLimit}")
            Slider(
                value = state.articleLimit.toFloat(),
                onValueChange = { viewModel.setArticleLimit(it.toInt()) },
                valueRange = 5f..50f
            )

            Spacer(Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Auto-download offline", Modifier.weight(1f))
                Switch(
                    checked = state.autoDownload,
                    onCheckedChange = { viewModel.setAutoDownload(it) }
                )
            }
        }
    }
}

@Composable
fun DropdownMenuSetting(current: String, options: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Text(
            text = current,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(12.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
        )
        DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it.uppercase()) },
                    onClick = {
                        expanded = false
                        onSelect(it)
                    }
                )
            }
        }
    }
}


