package com.easyprog.android.moviemate.fragments.movie_list

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.domain.MovieListRepository

class FakeMovieListListRepository : MovieListRepository {

    private var movieList = emptyList<MovieMainInfo>()
    private var error = false
    private var massageError: String = ""

    fun setMovieList(newMovieList: List<MovieMainInfo> = emptyList()) {
        movieList = newMovieList
    }

    fun setMovieListError(massageError: String) {
        this.massageError = massageError
        error = true
    }

    override suspend fun getMovieList(): Result<List<MovieMainInfo>> {
        return if (!error) {
            Result.SUCCESS(movieList)
        } else {
            Result.ERROR(massageError)
        }
    }
}