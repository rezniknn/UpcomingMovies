package com.alexeyreznik.movies.ui.list

import android.support.v4.app.Fragment
import com.alexeyreznik.movies.R
import com.alexeyreznik.movies.data.models.Movie
import com.alexeyreznik.movies.ui.details.MovieDetailsFragment

interface MoviesListRouter {

    fun toMovieDetails(item: Movie)

}

class MoviesListRouterImpl(private val fragment: Fragment) : MoviesListRouter {

    override fun toMovieDetails(item: Movie) {
        val ft = fragment.activity?.supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.fragment_container, MovieDetailsFragment.newInstance(item))
        ft?.addToBackStack(null)
        ft?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        ft?.commit()
    }
}