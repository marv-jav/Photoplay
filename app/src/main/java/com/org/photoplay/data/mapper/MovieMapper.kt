package com.org.photoplay.data.mapper

import com.org.photoplay.data.model.MovieDto
import com.org.photoplay.domain.model.Movie

fun MovieDto.toMovies(): List<Movie> {
    return results.map { movie ->
        Movie(id = movie.id, title = movie.title, poster_path = movie.poster_path)
    }
}