package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.FakeFirebaseSource
import com.easyprog.android.moviemate.domain.MovieRepository
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
    fun `checking the result for success`() {
        val expectedMovieList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра"),
            Movie(2, "Алиса в пограничье"),
            Movie(3, "Во имя мести")
        )
        firestore.setMovieList(expectedMovieList)
        val actualList = repository.getMovieList()
        assertEquals(expectedMovieList, actualList.data)
    }

}