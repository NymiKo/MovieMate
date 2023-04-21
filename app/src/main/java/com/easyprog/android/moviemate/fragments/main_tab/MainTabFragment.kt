package com.easyprog.android.moviemate.fragments.main_tab

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.adapters.carousel_movie.CarouselMovieAdapter
import com.easyprog.android.moviemate.adapters.new_movie.NewMovieAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.FragmentMainTabBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.navigateTo
import com.easyprog.android.moviemate.utils.onPageCallback
import com.easyprog.android.moviemate.utils.showSnackBar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@AndroidEntryPoint
class MainTabFragment : BaseFragment<FragmentMainTabBinding>(FragmentMainTabBinding::inflate) {

    private val mAdapterCarouselMovie = CarouselMovieAdapter(object : BaseActionListener{
        override fun onMovieClick(idMovie: String) {
            navigateTo(R.id.movieInfoFragment, bundle = bundleOf(ID_MOVIE to idMovie))
        }
    })

    private val mAdapterNewMovie = NewMovieAdapter(object : BaseActionListener{
        override fun onMovieClick(idMovie: String) {
            navigateTo(R.id.movieInfoFragment, bundle = bundleOf(ID_MOVIE to idMovie))
        }
    })

    private val viewModel: MainTabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCarouselMovieList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCarouselMovieList()
        getNewMovieList()
        setupView()
    }

    private fun setupView() {
        setupMovieCarousel()
        setupNewMovieRecyclerView()
    }

    private fun setupMovieCarousel() {
        binding.viewPagerCarouselMovie.adapter = mAdapterCarouselMovie
        TabLayoutMediator(binding.tabLayoutCarouselMarker, binding.viewPagerCarouselMovie) { tab, position ->
            tab.text = ""
        }.attach()


        binding.viewPagerCarouselMovie.onPageCallback { position ->
            binding.tabLayoutCarouselMarker.getTabAt(position)?.select()
        }

        startAutoScroll()
    }

    private fun startAutoScroll() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            while (isActive) {
                delay(3000)
                val currentItem = binding.viewPagerCarouselMovie.currentItem
                val totalItems = binding.viewPagerCarouselMovie.adapter?.itemCount ?: 0
                val nextItem = (currentItem + 1) % totalItems
                binding.viewPagerCarouselMovie.setCurrentItem(nextItem, true)
            }
        }
    }

    private fun getCarouselMovieList() {
        viewModel.movieList.observe(viewLifecycleOwner) { result ->
            when (result) {
                Result.LOADING -> {

                }
                is Result.ERROR -> {

                }
                is Result.SUCCESS -> {
                    mAdapterCarouselMovie.movieList = result.data
                }
            }
        }
    }

    private fun setupNewMovieRecyclerView() {
        binding.recyclerViewNewMovies.apply {
            adapter = mAdapterNewMovie
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun getNewMovieList() {
        viewModel.newMovieList.observe(viewLifecycleOwner) { result ->
            when (result) {
                Result.LOADING -> {

                }
                is Result.ERROR -> {

                }
                is Result.SUCCESS -> {
                    mAdapterNewMovie.movieList = result.data
                }
            }
        }
    }

    companion object {
        private const val ID_MOVIE = "idMovie"
    }
}