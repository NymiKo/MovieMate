package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result

interface MainTabRepository {
    suspend fun getCarouselMovieList(): Result<List<Movie>>
}