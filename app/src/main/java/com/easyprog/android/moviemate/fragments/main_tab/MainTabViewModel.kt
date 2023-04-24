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
import kotlinx.coroutines.withContext
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

    private val _weekendMovieList = MutableLiveData<Result<List<Movie>>>()
    val weekendMovieList: LiveData<Result<List<Movie>>> = _weekendMovieList

    private val _fascinatingSeriesList = MutableLiveData<Result<List<Movie>>>()
    val fascinatingSeriesList: LiveData<Result<List<Movie>>> = _fascinatingSeriesList

    fun initViewModel() {
        viewModelScope.launch {
            getMovieList(_movieList) { repository.getCarouselMovieList() }
            getMovieList(_newMovieList) { repository.getNewMovieList() }
            getMovieList(_weekendMovieList) { repository.getWeekendMovieList() }
            getMovieList(_fascinatingSeriesList) { repository.getFascinatingSeriesList() }
        }
    }

    private suspend fun getMovieList(
        liveData: MutableLiveData<Result<List<Movie>>>,
        getList: suspend () -> Result<List<Movie>>
    ) {
        if (liveData.value == null || liveData.value != emptyList<Movie>()) {
            liveData.value = Result.LOADING
            val movieList = withContext(dispatcher.io()) { getList() }
            liveData.value = movieList
        }
    }

}