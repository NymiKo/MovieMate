package com.easyprog.android.moviemate.di.modules

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.domain.MovieRepository
import com.easyprog.android.moviemate.domain.SearchRepository
import com.easyprog.android.moviemate.domain.implementation.MovieRepositoryImpl
import com.easyprog.android.moviemate.domain.implementation.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Singleton
    @Binds
    abstract fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}