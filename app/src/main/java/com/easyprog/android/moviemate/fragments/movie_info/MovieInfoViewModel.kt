package com.easyprog.android.moviemate.fragments.movie_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.MovieFullInfo
import com.easyprog.android.moviemate.data.model.MovieMainInfo
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

    private val _movieInfo = MutableLiveData<Result<List<MovieFullInfo>>>()
    val movieInfo: LiveData<Result<List<MovieFullInfo>>> = _movieInfo

    fun getMovieInfo(idMovie: String) {
        _movieInfo.value = Result.LOADING
        viewModelScope.launch(dispatcher.io()) {
            val movieInfo = repository.getMovieInfo(idMovie)
            _movieInfo.postValue(movieInfo)
        }
    }

}