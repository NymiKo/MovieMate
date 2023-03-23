package com.easyprog.android.moviemate.fragments.movie_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.MovieListAdapter
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.FragmentMovieListBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment

class MovieListFragment :
    BaseFragment<FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {

    private lateinit var mAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = MovieListAdapter().apply {
            movieList = mutableListOf(
                Movie(0, "Милый дом"),
                Movie(1, "Завтра"),
                Movie(2, "Алиса в пограничье"),
                Movie(3, "Во имя мести")
            )
        }
        binding.recyclerViewMovieList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }
    }

}