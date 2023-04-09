package com.easyprog.android.moviemate.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.moviemate.adapters.MoviesByCategoryAdapter
import com.easyprog.android.moviemate.databinding.FragmentCategoryBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.hideBottomNavView

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val mAdapter = MoviesByCategoryAdapter()

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
        binding.collapsingToolbar.title = requireArguments().getString("category")
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMovieList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}