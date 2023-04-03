package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie

class FakeFirebaseSource: FirebaseSource {

    private val movieList: MutableList<Movie> = mutableListOf()
    private var error: Boolean = false

    fun setMovieList(newMovieList: List<Movie> = emptyList(), error: Boolean = false) {
        this.error = error
        movieList.addAll(newMovieList)
    }

    override suspend fun getMovieList(): Result<List<Movie>> {
        return if (!error) {
            Result.SUCCESS<List<Movie>>(movieList)
        } else {
            Result.ERROR("error")
        }
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>> {
        return if (!error) {
            val filterList = movieList.filter { movie -> movie.name.contains(searchQuery) }
            Result.SUCCESS(filterList)
        } else {
            Result.ERROR("error")
        }
    }
}