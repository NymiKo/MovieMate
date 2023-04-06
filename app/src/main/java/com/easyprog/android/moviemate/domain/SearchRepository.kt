package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result

interface SearchRepository {
    suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>>
    suspend fun getRecommendedMovies(): Result<List<Movie>>
}