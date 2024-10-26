package com.org.photoplay.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.org.photoplay.ui.navigation.NavDestinations
import com.org.photoplay.ui.screens.Splash
import com.org.photoplay.ui.screens.movie_detail.MovieDetail
import com.org.photoplay.ui.screens.movie_list.MovieScreen
import com.org.photoplay.ui.theme.PhotoplayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoplayApp()
        }
    }
}

@Composable
fun PhotoplayApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    PhotoplayTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavDestinations.Splash.route,
                modifier = modifier.padding(innerPadding)
            ) {
                composable(NavDestinations.Splash.route) {
                    Splash { navController.navigate(NavDestinations.MovieScreen.route) }
                }
                composable(NavDestinations.MovieScreen.route) {
                    MovieScreen {
                        navController.navigate(NavDestinations.MovieDetail.route)
                    }
                }
                composable(NavDestinations.MovieDetail.route) {
                    MovieDetail()
                }
            }
        }
    }
}