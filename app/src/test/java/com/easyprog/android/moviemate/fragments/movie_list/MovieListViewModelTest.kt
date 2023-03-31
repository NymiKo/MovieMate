package com.easyprog.android.moviemate.fragments.movie_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.easyprog.android.moviemate.CoroutineTestRule
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: MovieListViewModel
    private lateinit var repository: FakeMovieListRepository

    @Before
    fun setup() {
        repository = FakeMovieListRepository()
        viewModel = MovieListViewModel(repository, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `test movies list state loading`() {
        val expectedState = Result.LOADING
        val actualState = viewModel.movieList.value
        assertEquals(expectedState, actualState)
    }

    @Test
    fun `get a list of movies and check the result for success`() = runTest {
        val expectedMovieList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра")
        )
        repository.setMovieList(expectedMovieList)
        viewModel.getMovieList()
        assertEquals(Result.SUCCESS(expectedMovieList), viewModel.movieList.value)
    }

    @Test
    fun `get a list of movies and check the result for an error`() = runTest {
        val massageError = Exception("No data")
        repository.setMovieListError(massageError)
        viewModel.getMovieList()
        assertEquals(Result.ERROR(massageError), viewModel.movieList.value)
    }

}