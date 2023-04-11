package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MoviesByCategoryRepository
import com.easyprog.android.moviemate.data.Result
import javax.inject.Inject

class MoviesByCategoryRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource
): MoviesByCategoryRepository {
    override suspend fun getMoviesList(category: String): Result<List<Movie>> = firebaseSource.getMoviesByCategory(category)
}