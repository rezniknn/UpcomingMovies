package com.alexeyreznik.movies.data.models

import com.squareup.moshi.Json
import java.io.Serializable

class Movie(@Json(name = "poster_path") val posterPath: String = "",
            @Json(name = "adult") val adult: Boolean = false,
            @Json(name = "overview") val overview: String = "",
            @Json(name = "genre_ids") val genreIds: List<Int> = emptyList(),
            @Json(name = "id") val id: Int = 0,
            @Json(name = "original_title") val originalTitle: String = "",
            @Json(name = "original_language") val originalLanguage: String = "",
            @Json(name = "title") val title: String = "",
            @Json(name = "backdrop_path") val backdropPath: String = "",
            @Json(name = "popularity") val popularity: Float = 0F,
            @Json(name = "vote_count") val voteCount: Int = 0,
            @Json(name = "video") val video: Boolean = false,
            @Json(name = "vote_average") val voteAverage: Float = 0F) : Serializable