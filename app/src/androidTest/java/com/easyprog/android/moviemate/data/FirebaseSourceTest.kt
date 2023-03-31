package com.easyprog.android.moviemate.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.easyprog.android.moviemate.data.model.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirebaseSourceTest {

    @Test
    fun checkingFirebaseResult() = runBlocking {
        val actualList = FirebaseSourceImpl().getMovieList()
        assertTrue(actualList is Result.SUCCESS<List<Movie>>)
    }

    @Test
    fun checkingFirebaseResultBySearch() = runBlocking {
        val actualList = FirebaseSourceImpl().getMovieListBySearch("ав")
        assertTrue(actualList is Result.SUCCESS<List<Movie>>)
    }

}