package com.org.photoplay.data.network

import com.org.photoplay.data.model.MovieDto
import com.org.photoplay.domain.model.Cast
import com.org.photoplay.domain.model.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMoviesList(): Response<MovieDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetail>

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(@Path("movie_id") movieId: Int): Response<Cast>

}