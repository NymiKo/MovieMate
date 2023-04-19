package com.easyprog.android.moviemate.fragments.serials

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MovieRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SerialsListViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: DispatchersList
): ViewModel() {

    private val _serialsList = MutableLiveData<Result<List<Movie>>>()
    val serialsList: LiveData<Result<List<Movie>>> = _serialsList

    fun getMovieList(catalog: String) {
        viewModelScope.launch(dispatcher.io()) {
            if (_serialsList.value == null || _serialsList.value != emptyList<Movie>()) {
                _serialsList.postValue(Result.LOADING)
                val movieList = repository.getMovieList(catalog)
                _serialsList.postValue(movieList)
            }
        }
    }
}