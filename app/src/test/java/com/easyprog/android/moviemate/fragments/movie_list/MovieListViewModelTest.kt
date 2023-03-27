package com.easyprog.android.moviemate.fragments.movie_list

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieListViewModelTest {

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
            Movie(1, "Завтра"),
            Movie(2, "Алиса в пограничье"),
            Movie(3, "Во имя мести")
        )
        repository.setMovieList(expectedMovieList)
        val actualMovieList = viewModel.getMovieList()
        assertEquals(expectedMovieList, actualMovieList)
    }

    @Test
    fun `get a list of movies and check the result for an error`() = runBlocking {
        repository.setMovieList(error = true)
        val actualResult = viewModel.getMovieList()
        assertTrue(actualResult is Result.ERROR)
    }

}