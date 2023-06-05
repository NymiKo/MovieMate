package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieFullInfo

interface SearchRepository {
    suspend fun getMovieListBySearch(searchQuery: String): Result<List<MovieFullInfo>>
    suspend fun getRecommendedMovies(): Result<List<MovieMainInfo>>
}