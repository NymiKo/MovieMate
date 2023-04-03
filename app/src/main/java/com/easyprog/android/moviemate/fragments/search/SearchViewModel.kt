package com.easyprog.android.moviemate.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchMovieList = MutableLiveData<Result<List<Movie>>>()
    val searchMovieList: LiveData<Result<List<Movie>>> = _searchMovieList

    fun getMovieListBySearch(searchQuery: String) {
        _searchMovieList.value = Result.LOADING
        viewModelScope.launch(dispatcher) {
            val movieList = repository.getMovieListBySearch(searchQuery)
            _searchMovieList.postValue(movieList)
        }
    }
}