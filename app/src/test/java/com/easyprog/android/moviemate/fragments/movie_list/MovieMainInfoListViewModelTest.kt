package com.easyprog.android.moviemate.fragments.movie_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.easyprog.android.moviemate.CoroutineTestRule
import com.easyprog.android.moviemate.FakeDispatcherList
import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.data.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieMainInfoListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: MovieListViewModel
    private lateinit var repository: FakeMovieListListRepository

    @Before
    fun setup() {
        repository = FakeMovieListListRepository()
        viewModel = MovieListViewModel(repository, FakeDispatcherList())
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
            MovieMainInfo(0, "Милый дом"),
            MovieMainInfo(1, "Завтра")
        )
        repository.setMovieList(expectedMovieList)
        viewModel.getMovieList()
        assertEquals(Result.SUCCESS(expectedMovieList), viewModel.movieList.value)
    }

    @Test
    fun `get a list of movies and check the result for an error`() = runTest {
        val massageError = "No data"
        repository.setMovieListError(massageError)
        viewModel.getMovieList()
        assertEquals(Result.ERROR(massageError), viewModel.movieList.value)
    }

}