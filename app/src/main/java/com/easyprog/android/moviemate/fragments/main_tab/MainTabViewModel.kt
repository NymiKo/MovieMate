package com.easyprog.android.moviemate.fragments.main_tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MainTabRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTabViewModel @Inject constructor(
    private val repository: MainTabRepository,
    private val dispatcher: DispatchersList
): ViewModel() {

    private val _movieList = MutableLiveData<Result<List<Movie>>>()
    val movieList: LiveData<Result<List<Movie>>> = _movieList

    private val _newMovieList = MutableLiveData<Result<List<Movie>>>()
    val newMovieList: LiveData<Result<List<Movie>>> = _newMovieList

    fun getCarouselMovieList() {
        viewModelScope.launch(dispatcher.io()) {
            if (_movieList.value == null || _movieList.value != emptyList<Movie>()) {
                _movieList.postValue(Result.LOADING)
                val movieList = repository.getCarouselMovieList()
                _movieList.postValue(movieList)
            }
        }
    }

    fun getNewMovieList() {
        viewModelScope.launch(dispatcher.io()) {
            if (_newMovieList.value == null || _newMovieList.value != emptyList<Movie>()) {
                _newMovieList.postValue(Result.LOADING)
                val movieList = repository.getNewMovieList()
                _newMovieList.postValue(movieList)
            }
        }
    }

}