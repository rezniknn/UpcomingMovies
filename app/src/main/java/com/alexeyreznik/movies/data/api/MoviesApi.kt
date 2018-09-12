package com.alexeyreznik.movies.data.api

import com.alexeyreznik.movies.data.models.Movie
import io.reactivex.Observable
import retrofit2.http.GET

interface MoviesApi {

    @GET("/$API_VERSION/movie/upcoming")
    fun getUpcomingMovies(): Observable<ApiResponse<List<Movie>>>

    companion object {
        const val API_VERSION = "3"
    }
}