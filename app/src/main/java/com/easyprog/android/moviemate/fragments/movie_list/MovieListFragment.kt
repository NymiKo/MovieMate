package com.easyprog.android.moviemate.fragments.movie_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.adapters.movie_list.MovieListAdapter
import com.easyprog.android.moviemate.databinding.FragmentMovieListBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.navigateTo
import com.easyprog.android.moviemate.utils.showBottomNavView
import com.easyprog.android.moviemate.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment :
    BaseFragment<FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {

    private var mAdapter = MovieListAdapter(object : BaseActionListener {
        override fun onMovieClick(idMovie: String) {
            navigateTo(MovieListFragmentDirections.actionMovieListFragmentToMovieInfoFragment(idMovie))
        }
    })

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        getMovieList()
    }

    override fun onStart() {
        super.onStart()
        showBottomNavView()
    }

    private fun setupView() {
        setupRecyclerView()
        setupButtonRepeat()
    }

    private fun setupButtonRepeat() {
        binding.buttonRepeat.setOnClickListener {
            viewModel.getMovieList()
        }
    }

    private fun getMovieList() {
        viewModel.movieList.observe(viewLifecycleOwner) { result ->
            when(result) {
                Result.LOADING -> {

                }
                is Result.ERROR -> {
                    hideProgressBar()
                    showButtonRepeat()
                    showSnackBar(R.string.error_message)
                }
                is Result.SUCCESS -> {
                    mAdapter.movieList = result.data
                    hideButtonRepeat()
                    hideProgressBar()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMovieList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }
    }

    private fun hideProgressBar() {
        binding.frameLayoutProgress.visibility = View.GONE
    }

    private fun showButtonRepeat() {
        binding.buttonRepeat.visibility = View.VISIBLE
    }

    private fun hideButtonRepeat() {
        binding.buttonRepeat.visibility = View.GONE
    }
}