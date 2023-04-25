package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.model.MovieCarousel
import com.easyprog.android.moviemate.data.model.MovieFullInfo
import com.easyprog.android.moviemate.data.model.MovieMainInfo

interface FirebaseSource {

    suspend fun getMovieList(catalog: String): Result<List<MovieMainInfo>>
    suspend fun getMovieListBySearch(searchQuery: String): Result<List<MovieFullInfo>>
    suspend fun getRecommendedMovies(): Result<List<MovieMainInfo>>
    suspend fun getMoviesByGenre(genre: String): Result<List<MovieMainInfo>>
    suspend fun getMovieInfo(idMovie: String): Result<List<MovieFullInfo>>
    suspend fun getCarouselMovieList(): Result<List<MovieCarousel>>
    suspend fun getNewMovieList(): Result<List<MovieMainInfo>>
    suspend fun getWeekendMovieList(): Result<List<MovieMainInfo>>
    suspend fun getFascinatingSeriesList(): Result<List<MovieMainInfo>>

}