package com.easyprog.android.moviemate.di

import android.app.Application
import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.FirebaseSourceImpl
import com.easyprog.android.moviemate.domain.MovieRepository
import com.easyprog.android.moviemate.domain.SearchRepository
import com.easyprog.android.moviemate.domain.implementation.MovieRepositoryImpl
import com.easyprog.android.moviemate.domain.implementation.SearchRepositoryImpl
import com.google.android.material.color.DynamicColors
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {


    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        FirebaseApp.initializeApp(applicationContext)
    }
}