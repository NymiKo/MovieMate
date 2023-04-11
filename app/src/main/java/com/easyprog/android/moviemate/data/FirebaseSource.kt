package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.model.Movie

interface FirebaseSource {

    suspend fun getMovieList(): Result<List<Movie>>
    suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>>
    suspend fun getRecommendedMovies(): Result<List<Movie>>
    suspend fun getMoviesByCategory(category: String): Result<List<Movie>>

}