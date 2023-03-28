package com.easyprog.android.moviemate.fragments.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.domain.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieList = MutableLiveData<Result<List<Movie>>>()
    val movieList: LiveData<Result<List<Movie>>> = _movieList

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {
        val movieList = repository.getMovieList()
        _movieList.value = movieList
    }

}