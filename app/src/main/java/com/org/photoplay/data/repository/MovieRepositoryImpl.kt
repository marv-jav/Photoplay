package com.org.photoplay.data.repository

import com.org.photoplay.data.mapper.toMovies
import com.org.photoplay.data.network.ApiService
import com.org.photoplay.domain.model.Cast
import com.org.photoplay.domain.model.Movie
import com.org.photoplay.domain.model.MovieDetail
import com.org.photoplay.domain.repository.MovieRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository {
    override suspend fun getPopularMovies(): NetworkState<List<Movie>> {
        return try {
            val response = apiService.getPopularMoviesList()
            if (response.isSuccessful) {
                val movies = response.body()?.toMovies() ?: emptyList()
                NetworkState.Success(movies)
            } else {
                NetworkState.Error(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: HttpException) {
            NetworkState.Error(Exception("Network Error: ${e.message()}"))
        } catch (e: Exception) {
            NetworkState.Error(Exception("An unexpected error occurred: ${e.message}"))
        }
    }

    override suspend fun getCast(movieId: Int): NetworkState<Cast> {
        return try {
            val response = apiService.getCast(movieId)
            if (response.isSuccessful) {
                val cast = response.body()
                NetworkState.Success(cast!!)
            } else {
                NetworkState.Error(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: HttpException) {
            NetworkState.Error(Exception("Network Error: ${e.message()}"))
        } catch (e: Exception) {
            NetworkState.Error(Exception("An unexpected error occurred: ${e.message}"))
        }
    }

    override suspend fun getMovieDetails(movieId: Int): NetworkState<MovieDetail> {
        return try {
            val response = apiService.getMovieDetails(movieId)
            if (response.isSuccessful) {
                val movieDetails = response.body()
                NetworkState.Success(movieDetails!!)
            } else {
                NetworkState.Error(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: HttpException) {
            NetworkState.Error(Exception("Network Error: ${e.message()}"))
        } catch (e: Exception) {
            NetworkState.Error(Exception("An unexpected error occurred: ${e.message}"))
        }
    }

}