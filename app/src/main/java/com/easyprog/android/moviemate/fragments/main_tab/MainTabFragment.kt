package com.easyprog.android.moviemate.fragments.main_tab

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.carousel_movie.CarouselMovieAdapter
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.FragmentMainTabBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainTabFragment : BaseFragment<FragmentMainTabBinding>(FragmentMainTabBinding::inflate) {

    private val mAdapterCarouselMovie = CarouselMovieAdapter()
    private val movieList = listOf(
        Movie("jiajdwajk", image = "https://firebasestorage.googleapis.com/v0/b/moviemate-ca321.appspot.com/o/sweet_home.jpg?alt=media&token=c7778b64-602e-406d-9327-60b02b3d8cce"),
        Movie("jiajdawdw", image = "https://firebasestorage.googleapis.com/v0/b/moviemate-ca321.appspot.com/o/john_wick.jpg?alt=media&token=bafb32a3-6ba9-46ef-a7d9-d278d929dcbe"),
        Movie("jijijaiwd", image = "https://firebasestorage.googleapis.com/v0/b/moviemate-ca321.appspot.com/o/kairos.jpg?alt=media&token=da97ed58-2633-42e0-9c0a-42e2b1408ee8"),
        Movie("djijngawn", image = "https://firebasestorage.googleapis.com/v0/b/moviemate-ca321.appspot.com/o/alice_in_borderland.jpg?alt=media&token=0cf8d424-83c3-4659-9dc4-43088a9afae4")
    )
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapterCarouselMovie.movieList = movieList
        setupView()
    }

    private fun setupView() {
        setupMovieCarousel()
    }

    private fun setupMovieCarousel() {
        binding.viewPagerCarouselMovie.adapter = mAdapterCarouselMovie
        TabLayoutMediator(binding.tabLayoutCarouselMarker, binding.viewPagerCarouselMovie) { tab, position ->
            tab.text = ""
        }.attach()


        binding.viewPagerCarouselMovie.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayoutCarouselMarker.getTabAt(position)?.select()
            }
        })

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
}