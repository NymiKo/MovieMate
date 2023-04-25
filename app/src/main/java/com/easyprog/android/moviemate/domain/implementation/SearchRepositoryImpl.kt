package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.domain.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseSource): SearchRepository {
    override suspend fun getMovieListBySearch(searchQuery: String) = firebaseSource.getMovieListBySearch(searchQuery)
    override suspend fun getRecommendedMovies(): Result<List<MovieMainInfo>> = firebaseSource.getRecommendedMovies()
}