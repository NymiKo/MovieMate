package com.easyprog.android.moviemate.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.databinding.FragmentSearchBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.fragments.base.factory
import com.easyprog.android.moviemate.utils.*
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels { factory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavView()
        setupView()
        searchListener()
    }

    private fun setupView() {
        setupSearchEditText()
    }

    private fun setupSearchEditText() {
        clickEndIconSearchEditText()
        focusSearchEditTextListener()
    }

    private fun focusSearchEditTextListener() {
        binding.textLayoutSearch.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setupStartIconSearchEditText(R.drawable.ic_back)
                setupEndIconSearchEditText(R.drawable.ic_clear)
                clickOnStartIconSearchEditText()
                clickEndIconClearSearchEditText()
            } else {
                setupStartIconSearchEditText(R.drawable.ic_search)
                setupEndIconSearchEditText(R.drawable.ic_filter)
                clickEndIconSearchEditText()
            }
        }
    }

    private fun setupStartIconSearchEditText(icon: Int) {
        binding.textLayoutSearch.startIconDrawable =
            AppCompatResources.getDrawable(requireContext(), icon)
    }

    private fun clickOnStartIconSearchEditText() {
        binding.textLayoutSearch.setStartIconOnClickListener {
            binding.textLayoutSearch.editText?.clearFocus()
            hideKeyboard()
        }
    }

    private fun setupEndIconSearchEditText(icon: Int) {
        binding.textLayoutSearch.endIconDrawable =
            AppCompatResources.getDrawable(requireContext(), icon)
    }

    private fun clickEndIconSearchEditText() {
        binding.textLayoutSearch.setEndIconOnClickListener {
            viewModel.searchMovieList.observe(viewLifecycleOwner) {
                Log.e("MOVIE_LIST", it.toString())
            }
            navigateTo(R.id.action_searchFragment_to_movieFilterFragment)
        }
    }

    private fun clickEndIconClearSearchEditText() {
        binding.textLayoutSearch.setEndIconOnClickListener {
            binding.textLayoutSearch.editText?.text?.clear()
        }
    }

    private fun searchListener() {
        binding.textLayoutSearch.editText?.textChangedListener()?.debounce(500)
            ?.onEach { viewModel.getMovieListBySearch(it.toString()) }
            ?.launchIn(lifecycleScope)
    }
}