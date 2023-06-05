package com.easyprog.android.moviemate.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.easyprog.android.moviemate.data.models.MovieMainInfo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirebaseSourceTest {

    private lateinit var firestore: FirebaseSourceImpl

    @Before
    fun setUp() {
        firestore = FirebaseSourceImpl()
    }

    @Test
    fun checkingFirebaseResult() = runBlocking {
        val actualList = firestore.getMovieList()
        assertTrue(actualList is Result.SUCCESS<List<MovieMainInfo>>)
        val result = if (actualList is Result.SUCCESS<List<MovieMainInfo>>) actualList.data else emptyList()
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }

    @Test
    fun checkingFirebaseResultBySearch() = runBlocking {
        val actualList = firestore.getMovieListBySearch("завтра")
        assertTrue(actualList is Result.SUCCESS<List<MovieMainInfo>>)
        val result = if (actualList is Result.SUCCESS<List<MovieMainInfo>>) actualList.data else emptyList()
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }

    @Test
    fun checkingFirebaseResultRecommendedMovies() = runBlocking {
        val actualList = firestore.getRecommendedMovies()
        assertTrue(actualList is Result.SUCCESS<List<MovieMainInfo>>)
        val result = if (actualList is Result.SUCCESS<List<MovieMainInfo>>) actualList.data else emptyList()
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }

    @Test
    fun checkingFirebaseResultMoviesByCategory() = runBlocking {
        val actualList = firestore.getMoviesByCategory("боевик")
        assertTrue(actualList is Result.SUCCESS<List<MovieMainInfo>>)
        val result = if (actualList is Result.SUCCESS<List<MovieMainInfo>>) actualList.data else emptyList()
        assertNotNull(result)
        assertFalse(result.isEmpty())
    }

}