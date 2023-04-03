package com.easyprog.android.moviemate.fragments.movie_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.adapters.MovieListAdapter
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.FragmentMovieListBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.fragments.base.factory
import com.easyprog.android.moviemate.utils.showSnackBar

class MovieListFragment :
    BaseFragment<FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {

    private var mAdapter = MovieListAdapter()

    private val viewModel: MovieListViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieList()
    }

    private fun getMovieList() {
        viewModel.movieList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.LOADING -> {

                }
                is Result.ERROR -> {
                    hideProgressBar()
                    showSnackBar(R.string.error_message)
                }
                is Result.SUCCESS -> {
                    setupRecyclerView(result.data)
                    hideProgressBar()
                }
            }
        }
    }

    private fun setupRecyclerView(movieList: List<Movie>) {
        mAdapter.movieList = movieList
        binding.recyclerViewMovieList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }
    }

    private fun hideProgressBar() {
        binding.frameLayoutProgress.visibility = View.GONE
    }
}