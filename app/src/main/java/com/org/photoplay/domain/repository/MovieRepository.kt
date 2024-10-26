package com.org.photoplay.domain.repository

import com.org.photoplay.data.repository.NetworkState
import com.org.photoplay.domain.model.Cast
import com.org.photoplay.domain.model.Movie
import com.org.photoplay.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getPopularMovies(): NetworkState<List<Movie>>

    suspend fun getCast(movieId: Int): NetworkState<Cast>

    suspend fun getMovieDetails(movieId: Int): NetworkState<MovieDetail>
}