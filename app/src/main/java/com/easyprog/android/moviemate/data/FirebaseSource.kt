package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.models.Filters
import com.easyprog.android.moviemate.data.models.MovieCarousel
import com.easyprog.android.moviemate.data.models.MovieFullInfo
import com.easyprog.android.moviemate.data.models.MovieMainInfo

interface FirebaseSource {

    suspend fun getMovieList(catalog: String): Result<List<MovieFullInfo>>
    suspend fun getMovieListBySearch(searchQuery: String): Result<List<MovieFullInfo>>
    suspend fun getRecommendedMovies(): Result<List<MovieMainInfo>>
    suspend fun getMoviesByGenre(genre: String): Result<List<MovieMainInfo>>
    suspend fun getMovieInfo(idMovie: String): Result<List<MovieFullInfo>>
    suspend fun getCarouselMovieList(): Result<List<MovieCarousel>>
    suspend fun getNewMovieList(): Result<List<MovieMainInfo>>
    suspend fun getWeekendMovieList(): Result<List<MovieMainInfo>>
    suspend fun getFascinatingSeriesList(): Result<List<MovieMainInfo>>
    suspend fun getMoviesByFilters(filters: Filters): Result<List<MovieMainInfo>>

}