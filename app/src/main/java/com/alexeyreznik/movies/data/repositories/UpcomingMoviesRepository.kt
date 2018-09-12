package com.alexeyreznik.movies.data.repositories

import com.alexeyreznik.movies.data.api.MoviesApi
import com.alexeyreznik.movies.data.models.Movie
import io.reactivex.Observable

interface UpcomingMoviesRepository {

    fun getUpcomingMovies(): Observable<List<Movie>>

}

class UpcomingMoviesRepositoryImpl(private val moviesApi: MoviesApi) : UpcomingMoviesRepository {

    override fun getUpcomingMovies(): Observable<List<Movie>> = moviesApi.getUpcomingMovies().flatMap {
        Observable.just(it.results ?: emptyList())
    }

}