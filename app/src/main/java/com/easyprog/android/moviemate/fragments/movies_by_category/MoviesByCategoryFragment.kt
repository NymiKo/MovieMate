package com.easyprog.android.moviemate.fragments.movies_by_category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.adapters.MoviesByCategoryAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.databinding.FragmentMoviesByCategoryBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.fragments.movie_info.MovieInfoFragmentArgs
import com.easyprog.android.moviemate.utils.hideBottomNavView
import com.easyprog.android.moviemate.utils.navigateTo
import com.easyprog.android.moviemate.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesByCategoryFragment : BaseFragment<FragmentMoviesByCategoryBinding>(FragmentMoviesByCategoryBinding::inflate) {

    private val mAdapter = MoviesByCategoryAdapter()
    private val args: MoviesByCategoryFragmentArgs by navArgs()
    private val viewModel: MoviesByCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMoviesList(args.category)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        getMovieList()
    }

    private fun setupView() {
        hideBottomNavView()
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        binding.collapsingToolbar.title = args.category
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMovieList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getMovieList() {
        viewModel.moviesList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.ERROR -> {
                    showSnackBar(R.string.error_message)
                }
                Result.LOADING -> {

                }
                is Result.SUCCESS -> {
                    mAdapter.movieList = result.data
                }
            }
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}