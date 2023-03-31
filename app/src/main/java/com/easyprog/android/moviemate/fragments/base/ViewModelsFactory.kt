package com.easyprog.android.moviemate.fragments.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easyprog.android.moviemate.di.App
import com.easyprog.android.moviemate.fragments.movie_list.MovieListViewModel
import kotlinx.coroutines.Dispatchers

class ViewModelsFactory(
    private val app: App
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(app.repository, Dispatchers.IO)
            }
            else -> {
                MovieListViewModel(app.repository, Dispatchers.IO)
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelsFactory(requireContext().applicationContext as App)