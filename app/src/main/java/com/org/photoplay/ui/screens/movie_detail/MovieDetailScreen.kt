package com.org.photoplay.ui.screens.movie_detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.org.photoplay.data.repository.NetworkState
import com.org.photoplay.domain.model.Cast
import com.org.photoplay.domain.model.MovieDetail
import com.org.photoplay.ui.components.CastItem
import com.org.photoplay.ui.components.CoilImage
import com.org.photoplay.ui.screens.movie_list.MoviesListViewModel
import com.org.photoplay.ui.theme.Black
import com.org.photoplay.ui.theme.Yellow

@Composable
fun MovieDetail(modifier: Modifier = Modifier) {
    val viewModel: MoviesListViewModel = hiltViewModel()
    val castState by remember { viewModel.castState }.collectAsState()
    val movieDetailsState by remember { viewModel.moviesDetailState }.collectAsState()

    Log.d("CAST_STATE", "MovieDetail: $castState")

    when (val movieState = movieDetailsState) {
        is NetworkState.Loading -> {
            CircularProgressIndicator()
        }

        is NetworkState.Error -> {
            Text(text = movieState.exception.message!!)
        }

        is NetworkState.Success -> {
            MovieContent(
                movie = movieState.data,
                castState = castState,
                modifier = modifier
            )
        }
    }
}

@Composable
fun MovieContent(
    movie: MovieDetail,
    castState: NetworkState<Cast>,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieHeader(movie = movie)
            MovieDescription(movie = movie)
            WatchButton()
            CastSection(castState = castState)
        }
    }
}

@Composable
fun MovieHeader(movie: MovieDetail) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CoilImage(
            modifier = Modifier.fillMaxSize(),
            url = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        )
        Row(
            modifier = Modifier.align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp, top = 48.dp, start = 16.dp)
            )
            Text(text = "Back")
        }
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            Text(
                text = movie.original_title,
                fontSize = 19.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun MovieDescription(movie: MovieDetail) {
    Text(
        text = movie.id.toString(),
        fontSize = 32.sp,
        modifier = Modifier.padding(top = 17.dp, bottom = 47.dp)
    )
    Text(
        text = movie.overview,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    )
}

@Composable
fun WatchButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 45.dp, end = 45.dp, bottom = 16.dp),
        onClick = { /* Add action */ },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Yellow
        )
    ) {
        Text("Watch Now", color = Black)
    }
}

@Composable
fun CastSection(castState: NetworkState<Cast>) {
    Column {
        Text(
            "Cast",
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.Start),
            fontSize = 20.sp
        )
        when (castState) {
            is NetworkState.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkState.Error -> {
                Text("Error loading cast", color = androidx.compose.ui.graphics.Color.Red)
            }

            is NetworkState.Success -> {
                LazyRow {
                    items(castState.data.cast) { cast ->
                        CastItem(url = cast.profile_path, name = cast.name)
                    }
                }
            }
        }
    }
}