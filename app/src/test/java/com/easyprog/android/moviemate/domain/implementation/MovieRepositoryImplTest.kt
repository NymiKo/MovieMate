package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.domain.FakeFirebaseSource
import com.easyprog.android.moviemate.domain.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    private lateinit var repository: MovieRepository
    private val firestore = FakeFirebaseSource()

    @Before
    fun setupRepository() {
        repository = MovieRepositoryImpl(firestore)
    }

    @Test
    fun `checking the result for success`() = runBlocking {
        val expectedMovieList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра"),
            Movie(2, "Алиса в пограничье"),
            Movie(3, "Во имя мести")
        )
        firestore.setMovieList(expectedMovieList)
        val actualList: Result.SUCCESS<List<Movie>> = repository.getMovieList() as Result.SUCCESS<List<Movie>>
        assertEquals(expectedMovieList, actualList.data)
    }

    @Test
    fun `checking the result for error`() = runBlocking {
        firestore.setMovieList(error = true)
        val actualResult = repository.getMovieList()
        assertTrue(actualResult is Result.ERROR)
    }
}