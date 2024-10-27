package com.org.photoplay.ui.screens.movie_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.org.photoplay.data.repository.NetworkState
import com.org.photoplay.domain.model.Movie
import com.org.photoplay.ui.components.MovieItem

@Composable
fun MovieScreen(modifier: Modifier = Modifier, onNavigate: (movieId: Int) -> Unit) {
    val viewModel: MoviesListViewModel = hiltViewModel()
    val movieState by viewModel.moviesState.collectAsState()

    Log.d("MovieContentState", "castState: $movieState")

    when (val state = movieState) {
        is NetworkState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is NetworkState.Error -> {
            Log.d("NetworkErrorView", "MovieScreen: ${state.exception.message}")
            Snackbar {
                Text(state.exception.message!!)
            }
        }

        is NetworkState.Success -> {
            MoviesList(modifier, movies = state.data) { movie ->
                viewModel.getCast(movieId = movie.id)
                viewModel.getMovieDetails(movieId = movie.id)
                onNavigate(movie.id)
            }
        }
    }
}

@Composable
fun MoviesList(modifier: Modifier = Modifier, movies: List<Movie>, onItemClicked: (Movie) -> Unit) {
    Surface(modifier) {
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies) { item: Movie ->
                MovieItem(item) {
                    onItemClicked(item)
                }
            }
        }
    }
}