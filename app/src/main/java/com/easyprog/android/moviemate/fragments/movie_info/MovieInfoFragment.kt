package com.easyprog.android.moviemate.fragments.movie_info

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.databinding.FragmentMovieInfoBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.fromHtmlToString
import com.easyprog.android.moviemate.utils.getColorFromTheme
import com.easyprog.android.moviemate.utils.hideBottomNavView
import com.easyprog.android.moviemate.utils.loadImageCollapsingToolbar
import com.google.android.material.color.MaterialColors
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import com.google.android.material.R.attr

@AndroidEntryPoint
class MovieInfoFragment :
    BaseFragment<FragmentMovieInfoBinding>(FragmentMovieInfoBinding::inflate) {

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
            when (result) {
                is Result.ERROR -> {

                }
                Result.LOADING -> {

                }
                is Result.SUCCESS -> {
                    binding.collapsingToolbar.title = result.data.name
                    binding.textExpandedDescription.text = result.data.description
                    binding.imageMovieAvatar.loadImageCollapsingToolbar(result.data.image)
                    binding.textYearProduction.text = String.format("<b><font color=#2ECC71>%s</font></b>%s", getString(R.string.year_production), result.data.year_production).fromHtmlToString()
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            if (abs(verticalOffset) == binding.appBarLayout.totalScrollRange) {
                if (!isToolbarCollapsed) {
                    changeColorNavigationIconToolbar(getColorFromTheme(attr.colorSecondaryVariant), true)
                }
            } else {
                changeColorNavigationIconToolbar(getColorFromTheme(attr.colorOnPrimary), false)
            }
        }
    }

    private fun changeColorNavigationIconToolbar(color: Int, isCollapsed: Boolean) {
        binding.toolbar.navigationIcon?.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                color, BlendModeCompat.SRC_ATOP
            )
        isToolbarCollapsed = isCollapsed
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}