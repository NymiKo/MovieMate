package com.easyprog.android.moviemate.fragments.movie_filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.databinding.FragmentMovieFilterBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.hideBottomNavView
import com.google.android.material.slider.RangeSlider

class MovieFilterFragment :
    BaseFragment<FragmentMovieFilterBinding>(FragmentMovieFilterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavView()
        setupView()
    }

    private fun setupView() {
        setupToolbar()
        setupSliderRating()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupSliderRating() {
        binding.sliderRating.addOnChangeListener { slider, _, _ ->
            val values = slider.values
            binding.textSelectedRating.text = getString(R.string.from_to, values[0].toInt(), values[1].toInt())
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}