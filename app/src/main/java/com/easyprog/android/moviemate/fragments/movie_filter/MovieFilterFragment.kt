package com.easyprog.android.moviemate.fragments.movie_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyprog.android.moviemate.databinding.FragmentMovieFilterBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.hideBottomNavView

class MovieFilterFragment :
    BaseFragment<FragmentMovieFilterBinding>(FragmentMovieFilterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavView()
    }
}