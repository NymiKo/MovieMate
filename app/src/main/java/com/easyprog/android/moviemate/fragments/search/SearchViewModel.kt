package com.easyprog.android.moviemate.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.SearchRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatcher: DispatchersList
) : ViewModel() {

    private val _searchMovieList = MutableLiveData<Result<List<Movie>>>()
    val searchMovieList: LiveData<Result<List<Movie>>> = _searchMovieList

    fun getMovieListBySearch(searchQuery: String) {
        _searchMovieList.value = Result.LOADING
        viewModelScope.launch(dispatcher.io()) {
            val movieList = repository.getMovieListBySearch(searchQuery)
            _searchMovieList.postValue(movieList)
        }
    }
}