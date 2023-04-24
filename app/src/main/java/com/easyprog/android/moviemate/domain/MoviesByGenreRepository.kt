package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result

interface MoviesByGenreRepository {
    suspend fun getMoviesList(genre: String): Result<List<Movie>>
}