package com.easyprog.android.moviemate.domain

import com.easyprog.android.moviemate.data.FirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.MovieMainInfo

class FakeFirebaseSource: FirebaseSource {

    private val movieList: MutableList<MovieMainInfo> = mutableListOf()
    private var error: Boolean = false

    fun setMovieList(newMovieList: List<MovieMainInfo> = emptyList(), error: Boolean = false) {
        this.error = error
        movieList.addAll(newMovieList)
    }

    override suspend fun getMovieList(): Result<List<MovieMainInfo>> {
        return if (!error) {
            Result.SUCCESS(movieList)
        } else {
            Result.ERROR("error")
        }
    }

    override suspend fun getRecommendedMovies(): Result<List<MovieMainInfo>> {
        return if (!error) {
             Result.SUCCESS(movieList)
        } else {
            Result.ERROR("error")
        }
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<MovieMainInfo>> {
        return if (!error) {
            val filterList = movieList.filter { movie -> movie.name.contains(searchQuery) }
            Result.SUCCESS(filterList)
        } else {
            Result.ERROR("error")
        }
    }
}