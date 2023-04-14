package com.easyprog.android.moviemate.fragments.movie_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MovieInfoRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repository: MovieInfoRepository,
    private val dispatcher: DispatchersList
): ViewModel() {

    private val _movieInfo = MutableLiveData<Result<List<Movie>>>()
    val movieInfo: LiveData<Result<List<Movie>>> = _movieInfo

    fun getMovieInfo(idMovie: String) {
        viewModelScope.launch(dispatcher.io()) {
            val movieInfo = repository.getMovieInfo(idMovie)
            _movieInfo.postValue(movieInfo)
        }
    }

}