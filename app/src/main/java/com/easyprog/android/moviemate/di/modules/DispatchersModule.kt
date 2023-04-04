package com.easyprog.android.moviemate.di.modules

import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun provideDispatchersList(dispatchersList: DispatchersList.Base): DispatchersList

}