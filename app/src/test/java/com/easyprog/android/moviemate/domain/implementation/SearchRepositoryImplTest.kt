package com.easyprog.android.moviemate.domain.implementation

import com.easyprog.android.moviemate.data.model.MovieMainInfo
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
            MovieMainInfo(0, "Милый дом"),
            MovieMainInfo(1, "Завтра"),
            MovieMainInfo(2, "Во имя мести")
        )
        firestore.setMovieList(movieList)
        val actualList = repository.getMovieListBySearch("авт")
        val expectedList = Result.SUCCESS(listOf(MovieMainInfo(1, "Завтра")))
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get an error during the search`() = runTest {
        firestore.setMovieList(error = true)
        val actualException = repository.getMovieListBySearch("авт")
        val expectedException = Result.ERROR("error")
        Assert.assertEquals(expectedException, actualException)
    }

    @Test
    fun `get an empty list of movies if the movie is not found`() = runTest {
        firestore.setMovieList()
        val actualList = repository.getMovieListBySearch("оала")
        val expectedList = Result.SUCCESS(emptyList<MovieMainInfo>())
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get recommended movies with a successful result`() = runTest {
        val movieList = listOf(
            MovieMainInfo(0, "Милый дом"),
            MovieMainInfo(1, "Завтра"),
            MovieMainInfo(2, "Во имя мести")
        )
        firestore.setMovieList(movieList)
        val actualList = repository.getRecommendedMovies()
        val expectedList = Result.SUCCESS(movieList)
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun `get recommended movies with an error`() = runTest {
        firestore.setMovieList(error = true)
        val actualException = repository.getRecommendedMovies()
        val expectedException = Result.ERROR("error")
        Assert.assertEquals(expectedException, actualException)
    }
}