package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MainTabRepository
import javax.inject.Inject

class MainTabRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource
): MainTabRepository {
    override suspend fun getCarouselMovieList(): Result<List<Movie>> = firebaseSource.getCarouselMovieList()
    override suspend fun getNewMovieList(): Result<List<Movie>> = firebaseSource.getNewMovieList()
    override suspend fun getWeekendMovieList(): Result<List<Movie>> = firebaseSource.getWeekendMovieList()
    override suspend fun getFascinatingSeriesList(): Result<List<Movie>> = firebaseSource.getFascinatingSeriesList()
}