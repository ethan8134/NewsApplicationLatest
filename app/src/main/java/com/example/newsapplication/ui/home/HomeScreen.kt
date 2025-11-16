package com.example.newsapplication.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.NewsApplication
import com.example.newsapplication.R
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onArticleClick: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    val app = LocalContext.current.applicationContext as NewsApplication
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(app.container.repository)
    )

    val state by viewModel.uiState.collectAsState()

    // Trigger initial refresh when screen appears
    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Scaffold(
        topBar = {
            NewsTopBar(onSettingsClick)
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when {
                state.isLoading && state.articles.isEmpty() -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.errorMessage != null && state.articles.isEmpty() -> {
                    Text(
                        text = state.errorMessage ?: "Unknown error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    ArticlesList(
                        articles = state.articles,
                        onArticleClick = { id ->
                            onArticleClick(id)
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun ArticlesList(
    articles: List<Article>,
    onArticleClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(articles) { article ->
            ArticleCard(
                title = article.title,
                date = "",
                onClick = { onArticleClick(article.id) }
            )
        }
    }
}


data class FakeArticle(
    val title: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(onSettingsClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                "Daily News",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White
                )
            )
        },
        actions = {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Paramètres",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF5D4037), // Rouge profond style "journal"
            titleContentColor = Color.White
        )
    )
}


@Composable
fun ArticleCard(
    title: String,
    date: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Image placeholder, tu pourras la remplacer plus tard par une image API
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "Image article",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
