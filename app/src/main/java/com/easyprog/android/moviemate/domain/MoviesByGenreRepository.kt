package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.data.Result

interface MoviesByGenreRepository {
    suspend fun getMoviesListByGenre(genre: String): Result<List<MovieMainInfo>>
}