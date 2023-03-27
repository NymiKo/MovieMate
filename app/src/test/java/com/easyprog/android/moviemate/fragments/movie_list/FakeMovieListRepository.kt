package com.easyprog.android.moviemate.fragments.movie_list

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MovieRepository

class FakeMovieListRepository : MovieRepository {

    private var movieList = emptyList<Movie>()
    private var error = false

    fun setMovieList(newMovieList: List<Movie> = emptyList(), error: Boolean = false) {
        movieList = newMovieList
        this.error = error
    }

    override suspend fun getMovieList(): Result<List<Movie>> {
        return if (!error) {
            Result.SUCCESS(movieList)
        } else {
            Result.ERROR(Exception("No LIst"))
        }
    }
}