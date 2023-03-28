package com.easyprog.android.moviemate.fragments.movie_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.easyprog.android.moviemate.CoroutineTestRule
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import kotlinx.coroutines.runBlocking
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
    private val repository = FakeMovieListRepository()

    @Before
    fun setup() {
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun `get a list of movies and check the result for success`() = runBlocking {
        val expectedMovieList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра")
        )
        val expectedStateList = listOf(
            Result.LOADING,
            Result.SUCCESS(expectedMovieList)
        )
        val actualStateList = mutableListOf<Result<List<Movie>>>()
        viewModel.movieList.observeForever { actualStateList.add(it) }
        repository.setMovieList(expectedMovieList)
        assertEquals(expectedStateList, actualStateList)
    }

    @Test
    fun `get a list of movies and check the result for an error`() = runBlocking {
        val expectedStateList = listOf(
            Result.LOADING,
            Result.ERROR(Exception())
        )
        val actualStateList = mutableListOf<Result<List<Movie>>>()
        viewModel.movieList.observeForever { actualStateList.add(it) }
        repository.setMovieList(error = true)
        assertTrue(actualStateList[0] is Result.LOADING)
        assertTrue(actualStateList[1] is Result.ERROR)
    }

}