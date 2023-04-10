package com.easyprog.android.moviemate.fragments.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.CategoriesAdapter
import com.easyprog.android.moviemate.adapters.RecommendedMoviesAdapter
import com.easyprog.android.moviemate.adapters.SearchMovieAdapter
import com.easyprog.android.moviemate.adapters.categories.CategoriesActionListener
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.FragmentSearchBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.fragments.category.CategoryFragment
import com.easyprog.android.moviemate.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private var mAdapterSearchMovie = SearchMovieAdapter()
    private var mAdapterRecommendedMovies = RecommendedMoviesAdapter()
    private var mAdapterCategories = CategoriesAdapter(object : CategoriesActionListener {
        override fun categoryClick(category: String) {
            navigateTo(R.id.action_searchFragment_to_categoryFragment, bundleOf("category" to category))
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.search.observe(viewLifecycleOwner) { search ->
            binding.textLayoutSearch.editText?.setText(search)
        }
        viewModel.getRecommendedMovies()
        setupView()
    }

    private fun setupView() {
        showBottomNavView()
        setupSearchEditText()
        setupFoundMovieRecyclerView()
        setupRecommendedMoviesRecyclerView()
        setupCategoriesRecyclerView()
    }

    private fun setupSearchEditText() {
        clickEndIconSearchEditText()
        focusSearchEditTextListener()
        setupImeOptionOnSearchEditText()
        searchListener()
    }

    private fun setupImeOptionOnSearchEditText() {
        binding.textLayoutSearch.editText?.setOnEditorActionListener { view, actionId, _ ->
            return@setOnEditorActionListener actionListenerImeOptionsOnSearchEditText(actionId, view)
        }
    }

    private fun actionListenerImeOptionsOnSearchEditText(actionId: Int, view: TextView): Boolean {
        return when(actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                if (view.text.isEmpty()) hideKeyboard()
                else navigateTo(R.id.action_searchFragment_to_searchResultFragment)
                true
            }
            else -> false
        }
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
            binding.textLayoutSearch.editText?.apply {
                text.clear()
                clearFocus()
            }
            hideKeyboard()
            hideTextViewNothingFound()
            setResultToFoundMovieRecyclerView(emptyList())
            binding.recyclerViewFoundMovies.visibility = View.GONE
            binding.groupRecommended.visibility = View.VISIBLE
            binding.recyclerViewCategories.visibility = View.VISIBLE
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
                if (binding.textLayoutSearch.editText?.isFocused == true) {
                    viewModel.getMovieListBySearch(it.toString())
                    getSearchResult()
                }
            }
            ?.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupFoundMovieRecyclerView() {
        binding.recyclerViewFoundMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapterSearchMovie
        }
    }

    private fun setResultToFoundMovieRecyclerView(movieList: List<Movie>) {
        mAdapterSearchMovie.movieList = movieList
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
            setResultToFoundMovieRecyclerView(data)
            hideTextViewNothingFound()
            binding.groupRecommended.visibility = View.GONE
            binding.recyclerViewFoundMovies.visibility = View.VISIBLE
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
        binding.recyclerViewFoundMovies.visibility = View.GONE
        binding.groupRecommended.visibility = View.GONE
        binding.recyclerViewCategories.visibility = View.GONE
    }

    private fun getRecommendedMoviesResult() {
        viewModel.recommendedMoviesList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.ERROR -> showToast(R.string.error_message)
                Result.LOADING -> {

                }
                is Result.SUCCESS -> setResultToRecommendedMoviesRecyclerView(result.data)
            }
        }
    }

    private fun setResultToRecommendedMoviesRecyclerView(movieList: List<Movie>) {
        mAdapterRecommendedMovies.recommendedMoviesList = movieList
    }

    private fun setupRecommendedMoviesRecyclerView() {
        getRecommendedMoviesResult()
        binding.recyclerViewRecommendedMovies.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterRecommendedMovies
        }
    }

    private fun setupCategoriesRecyclerView() {
        binding.recyclerViewCategories.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.HORIZONTAL)
            adapter = mAdapterCategories
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveSearch(binding.textLayoutSearch.editText?.text.toString())
    }
}