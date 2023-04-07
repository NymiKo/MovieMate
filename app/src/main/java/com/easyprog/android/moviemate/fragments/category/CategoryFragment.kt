package com.easyprog.android.moviemate.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.View
import com.easyprog.android.moviemate.databinding.FragmentCategoryBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.hideBottomNavView

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        hideBottomNavView()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.collapsingToolbar.title = requireArguments().getString("category")
    }
}