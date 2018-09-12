package com.alexeyreznik.movies.ui.list.adapter

import android.content.Context
import android.databinding.ObservableField
import com.alexeyreznik.movies.R
import com.alexeyreznik.movies.data.models.Movie


class MovieViewModel {

    val overview = ObservableField<String>()
    val originalTitle = ObservableField<String>()
    val popularity = ObservableField<String>()
    val imageUrl = ObservableField<String>()

    companion object {
        fun fromItem(item: Movie, context: Context) =
                MovieViewModel().apply {
                    overview.set(item.overview)
                    originalTitle.set(item.originalTitle)

                    imageUrl.set(formatPosterUrl(item.posterPath))
                    popularity.set(formatPopularity(context, item.popularity))
                }

        private fun formatPosterUrl(posterPath: String) =
                "https://image.tmdb.org/t/p/w200/$posterPath"

        private fun formatPopularity(context: Context, popularity: Float): String =
                String.format("%s: %.1f", context.getString(R.string.popularity), popularity)
    }
}