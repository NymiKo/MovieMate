package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.domain.FakeFirebaseSource
import com.easyprog.android.moviemate.domain.MovieListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieMainInfoListRepositoryImplTest {

    private lateinit var repository: MovieListRepository
    private val firestore = FakeFirebaseSource()

    @Before
    fun setupRepository() {
        repository = MovieListListRepositoryImpl(firestore)
    }

    @Test
    fun `checking the result for success`() = runBlocking {
        val expectedMovieList = listOf(
            MovieMainInfo("0", "Милый дом"),
            MovieMainInfo("1", "Завтра"),
            MovieMainInfo("2", "Алиса в пограничье"),
            MovieMainInfo("3", "Во имя мести")
        )
        firestore.setMovieList(expectedMovieList)
        val actualList: Result.SUCCESS<List<MovieMainInfo>> = repository.getMovieList() as Result.SUCCESS<List<MovieMainInfo>>
        assertEquals(expectedMovieList, actualList.data)
    }

    @Test
    fun `checking the result for error`() = runBlocking {
        firestore.setMovieList(error = true)
        val actualResult = repository.getMovieList()
        assertTrue(actualResult is Result.ERROR)
    }
}