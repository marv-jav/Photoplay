package com.org.photoplay.ui.screens.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.photoplay.data.repository.NetworkState
import com.org.photoplay.domain.model.Cast
import com.org.photoplay.domain.model.Movie
import com.org.photoplay.domain.model.MovieDetail
import com.org.photoplay.domain.repository.MovieRepository
import com.org.photoplay.domain.usecase.GetPopularMoviesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getPopularMoviesUC: GetPopularMoviesUC,
    private val castRepo: MovieRepository
) :
    ViewModel() {

    private val _moviesState = MutableStateFlow<NetworkState<List<Movie>>>(NetworkState.Loading)
    val moviesState: StateFlow<NetworkState<List<Movie>>> get() = _moviesState

    private val _castState = MutableStateFlow<NetworkState<Cast>>(NetworkState.Loading)
    val castState: StateFlow<NetworkState<Cast>> get() = _castState

    private val _moviesDetailState =
        MutableStateFlow<NetworkState<MovieDetail>>(NetworkState.Loading)
    val moviesDetailState: StateFlow<NetworkState<MovieDetail>> get() = _moviesDetailState

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            _moviesState.value = NetworkState.Loading
            _moviesState.value = getPopularMoviesUC()
        }
    }

    fun getCast(movieId: Int) {
        viewModelScope.launch {
            _castState.value = NetworkState.Loading
            _castState.value = castRepo.getCast(movieId)
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _moviesDetailState.value = NetworkState.Loading
            _moviesDetailState.value = castRepo.getMovieDetails(movieId)
        }
    }

}