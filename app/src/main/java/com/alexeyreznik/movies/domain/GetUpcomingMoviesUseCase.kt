package com.alexeyreznik.movies.domain

import com.alexeyreznik.movies.data.models.Movie
import com.alexeyreznik.movies.data.repositories.UpcomingMoviesRepository
import io.reactivex.Observable

interface GetUpcomingMoviesUseCase {
    fun execute(): Observable<List<Movie>>
}

class GetUpcomingMoviesUseCaseImp(private val upcomingMoviesRepository: UpcomingMoviesRepository) : GetUpcomingMoviesUseCase {

    override fun execute(): Observable<List<Movie>> = upcomingMoviesRepository.getUpcomingMovies()
}