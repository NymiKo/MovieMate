package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieCarousel
import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.domain.MainTabRepository
import javax.inject.Inject

class MainTabRepositoryImpl @Inject constructor(
    private val firebaseSource: FirebaseSource
): MainTabRepository {
    override suspend fun getCarouselMovieList(): Result<List<MovieCarousel>> = firebaseSource.getCarouselMovieList()
    override suspend fun getNewMovieList(): Result<List<MovieMainInfo>> = firebaseSource.getNewMovieList()
    override suspend fun getWeekendMovieList(): Result<List<MovieMainInfo>> = firebaseSource.getWeekendMovieList()
    override suspend fun getFascinatingSeriesList(): Result<List<MovieMainInfo>> = firebaseSource.getFascinatingSeriesList()
}