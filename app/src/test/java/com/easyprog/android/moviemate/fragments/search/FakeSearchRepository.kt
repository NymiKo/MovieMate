package com.easyprog.android.moviemate.fragments.search

import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.SearchRepository

class FakeSearchRepository : SearchRepository {

    private var searchMovieList: List<Movie> = emptyList()
    private var error = false
    private var messageError: String = ""

    fun setSearchMovieList(newSearchMovieList: List<Movie> = emptyList()) {
        searchMovieList = newSearchMovieList
    }

    fun setError(messageError: String) {
        this.messageError = messageError
        error = true
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>> {
        return if (!error) {
            val filterList = if (searchMovieList.isNotEmpty()) searchMovieList.filter { movie ->
                movie.name.contains(searchQuery)
            } else searchMovieList

            Result.SUCCESS(filterList)
        } else {
            Result.ERROR(messageError)
        }
    }
}