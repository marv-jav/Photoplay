package com.org.photoplay.domain.usecase

import com.org.photoplay.data.repository.NetworkState
import com.org.photoplay.domain.model.Movie
import com.org.photoplay.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUC @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): NetworkState<List<Movie>> {
        return movieRepository.getPopularMovies()
    }
}