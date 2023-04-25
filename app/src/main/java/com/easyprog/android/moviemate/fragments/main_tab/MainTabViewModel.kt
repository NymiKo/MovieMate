package com.easyprog.android.moviemate.fragments.main_tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.MovieCarousel
import com.easyprog.android.moviemate.data.model.MovieMainInfo
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

    private val _carouselMovieList = MutableLiveData<Result<List<MovieCarousel>>>()
    val carouselMovieList: LiveData<Result<List<MovieCarousel>>> = _carouselMovieList

    private val _newMovieList = MutableLiveData<Result<List<MovieMainInfo>>>()
    val newMovieList: LiveData<Result<List<MovieMainInfo>>> = _newMovieList

    private val _weekendMovieList = MutableLiveData<Result<List<MovieMainInfo>>>()
    val weekendMovieList: LiveData<Result<List<MovieMainInfo>>> = _weekendMovieList

    private val _fascinatingSeriesList = MutableLiveData<Result<List<MovieMainInfo>>>()
    val fascinatingSeriesList: LiveData<Result<List<MovieMainInfo>>> = _fascinatingSeriesList

    fun initViewModel() {
        viewModelScope.launch {
            getMovieList(_carouselMovieList) { repository.getCarouselMovieList() }
            getMovieList(_newMovieList) { repository.getNewMovieList() }
            getMovieList(_weekendMovieList) { repository.getWeekendMovieList() }
            getMovieList(_fascinatingSeriesList) { repository.getFascinatingSeriesList() }
        }
    }

    private suspend fun getMovieList(
        liveData: MutableLiveData<Result<List<MovieMainInfo>>>,
        getList: suspend () -> Result<List<MovieMainInfo>>
    ) {
        if (liveData.value == null || liveData.value != emptyList<MovieMainInfo>()) {
            liveData.value = Result.LOADING
            val movieList = withContext(dispatcher.io()) { getList() }
            liveData.value = movieList
        }
    }

}