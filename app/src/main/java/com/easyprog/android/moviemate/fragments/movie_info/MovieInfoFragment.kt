package com.easyprog.android.moviemate.fragments.movie_info

import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.databinding.FragmentMovieInfoBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.fromHtmlToString
import com.easyprog.android.moviemate.utils.hideBottomNavView
import com.easyprog.android.moviemate.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import io.github.glailton.expandabletextview.readMoreText
import kotlin.math.abs

@AndroidEntryPoint
class MovieInfoFragment : BaseFragment<FragmentMovieInfoBinding>(FragmentMovieInfoBinding::inflate) {

    private val viewModel: MovieInfoViewModel by viewModels()
    private val args: MovieInfoFragmentArgs by navArgs()
    private var isToolbarCollapsed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieInfo(args.idMovie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        getMovieInfo()
    }

    private fun setupView() {
        hideBottomNavView()
        setupToolbar()
    }

    private fun getMovieInfo() {
        viewModel.movieInfo.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.ERROR -> {

                }
                Result.LOADING -> {

                }
                is Result.SUCCESS -> {
                    binding.collapsingToolbar.title = result.data.name
                    binding.textExpandedDescription.text = result.data.description
                    binding.imageMovieAvatar.loadImage(result.data.image)
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            if (abs(verticalOffset) == binding.appBarLayout.totalScrollRange) {
                if (!isToolbarCollapsed) {
                    binding.toolbar.navigationIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.BLACK, BlendModeCompat.SRC_ATOP)
                    isToolbarCollapsed = true
                }
            } else {
                binding.toolbar.navigationIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE, BlendModeCompat.SRC_ATOP)
                isToolbarCollapsed = false
            }
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}