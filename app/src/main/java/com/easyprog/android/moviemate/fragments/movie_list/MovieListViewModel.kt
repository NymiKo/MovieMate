package com.easyprog.android.moviemate.fragments.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieList = MutableLiveData<Result<List<Movie>>>(Result.LOADING)
    val movieList: LiveData<Result<List<Movie>>> = _movieList

    fun getMovieList() {
        viewModelScope.launch {
            val movieList = withContext(Dispatchers.IO) { repository.getMovieList() }
            _movieList.value = movieList
        }
    }
}