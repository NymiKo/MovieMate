package com.easyprog.android.moviemate.di.modules

import com.easyprog.android.moviemate.domain.MovieInfoRepository
import com.easyprog.android.moviemate.domain.MovieRepository
import com.easyprog.android.moviemate.domain.MoviesByCategoryRepository
import com.easyprog.android.moviemate.domain.SearchRepository
import com.easyprog.android.moviemate.domain.implementation.MovieInfoRepositoryImpl
import com.easyprog.android.moviemate.domain.implementation.MovieListRepositoryImpl
import com.easyprog.android.moviemate.domain.implementation.MoviesByCategoryRepositoryImpl
import com.easyprog.android.moviemate.domain.implementation.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMovieRepository(movieListRepositoryImpl: MovieListRepositoryImpl): MovieRepository

    @Singleton
    @Binds
    abstract fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Singleton
    @Binds
    abstract fun provideMoviesByCategoryRepository(moviesByCategoryRepositoryImpl: MoviesByCategoryRepositoryImpl): MoviesByCategoryRepository

    @Singleton
    @Binds
    abstract fun provideMovieInfoRepository(movieInfoRepositoryImpl: MovieInfoRepositoryImpl): MovieInfoRepository
}