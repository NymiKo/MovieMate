package com.easyprog.android.moviemate.fragments.movies_by_genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.base.BaseActionListener
import com.easyprog.android.moviemate.adapters.movie_by_genre.MoviesByGenreAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.databinding.FragmentMoviesByGenreBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.hideBottomNavView
import com.easyprog.android.moviemate.utils.navigateTo
import com.easyprog.android.moviemate.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesByGenreFragment : BaseFragment<FragmentMoviesByGenreBinding>(FragmentMoviesByGenreBinding::inflate) {

    private val mAdapter = MoviesByGenreAdapter(object : BaseActionListener {
        override fun onMovieClick(idMovie: String) {
            navigateTo(MoviesByGenreFragmentDirections.actionMoviesByCategoryFragmentToMovieInfoFragment(idMovie))
        }
    })
    private val args: MoviesByGenreFragmentArgs by navArgs()
    private val viewModel: MoviesByGenreViewModel by viewModels()

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
                    binding.layoutProgress.visibility = View.GONE
                    showSnackBar(R.string.error_message, null)
                }
                Result.LOADING -> {
                    binding.layoutProgress.visibility = View.VISIBLE
                }
                is Result.SUCCESS -> {
                    mAdapter.movieList = result.data
                    binding.layoutProgress.visibility = View.GONE
                }
            }
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}