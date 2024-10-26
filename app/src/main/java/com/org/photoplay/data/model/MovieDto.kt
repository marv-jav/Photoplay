package com.org.photoplay.data.model

import com.org.photoplay.domain.model.Movie

data class MovieDto(
    val page: Int,
    val results: List<Movie>
)