package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieFullInfo

interface MovieInfoRepository {
    suspend fun getMovieInfo(idMovie: String): Result<List<MovieFullInfo>>
}