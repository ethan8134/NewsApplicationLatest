package com.example.newsapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapplication.ui.article.ArticleScreen
import com.example.newsapplication.ui.home.HomeScreen
import com.example.newsapplication.ui.settings.SettingsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onArticleClick = { articleId ->
                    navController.navigate("article/$articleId")
                },
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable("article/{articleId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("articleId") ?: ""
            ArticleScreen(
                articleId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
