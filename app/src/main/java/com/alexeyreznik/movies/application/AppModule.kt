package com.alexeyreznik.movies.application

import android.content.Context
import com.alexeyreznik.movies.utils.DefaultSchedulers
import com.alexeyreznik.movies.utils.MoviesSchedulers
import org.koin.dsl.module.module

object AppModule {

    fun build(applicationContext: Context) = module {

        single { applicationContext }

        single<ResourcesProvider> { ResourcesProviderImpl(applicationContext) }

        single<MoviesSchedulers> { DefaultSchedulers() }
    }

}