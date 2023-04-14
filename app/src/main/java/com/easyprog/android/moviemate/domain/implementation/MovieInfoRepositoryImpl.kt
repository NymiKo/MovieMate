package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MovieInfoRepository
import javax.inject.Inject

class MovieInfoRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource
): MovieInfoRepository {
    override suspend fun getMovieInfo(idMovie: String): Result<List<Movie>> = firebaseSource.getMovieInfo(idMovie)
}