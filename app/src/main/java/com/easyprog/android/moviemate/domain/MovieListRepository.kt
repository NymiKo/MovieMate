package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.data.Result

interface MovieListRepository {

    suspend fun getMovieList(catalog: String): Result<List<MovieMainInfo>>

}