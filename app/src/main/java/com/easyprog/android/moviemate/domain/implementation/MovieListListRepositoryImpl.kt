package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieFullInfo
import com.easyprog.android.moviemate.domain.MovieListRepository
import javax.inject.Inject

class MovieListListRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseSource): MovieListRepository {

    override suspend fun getMovieList(catalog: String): Result<List<MovieFullInfo>> = firebaseSource.getMovieList(catalog)
}