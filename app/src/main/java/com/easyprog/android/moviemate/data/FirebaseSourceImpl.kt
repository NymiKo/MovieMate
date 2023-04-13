package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.model.Movie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSourceImpl @Inject constructor() : FirebaseSource {

    private companion object {
        private const val COLLECTION_MOVIES = "Movies"
        private const val COLLECTION_RECOMMENDED_MOVIES = "recommended_movies"
        private const val ID = "id"
        private const val NAME = "name"
        val MOVIE_CLASS = Movie::class.java
    }

    private lateinit var firestore: FirebaseFirestore

    private fun getFirestore(): FirebaseFirestore {
        if (!::firestore.isInitialized) {
            firestore = Firebase.firestore
        }
        return firestore
    }

    override suspend fun getMovieList(): Result<List<Movie>> {
        val snapshot = getFirestore().collection(COLLECTION_MOVIES).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>> {
        val snapshot =
            getFirestore().collection(COLLECTION_MOVIES).whereEqualTo(NAME, searchQuery.lowercase())
                .get().await()
        return getResult(snapshot)
    }

    override suspend fun getRecommendedMovies(): Result<List<Movie>> {
        val snapshot = getFirestore().collection(COLLECTION_RECOMMENDED_MOVIES).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMoviesByCategory(category: String): Result<List<Movie>> {
        val snapshot = getFirestore().collection(category.lowercase()).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMovieInfo(idMovie: String): Result<Movie> {
        val snapshot = getFirestore().collection(COLLECTION_MOVIES).whereEqualTo(ID, idMovie)
                .get().await()
        return getResult(snapshot)
    }

    private inline fun <reified T : Any> getResult(snapshot: QuerySnapshot): Result<T> {
        return try {
            if (!snapshot.isEmpty) {
                val result = snapshot.toObjects(MOVIE_CLASS)
                if (result.size == 1) {
                    Result.SUCCESS(result[0] as T)
                } else {
                    Result.SUCCESS(result as T)
                }
            } else {
                Result.ERROR("error")
            }
        } catch (e: Exception) {
            Result.ERROR(e.message.toString())
        }
    }

}