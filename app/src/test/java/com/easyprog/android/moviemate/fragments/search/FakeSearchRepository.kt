package com.easyprog.android.moviemate.fragments.search

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.SearchRepository

class FakeSearchRepository : SearchRepository {

    private var movieList: List<Movie> = emptyList()
    private var error = false
    private var messageError: String = ""

    fun setMovieList(newSearchMovieList: List<Movie> = emptyList()) {
        movieList = newSearchMovieList
    }

    fun setError(messageError: String) {
        this.messageError = messageError
        error = true
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>> {
        return if (!error) {
            val filterList = if (movieList.isNotEmpty()) movieList.filter { movie ->
                movie.name.contains(searchQuery)
            } else movieList

            Result.SUCCESS(filterList)
        } else {
            Result.ERROR(messageError)
        }
    }

    override suspend fun getRecommendedMovies(): Result<List<Movie>> {
        return if (!error) {
            Result.SUCCESS(movieList)
        } else {
            Result.ERROR(messageError)
        }
    }
}