package com.easyprog.android.moviemate.fragments.category

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.moviemate.adapters.MoviesByCategoryAdapter
import com.easyprog.android.moviemate.databinding.FragmentMoviesByCategoryBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.hideBottomNavView

class MoviesByCategoryFragment : BaseFragment<FragmentMoviesByCategoryBinding>(FragmentMoviesByCategoryBinding::inflate) {

    private val mAdapter = MoviesByCategoryAdapter()
    private val args: MoviesByCategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        hideBottomNavView()
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        binding.collapsingToolbar.title = args.category
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMovieList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}