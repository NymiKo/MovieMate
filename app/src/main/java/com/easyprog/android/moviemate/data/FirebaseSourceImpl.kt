package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.model.Movie
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

private const val COLLECTION_MOVIES = "Movies"
private const val ID = "id"

class FirebaseSourceImpl: FirebaseSource {

    private val firestore = Firebase.firestore

    override suspend fun getMovieList(): Result<List<Movie>> {
        val snapshot = firestore.collection(COLLECTION_MOVIES).orderBy(ID).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<Movie>> {
        val snapshot = firestore.collection(COLLECTION_MOVIES).whereEqualTo("name", searchQuery).get().await()
        return getResult(snapshot)
    }

    private fun getResult(snapshot: QuerySnapshot): Result<List<Movie>> {
        return if (!snapshot.isEmpty) {
            Result.SUCCESS(snapshot.toObjects(Movie::class.java))
        } else {
            Result.ERROR(Exception("Error"))
        }
    }
}