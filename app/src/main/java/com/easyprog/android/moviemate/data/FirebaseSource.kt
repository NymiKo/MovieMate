package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.model.Movie

interface FirebaseSource {

    suspend fun getMovieList(): Result<List<Movie>>

}