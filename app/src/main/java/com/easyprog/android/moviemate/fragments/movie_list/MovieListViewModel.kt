package com.easyprog.android.moviemate.fragments.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieFullInfo
import com.easyprog.android.moviemate.domain.MovieListRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieListRepository,
    private val dispatcher: DispatchersList
) : ViewModel() {

    private val _movieList = MutableLiveData<Result<List<MovieFullInfo>>>()
    val movieList: LiveData<Result<List<MovieFullInfo>>> = _movieList

    fun getMovieList(catalog: String) {
        viewModelScope.launch(dispatcher.io()) {
            if (_movieList.value == null || _movieList.value != emptyList<MovieFullInfo>()) {
                _movieList.postValue(Result.LOADING)
                val movieList = repository.getMovieList(catalog)
                _movieList.postValue(movieList)
            }
        }
    }
}