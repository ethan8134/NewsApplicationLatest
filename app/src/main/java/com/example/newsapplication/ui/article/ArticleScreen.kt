package com.example.newsapplication.ui.article

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import coil.compose.AsyncImage
import com.example.newsapplication.NewsApplication
import com.example.newsapplication.domain.model.Article
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    articleId: String,
    onBack: () -> Unit
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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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

            val article = state.article
            val error = state.error

            when {
                state.isLoading ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))

                error != null ->
                    Text(
                        text = error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                article != null ->
                    ArticleContent(article)
            }
        }
    }
}

@Composable
fun ArticleContent(article: Article) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall
            )

            article.publishedAt?.let {
                Text(
                    text = "Published: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            if (!article.description.isNullOrBlank()) {
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            if (!article.content.isNullOrBlank()) {
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, article.url?.toUri())
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Read full article")
            }
        }
    }
}
