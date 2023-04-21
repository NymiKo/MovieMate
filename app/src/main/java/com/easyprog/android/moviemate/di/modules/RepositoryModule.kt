package com.easyprog.android.moviemate.di.modules

import com.easyprog.android.moviemate.domain.*
import com.easyprog.android.moviemate.domain.implementation.*
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

    @Singleton
    @Binds
    abstract fun provideMainTabRepository(mainTabRepositoryImpl: MainTabRepositoryImpl): MainTabRepository
}