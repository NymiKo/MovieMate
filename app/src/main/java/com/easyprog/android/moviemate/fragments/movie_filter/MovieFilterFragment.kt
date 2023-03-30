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

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavView()
    }

    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            MovieFilterFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}