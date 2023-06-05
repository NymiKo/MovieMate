package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieFullInfo

interface MovieListRepository {

    suspend fun getMovieList(catalog: String): Result<List<MovieFullInfo>>

}