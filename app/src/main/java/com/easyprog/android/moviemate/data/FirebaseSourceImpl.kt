package com.easyprog.android.moviemate.data

import com.easyprog.android.moviemate.data.model.MovieCarousel
import com.easyprog.android.moviemate.data.model.MovieFullInfo
import com.easyprog.android.moviemate.data.model.MovieMainInfo
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
        private const val COLLECTION_CAROUSEL = "carousel"
        private const val COLLECTION_NEW_MOVIE = "new_movie"
        private const val COLLECTION_WEEKEND_MOVIE = "weekend_movies"
        private const val COLLECTION_FASCINATING_SERIES = "fascinating_series"
        private const val ID = "id"
        private const val NAME_FOR_SEARCH = "name_for_search"
        private const val NAME = "name"
        private const val CATALOG = "catalog"
        val MOVIE_CLASS = MovieMainInfo::class.java
    }

    private lateinit var firestore: FirebaseFirestore

    private fun getFirestore(): FirebaseFirestore {
        if (!::firestore.isInitialized) {
            firestore = Firebase.firestore
        }
        return firestore
    }

    override suspend fun getMovieList(catalog: String): Result<List<MovieMainInfo>> {
        val snapshot = getFirestore().collection(COLLECTION_MOVIES).whereEqualTo(CATALOG, catalog).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMovieListBySearch(searchQuery: String): Result<List<MovieFullInfo>> {
        val snapshot =
            getFirestore().collection(COLLECTION_MOVIES).whereEqualTo(NAME_FOR_SEARCH, searchQuery.lowercase())
                .get().await()
        return getResult(snapshot)
    }

    override suspend fun getRecommendedMovies(): Result<List<MovieMainInfo>> {
        val snapshot = getFirestore().collection(COLLECTION_RECOMMENDED_MOVIES).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMoviesByGenre(genre: String): Result<List<MovieMainInfo>> {
        val snapshot = getFirestore().collection(genre.lowercase()).orderBy(NAME).get().await()
        return getResult(snapshot)
    }

    override suspend fun getMovieInfo(idMovie: String): Result<List<MovieFullInfo>> {
        val snapshot = getFirestore().collection(COLLECTION_MOVIES).whereEqualTo(ID, idMovie)
            .get().await()
        return getResult(snapshot)
    }

    override suspend fun getCarouselMovieList(): Result<List<MovieCarousel>> {
        val snapshot = getFirestore().collection(COLLECTION_CAROUSEL).get().await()
        return getResult(snapshot)
    }

    override suspend fun getNewMovieList(): Result<List<MovieMainInfo>> {
        val snapshot = getFirestore().collection(COLLECTION_NEW_MOVIE).get().await()
        return getResult(snapshot)
    }

    override suspend fun getWeekendMovieList(): Result<List<MovieMainInfo>> {
        val snapshot = getFirestore().collection(COLLECTION_WEEKEND_MOVIE).get().await()
        return getResult(snapshot)
    }

    override suspend fun getFascinatingSeriesList(): Result<List<MovieMainInfo>> {
        val snapshot = getFirestore().collection(COLLECTION_FASCINATING_SERIES).get().await()
        return getResult(snapshot)
    }

    private inline fun <reified T: Any> getResult(snapshot: QuerySnapshot): Result<List<T>> {
        return try {
            if (!snapshot.isEmpty) {
                Result.SUCCESS(snapshot.toObjects(T::class.java))
            } else {
                Result.ERROR("error")
            }
        } catch (e: Exception) {
            Result.ERROR(e.message.toString())
        }
    }
}