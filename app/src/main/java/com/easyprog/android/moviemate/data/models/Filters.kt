package com.easyprog.android.moviemate.data.models

data class Filters(
    val minRating: Int = 0,
    val maxRating: Int = 10,
    val genre: String = "",
    val country: String = "",
    val year: String = ""
)
