package com.easyprog.android.moviemate.di.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.base.BaseActionListener
import com.easyprog.android.moviemate.adapters.categories.CategoriesAdapter
import com.easyprog.android.moviemate.adapters.search_movie_adapter.SearchMovieAdapter
import com.easyprog.android.moviemate.adapters.categories.CategoriesActionListener
import com.easyprog.android.moviemate.adapters.movies_by_category.MoviesByCategoryAdapter
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.data.models.MovieFullInfo
import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.databinding.FragmentSearchBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private var mAdapterSearchMovie = SearchMovieAdapter(onClickItem())
    private var mAdapterRecommendedMovies = MoviesByCategoryAdapter(onClickItem())
    private var mAdapterCategories = CategoriesAdapter(object : CategoriesActionListener {
        override fun categoryClick(category: String) {
            navigateTo(SearchFragmentDirections.actionSearchFragmentToCategoryFragment(category))
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

    override fun onStart() {
        super.onStart()
        showBottomNavView()
    }

    private fun setupView() {
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
                if (binding.textLayoutSearch.editText?.isFocused == true && binding.textLayoutSearch.editText?.text!!.isNotEmpty()) {
                    viewModel.getMovieListBySearch(it.toString())
                    getSearchResult()
                } else {
                    hideTextViewNothingFound()
                    binding.recyclerViewFoundMovies.visibility = View.GONE
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

    private fun setResultToFoundMovieRecyclerView(movieList: List<MovieFullInfo>) {
        mAdapterSearchMovie.movieList = movieList
    }

    private fun getSearchResult() {
        viewModel.searchMovieList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.ERROR -> {
                    showTextViewNothingFound()
                }
                Result.LOADING -> {

                }
                is Result.SUCCESS -> {
                    checkResultSuccess(result.data)
                }
            }
        }
    }

    private fun checkResultSuccess(data: List<MovieFullInfo>) {
        if (data.isNotEmpty()) {
            binding.recyclerViewFoundMovies.visibility = View.VISIBLE
            setResultToFoundMovieRecyclerView(data)
            hideTextViewNothingFound()
        } else {
            showTextViewNothingFound()
        }
    }

    private fun hideTextViewNothingFound() {
        binding.textNothingFound.visibility = View.GONE
    }

    private fun showTextViewNothingFound() {
        binding.textNothingFound.visibility = View.VISIBLE
        binding.recyclerViewFoundMovies.visibility = View.GONE
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

    private fun setResultToRecommendedMoviesRecyclerView(movieList: List<MovieMainInfo>) {
        mAdapterRecommendedMovies.movieList = movieList
    }

    private fun setupRecommendedMoviesRecyclerView() {
        getRecommendedMoviesResult()
        binding.recyclerViewRecommendedMovies.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterRecommendedMovies
            itemAnimator = null
        }
    }

    private fun setupCategoriesRecyclerView() {
        binding.recyclerViewCategories.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.HORIZONTAL)
            adapter = mAdapterCategories
        }
    }

    private fun onClickItem() = object : BaseActionListener {
        override fun onMovieClick(idMovie: String) {
            navigateTo(SearchFragmentDirections.actionSearchFragmentToMovieInfoFragment(idMovie))
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveSearch(binding.textLayoutSearch.editText?.text.toString())
    }
}