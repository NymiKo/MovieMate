package com.easyprog.android.moviemate.fragments.movie_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.base.BaseActionListener
import com.easyprog.android.moviemate.adapters.movie_list.MovieListAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.databinding.FragmentMovieListTabBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.navigateTo
import com.easyprog.android.moviemate.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListTabFragment :
    BaseFragment<FragmentMovieListTabBinding>(FragmentMovieListTabBinding::inflate) {

    private var mAdapter = MovieListAdapter(object : BaseActionListener {
        override fun onMovieClick(idMovie: String) {
            navigateTo(R.id.movieInfoFragment, bundle = bundleOf(ID_MOVIE to idMovie))
        }
    })

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieList(FILM)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieList()
        setupView()
    }

    private fun setupView() {
        setupRecyclerView()
        setupButtonRepeat()
    }

    private fun setupButtonRepeat() {
        binding.buttonRepeat.setOnClickListener {
            viewModel.getMovieList(FILM)
        }
    }

    private fun getMovieList() {
        viewModel.movieList.observe(viewLifecycleOwner) { result ->
            when (result) {
                Result.LOADING -> {
                    showProgressBar()
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
            setHasFixedSize(true)
            setItemViewCacheSize(4)
        }
    }

    private fun showProgressBar() {
        binding.progress.show()
    }

    private fun hideProgressBar() {
        binding.progress.hide()
    }

    private fun showButtonRepeat() {
        binding.buttonRepeat.visibility = View.VISIBLE
    }

    private fun hideButtonRepeat() {
        binding.buttonRepeat.visibility = View.GONE
    }

    companion object {
        private const val FILM = "Film"
        private const val ID_MOVIE = "idMovie"
    }
}