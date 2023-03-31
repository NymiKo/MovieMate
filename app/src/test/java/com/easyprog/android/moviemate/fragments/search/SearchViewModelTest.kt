package com.easyprog.android.moviemate.fragments.search

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private lateinit var repository: FakeSearchRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setupViewModel() {
        repository = FakeSearchRepository()
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun `get a list of movies on demand in the search with a successful result`() = runTest {
        val expectedList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра"),
            Movie(2, "Во имя мести")
        )
        repository.setSearchMovieList(searchMovieList)
        viewModel.getMovieListBySearch("лы")
        val actualList = viewModel.searchMovieList.value
        advanceUntilIdle()
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get an error on a search query`() = runTest {
        val expectedException = Exception("Error")
        repository.setError(expectedException)
        viewModel.getMovieListBySearch("лы")
        val actualException = viewModel.searchMovieList.value
        advanceUntilIdle()
        Assert.assertEquals(expectedException, actualException)
    }

    @Test
    fun `get an empty list on request`() {
        val actualList = viewModel.getMovieListBySearch("лы")
        val expectedList = Result.SUCCESS(emptyList<Movie>())
        Assert.assertEquals(expectedList, actualList)
    }

}