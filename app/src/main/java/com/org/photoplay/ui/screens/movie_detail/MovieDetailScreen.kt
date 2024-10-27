package com.org.photoplay.ui.screens.movie_detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.org.photoplay.R
import com.org.photoplay.data.repository.NetworkState
import com.org.photoplay.domain.model.Cast
import com.org.photoplay.domain.model.CastMember
import com.org.photoplay.domain.model.MovieDetail
import com.org.photoplay.ui.components.CastItem
import com.org.photoplay.ui.components.CoilImage
import com.org.photoplay.ui.screens.movie_list.MoviesListViewModel
import com.org.photoplay.ui.theme.Black
import com.org.photoplay.ui.theme.Yellow
import com.org.photoplay.utils.isLoading

@Composable
fun MovieDetail(modifier: Modifier = Modifier, movieId: Int) {
    val viewModel: MoviesListViewModel = hiltViewModel()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
        viewModel.getCast(movieId)
    }

    val castState by viewModel.castState.collectAsState()
    val movieDetailsState by viewModel.moviesDetailState.collectAsState()

    MovieContent(modifier = modifier, movieDetailState = movieDetailsState, castState = castState)
}

@Composable
fun MovieContent(
    movieDetailState: NetworkState<MovieDetail>,
    castState: NetworkState<Cast>,
    modifier: Modifier = Modifier
) {
    Log.d("MovieContent", "movieDetailState: $movieDetailState")
    Log.d("MovieContent", "castState: $castState")

    val isLoading = isLoading(movieDetailState, castState)

    Surface(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = {
                item {
                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        when (movieDetailState) {
                            is NetworkState.Success -> {
                                MovieHeader(movieDetail = movieDetailState.data)
                                MovieDescription(movieDetail = movieDetailState.data)
                            }

                            is NetworkState.Error -> {
                            }

                            NetworkState.Loading -> {
                            }
                        }
                        WatchButton()
                        when (castState) {
                            is NetworkState.Success -> {
                                CastSection(casts = castState.data.cast)
                            }

                            is NetworkState.Error -> {

                            }

                            NetworkState.Loading -> {

                            }
                        }
                    }
                }
            })
    }
}

@Composable
fun MovieHeader(movieDetail: MovieDetail) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        CoilImage(
            modifier = Modifier.fillMaxSize(),
            url = "https://image.tmdb.org/t/p/w500${movieDetail.poster_path}"
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .wrapContentSize()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.left_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 8.dp, top = 48.dp, start = 16.dp),
                tint = Color.White
            )
            Text(text = "Back", fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            Text(
                text = movieDetail.original_title,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun MovieDescription(movieDetail: MovieDetail) {
    Text("Popularity", modifier = Modifier.padding(top = 17.dp, bottom = 8.dp))
    Text(
        text = movieDetail.popularity.toString(),
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 20.dp),
        fontWeight = FontWeight.Bold
    )
    Text(
        text = movieDetail.overview,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        textAlign = TextAlign.Justify
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
fun CastSection(casts: List<CastMember>) {
    Column {
        Text(
            "Cast",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 20.dp)
                .align(Alignment.Start),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(casts) { cast ->
                CastItem(url = cast.profile_path, name = cast.name)
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieDetailPrev() {

}