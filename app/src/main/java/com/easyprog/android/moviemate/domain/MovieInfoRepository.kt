package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result

interface MovieInfoRepository {
    suspend fun getMovieInfo(idMovie: String): Result<Movie>
}