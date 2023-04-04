package com.easyprog.android.moviemate.di.modules

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.FirebaseSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseSourceModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseSource(firebaseSource: FirebaseSourceImpl): FirebaseSource
}