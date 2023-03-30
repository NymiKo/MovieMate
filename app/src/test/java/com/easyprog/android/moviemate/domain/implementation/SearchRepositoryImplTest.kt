package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.domain.FakeFirebaseSource
import com.easyprog.android.moviemate.data.Result
import com.easyprog.android.moviemate.domain.SearchRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {

    private lateinit var repository: SearchRepository
    private val firestore = FakeFirebaseSource()

    @Before
    fun setupRepository() {
        repository = SearchRepositoryImpl(firestore)
    }

    @Test
    fun `get a list of movies by search with a successful result`() = runTest {
        val movieList = listOf(
            Movie(0, "Милый дом"),
            Movie(1, "Завтра"),
            Movie(2, "Во имя мести")
        )
        firestore.setMovieList(movieList)
        val actualList = repository.getMovieListBySearch("авт")
        val expectedList = Result.SUCCESS(listOf(Movie(1, "Завтра")))
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get an error during the search`() = runTest {
        firestore.setMovieList(error = true)
        val actualException = repository.getMovieListBySearch("авт")
        Assert.assertTrue(actualException is Result.ERROR)
    }

    @Test
    fun `get an empty list of movies if the movie is not found`() = runTest {
        firestore.setMovieList()
        val actualList = repository.getMovieListBySearch("оала")
        val expectedList = Result.SUCCESS(emptyList<Movie>())
        Assert.assertEquals(expectedList, actualList)
    }

}