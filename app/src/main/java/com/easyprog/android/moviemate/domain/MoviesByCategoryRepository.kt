package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result

interface MoviesByCategoryRepository {
    suspend fun getMoviesList(category: String): Result<List<Movie>>
}