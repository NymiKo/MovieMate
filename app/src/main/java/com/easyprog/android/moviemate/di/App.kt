package com.easyprog.android.moviemate.di

import android.app.Application
import com.easyprog.android.moviemate.data.FirebaseSourceImpl
import com.easyprog.android.moviemate.domain.MovieRepository
import com.easyprog.android.moviemate.domain.SearchRepository
import com.easyprog.android.moviemate.domain.implementation.MovieRepositoryImpl
import com.easyprog.android.moviemate.domain.implementation.SearchRepositoryImpl
import com.google.android.material.color.DynamicColors
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase

class App: Application() {

    lateinit var movieRepository: MovieRepository
    lateinit var searchRepository: SearchRepository
    private val firebase = FirebaseSourceImpl()

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        FirebaseApp.initializeApp(applicationContext)
        movieRepository = MovieRepositoryImpl(firebase)
        searchRepository = SearchRepositoryImpl(firebase)
    }
}