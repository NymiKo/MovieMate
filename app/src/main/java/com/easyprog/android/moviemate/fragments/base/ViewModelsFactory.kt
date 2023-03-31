package com.easyprog.android.moviemate.fragments.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easyprog.android.moviemate.di.App
import com.easyprog.android.moviemate.fragments.movie_list.MovieListViewModel
import com.easyprog.android.moviemate.fragments.search.SearchViewModel
import kotlinx.coroutines.Dispatchers

class ViewModelsFactory(
    private val app: App
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(app.movieRepository, Dispatchers.IO)
            }
            SearchViewModel::class.java -> {
                SearchViewModel(app.searchRepository, Dispatchers.IO)
            }
            else -> {
                MovieListViewModel(app.movieRepository, Dispatchers.IO)
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelsFactory(requireContext().applicationContext as App)