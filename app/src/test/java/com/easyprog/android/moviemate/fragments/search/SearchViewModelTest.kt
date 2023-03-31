package com.easyprog.android.moviemate.fragments.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.easyprog.android.moviemate.CoroutineTestRule
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
        viewModel = SearchViewModel(repository, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `get a list of movies on demand in the search with a successful result`() = runTest {
        val movieList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра"),
            Movie(2, "Во имя мести")
        )
        repository.setSearchMovieList(movieList)
        viewModel.getMovieListBySearch("лы")
        advanceUntilIdle()
        val actualList = viewModel.searchMovieList.value
        val expectedList = Result.SUCCESS(listOf(Movie(0, "Милый дом")))
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get an error on a search query`() = runTest {
        val exception = Exception("Error")
        repository.setError(exception)
        viewModel.getMovieListBySearch("лы")
        advanceUntilIdle()
        val actualException = viewModel.searchMovieList.value
        val expectedException = Result.ERROR(exception)
        Assert.assertEquals(expectedException, actualException)
    }

    @Test
    fun `get an empty list on request`() = runTest {
        viewModel.getMovieListBySearch("лы")
        advanceUntilIdle()
        val actualList = viewModel.searchMovieList.value
        val expectedList = Result.SUCCESS(emptyList<Movie>())
        Assert.assertEquals(expectedList, actualList)
    }

}