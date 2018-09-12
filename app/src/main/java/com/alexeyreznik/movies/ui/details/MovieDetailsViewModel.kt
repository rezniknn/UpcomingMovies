package com.alexeyreznik.movies.ui.details

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.alexeyreznik.movies.R
import com.alexeyreznik.movies.application.ResourcesProvider
import com.alexeyreznik.movies.data.models.Movie

class MovieDetailsViewModel(private val resourcesProvider: ResourcesProvider) : ViewModel() {

    val title = ObservableField<String>()
    val overview = ObservableField<String>()
    val language = ObservableField<String>()
    val backdrop = ObservableField<String>()
    val popularity = ObservableField<String>()

    fun setMovie(movie: Movie) {
        title.set(movie.title)
        overview.set(movie.overview)
        language.set(formatLanguage(movie.originalLanguage))
        backdrop.set(formatBackdropUrl(movie.backdropPath))
        popularity.set(formatPopularity(movie.popularity))
    }

    private fun formatBackdropUrl(backdropPath: String) =
            "https://image.tmdb.org/t/p/w500/$backdropPath"

    private fun formatLanguage(language: String) = "${resourcesProvider.getString(R.string.language)}: $language"

    private fun formatPopularity(popularity: Float): String =
            String.format("%s: %.1f", resourcesProvider.getString(R.string.popularity), popularity)

}