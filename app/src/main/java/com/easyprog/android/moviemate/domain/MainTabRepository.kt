package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieCarousel

interface MainTabRepository {
    suspend fun getCarouselMovieList(): Result<List<MovieCarousel>>
    suspend fun getNewMovieList(): Result<List<MovieMainInfo>>
    suspend fun getWeekendMovieList(): Result<List<MovieMainInfo>>
    suspend fun getFascinatingSeriesList(): Result<List<MovieMainInfo>>
}