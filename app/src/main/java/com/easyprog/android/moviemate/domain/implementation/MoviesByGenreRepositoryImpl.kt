package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.domain.MoviesByGenreRepository
import com.easyprog.android.moviemate.data.Result
import javax.inject.Inject

class MoviesByGenreRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource
): MoviesByGenreRepository {
    override suspend fun getMoviesListByGenre(genre: String): Result<List<MovieMainInfo>> = firebaseSource.getMoviesByGenre(genre)
}