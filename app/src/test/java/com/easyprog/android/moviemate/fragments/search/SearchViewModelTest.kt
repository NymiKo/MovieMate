package com.easyprog.android.moviemate.fragments.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.easyprog.android.moviemate.CoroutineTestRule
import com.easyprog.android.moviemate.FakeDispatcherList
import com.easyprog.android.moviemate.data.models.MovieMainInfo
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.di.search.SearchViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: FakeSearchRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setupViewModel() {
        repository = FakeSearchRepository()
        viewModel = SearchViewModel(repository, FakeDispatcherList())
    }

    @Test
    fun `get a list of movies on demand in the search with a successful result`() = runTest {
        val movieList = listOf(
            MovieMainInfo(0, "Милый дом"),
            MovieMainInfo(1, "Завтра"),
            MovieMainInfo(2, "Во имя мести")
        )
        repository.setMovieList(movieList)
        viewModel.getMovieListBySearch("лы")
        val actualList = viewModel.searchMovieList.value
        val expectedList = Result.SUCCESS(listOf(MovieMainInfo(0, "Милый дом")))
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get an error on a search query`() = runTest {
        val exception = "error"
        repository.setError(exception)
        viewModel.getMovieListBySearch("лы")
        val actualException = viewModel.searchMovieList.value
        val expectedException = Result.ERROR(exception)
        Assert.assertEquals(expectedException, actualException)
    }

    @Test
    fun `get an empty list on request`() = runTest {
        viewModel.getMovieListBySearch("лы")
        val actualList = viewModel.searchMovieList.value
        val expectedList = Result.SUCCESS(emptyList<MovieMainInfo>())
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get a list of recommended movies with a successful result`() = runTest {
        val movieList = listOf(
            MovieMainInfo(0, "Милый дом"),
            MovieMainInfo(1, "Завтра"),
            MovieMainInfo(2, "Во имя мести")
        )
        repository.setMovieList(movieList)
        viewModel.getRecommendedMovies()
        val actualList = viewModel.recommendedMoviesList.value
        val expectedList = Result.SUCCESS(movieList)
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get a list of recommended movies with an error`() = runTest {
        val messageError = "error"
        repository.setError(messageError)
        viewModel.getRecommendedMovies()
        val actualException = viewModel.recommendedMoviesList.value
        val expectedException = Result.ERROR(messageError)
        Assert.assertEquals(expectedException, actualException)
    }

}