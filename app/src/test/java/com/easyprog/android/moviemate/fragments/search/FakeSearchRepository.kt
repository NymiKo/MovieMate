package com.easyprog.android.moviemate.fragments.search

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.SearchRepository

class FakeSearchRepository: SearchRepository {

    private var searchMovieList = emptyList<Movie>()
    private var error = false
    private var messageError: Exception = Exception()

    fun setSearchMovieList(newSearchMovieList: List<Movie> = emptyList()) {
        searchMovieList = newSearchMovieList
    }

    fun setError(messageError: Exception) {
        this.messageError = messageError
        error = true
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>> {
        return if (!error) {
            val filterList = searchMovieList.filter { movie -> movie.name.contains(searchQuery) }
            Result.SUCCESS(filterList)
        } else {
            Result.ERROR(messageError)
        }
    }
}