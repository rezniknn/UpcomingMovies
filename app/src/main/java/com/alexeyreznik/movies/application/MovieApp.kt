package com.alexeyreznik.movies.application

import android.app.Application
import com.alexeyreznik.movies.data.api.ApiModule
import com.alexeyreznik.movies.ui.details.MovieDetailsModule
import com.alexeyreznik.movies.ui.list.MoviesListModule
import org.koin.android.ext.android.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin(this,
                listOf(
                        AppModule.build(applicationContext),
                        ApiModule.build(applicationContext),
                        MoviesListModule.build(),
                        MovieDetailsModule.build()
                ))

    }

    companion object {
        lateinit var instance: MovieApp
    }
}