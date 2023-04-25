package com.easyprog.android.moviemate.di.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.domain.SearchRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatcher: DispatchersList
) : ViewModel() {

    private val _searchMovieList = MutableLiveData<Result<List<MovieMainInfo>>>()
    val searchMovieList: LiveData<Result<List<MovieMainInfo>>> = _searchMovieList

    private val _recommendedMoviesList = MutableLiveData<Result<List<MovieMainInfo>>>()
    val recommendedMoviesList: LiveData<Result<List<MovieMainInfo>>> = _recommendedMoviesList

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    fun getMovieListBySearch(searchQuery: String) {
        _searchMovieList.value = Result.LOADING
        viewModelScope.launch(dispatcher.io()) {
            val movieList = repository.getMovieListBySearch(searchQuery)
            _searchMovieList.postValue(movieList)
        }
    }

    fun getRecommendedMovies() {
        viewModelScope.launch(dispatcher.io()) {
            if (_recommendedMoviesList.value == null) {
                val recommendedMovies = repository.getRecommendedMovies()
                _recommendedMoviesList.postValue(recommendedMovies)
            }
        }
    }

    fun saveSearch(search: String) {
        _search.value = search
    }
}