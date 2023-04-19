package com.easyprog.android.moviemate.fragments.serials

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.adapters.movie_list.MovieListAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.databinding.FragmentSerialsListTabBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.navigateTo
import com.easyprog.android.moviemate.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SerialsListTabFragment :
    BaseFragment<FragmentSerialsListTabBinding>(FragmentSerialsListTabBinding::inflate) {

    private val viewModel: SerialsListViewModel by viewModels()
    private val mAdapter = MovieListAdapter(object : BaseActionListener {
        override fun onMovieClick(idMovie: String) {
            navigateTo(R.id.movieInfoFragment, bundleOf("idMovie" to idMovie))
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieList(SERIAL)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSerialsList()
        setupView()
    }

    private fun setupView() {
        setupSerialsRecyclerView()
    }

    private fun setupSerialsRecyclerView() {
        binding.recyclerViewSerialsList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun getSerialsList() {
        viewModel.serialsList.observe(viewLifecycleOwner) { result ->
            when (result) {
                Result.LOADING -> {
                    showProgressBar()
                }
                is Result.ERROR -> {
                    hideProgressBar()
                    showSnackBar(R.string.error_message)
                }
                is Result.SUCCESS -> {
                    mAdapter.movieList = result.data
                    hideProgressBar()
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progress.show()
    }

    private fun hideProgressBar() {
        binding.progress.hide()
    }

    companion object {
        private const val SERIAL = "Serial"
    }
}