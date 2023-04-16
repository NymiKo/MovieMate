package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MovieRepository
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseSource): MovieRepository {

    override suspend fun getMovieList(): Result<List<Movie>> = firebaseSource.getMovieList()
}