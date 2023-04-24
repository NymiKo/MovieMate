package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MoviesByGenreRepository
import com.easyprog.android.moviemate.data.Result
import javax.inject.Inject

class MoviesByGenreRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource
): MoviesByGenreRepository {
    override suspend fun getMoviesList(genre: String): Result<List<Movie>> = firebaseSource.getMoviesByGenre(genre)
}