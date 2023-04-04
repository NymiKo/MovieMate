package com.easyprog.android.moviemate.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.SearchMovieAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.FragmentSearchBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.fragments.base.factory
import com.easyprog.android.moviemate.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private var mAdapter = SearchMovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        showBottomNavView()
        setupSearchEditText()
        searchListener()
        setupRecyclerView()
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
            hideTextViewNothingFound()
            setResultToRecyclerView(emptyList())
        }
    }

    private fun setupEndIconSearchEditText(icon: Int) {
        binding.textLayoutSearch.endIconDrawable =
            AppCompatResources.getDrawable(requireContext(), icon)
    }

    private fun clickEndIconSearchEditText() {
        binding.textLayoutSearch.setEndIconOnClickListener {
            navigateTo(R.id.action_searchFragment_to_movieFilterFragment)
        }
    }

    private fun clickEndIconClearSearchEditText() {
        binding.textLayoutSearch.setEndIconOnClickListener {
            binding.textLayoutSearch.editText?.text?.clear()
        }
    }

    private fun searchListener() {
        binding.textLayoutSearch.editText?.textChangedListener()
            ?.debounce(500)
            ?.onEach {
                if (binding.textLayoutSearch.editText!!.isFocused) {
                    viewModel.getMovieListBySearch(it.toString())
                    getSearchResult()
                }
            }
            ?.launchIn(lifecycleScope)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFoundMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun setResultToRecyclerView(movieList: List<Movie>) {
        mAdapter.movieList = movieList
    }

    private fun getSearchResult() {
        viewModel.searchMovieList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.ERROR -> {
                    showSnackBar(R.string.error_message)
                }
                Result.LOADING -> {

                }
                is Result.SUCCESS -> {
                    checkResultSuccess(result.data)
                }
            }
        }
    }

    private fun checkResultSuccess(data: List<Movie>) {
        if (data.isNotEmpty()) {
            setResultToRecyclerView(data)
            hideTextViewNothingFound()
        }
        else {
            showTextViewNothingFound()
        }
    }

    private fun hideTextViewNothingFound() {
        binding.textNothingFound.visibility = View.GONE
    }

    private fun showTextViewNothingFound() {
        binding.textNothingFound.visibility = View.VISIBLE
    }

}