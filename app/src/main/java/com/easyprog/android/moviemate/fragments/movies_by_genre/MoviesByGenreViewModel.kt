package com.easyprog.android.moviemate.fragments.movies_by_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.domain.MoviesByGenreRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.easyprog.android.moviemate.data.Result

@HiltViewModel
class MoviesByGenreViewModel @Inject constructor(
    private val repository: MoviesByGenreRepository,
    private val dispatcher: DispatchersList
): ViewModel(){

    private val _moviesList = MutableLiveData<Result<List<MovieMainInfo>>>()
    val moviesList: LiveData<Result<List<MovieMainInfo>>> = _moviesList

    fun getMoviesList(category: String) {
        viewModelScope.launch(dispatcher.io()) {
            val moviesLIst = repository.getMoviesListByGenre(category)
            _moviesList.postValue(moviesLIst)
        }
    }

}