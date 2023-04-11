package com.easyprog.android.moviemate.fragments.movies_by_category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.MoviesByCategoryRepository
import com.easyprog.android.moviemate.fragments.base.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.easyprog.android.moviemate.data.Result

@HiltViewModel
class MoviesByCategoryViewModel @Inject constructor(
    private val repository: MoviesByCategoryRepository,
    private val dispatcher: DispatchersList
): ViewModel(){

    private val _moviesList = MutableLiveData<Result<List<Movie>>>()
    val moviesList: LiveData<Result<List<Movie>>> = _moviesList

    fun getMoviesList(category: String) {
        viewModelScope.launch(dispatcher.io()) {
            val moviesLIst = repository.getMoviesList(category)
            _moviesList.postValue(moviesLIst)
        }
    }

}