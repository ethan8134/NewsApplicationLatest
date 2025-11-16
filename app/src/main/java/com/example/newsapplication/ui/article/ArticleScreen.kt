package com.example.newsapplication.ui.article

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.NewsApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    articleId: String,
    onBack: () -> Unit,
) {
    val app = LocalContext.current.applicationContext as NewsApplication

    val viewModel: ArticleViewModel = viewModel(
        factory = ArticleViewModelFactory(app.container.repository, articleId)
    )

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Article") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    val error = state.error
                    Text(
                        text = error ?: "Error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    val article = state.article
                    if (article != null) {
                        ArticleContent(article)
                    }
                }
            }

        }
    }
}

@Composable
fun ArticleContent(article: com.example.newsapplication.domain.model.Article) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = article.title, style = MaterialTheme.typography.titleLarge)
        Text(text = article.content ?: "No content available")
    }
}
