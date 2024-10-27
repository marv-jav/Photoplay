package com.org.photoplay.ui.navigation

sealed class NavDestinations(val route: String) {
    data object Splash : NavDestinations("splash")
    data object MovieScreen : NavDestinations("movie_list")
    data object MovieDetail : NavDestinations("movie_detail/{movieId}") {
        fun withArgs(movieId: Int) = "movie_detail/$movieId"
    }
}