package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result

interface MovieRepository {

    suspend fun getMovieList(catalog: String): Result<List<Movie>>

}