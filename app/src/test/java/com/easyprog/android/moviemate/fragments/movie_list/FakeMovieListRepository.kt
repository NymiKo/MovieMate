package com.easyprog.android.moviemate.fragments.movie_list

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MovieRepository

class FakeMovieListRepository : MovieRepository {

    private var movieList = emptyList<Movie>()
    private var error = false
    private var massageError: Exception = Exception("")

    fun setMovieList(newMovieList: List<Movie> = emptyList()) {
        movieList = newMovieList
    }

    fun setMovieListError(massageError: Exception) {
        this.massageError = massageError
        error = true
    }

    override suspend fun getMovieList(): Result<List<Movie>> {
        return if (!error) {
            Result.SUCCESS(movieList)
        } else {
            Result.ERROR(massageError)
        }
    }
}