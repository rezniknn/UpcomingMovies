package com.alexeyreznik.movies.ui.list

import com.alexeyreznik.movies.data.repositories.UpcomingMoviesRepository
import com.alexeyreznik.movies.data.repositories.UpcomingMoviesRepositoryImpl
import com.alexeyreznik.movies.domain.GetUpcomingMoviesUseCase
import com.alexeyreznik.movies.domain.GetUpcomingMoviesUseCaseImp
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object MoviesListModule {

    fun build() = module {

        viewModel { MoviesListViewModel(get(), get()) }

        single<UpcomingMoviesRepository> { UpcomingMoviesRepositoryImpl(get()) }

        single<GetUpcomingMoviesUseCase> { GetUpcomingMoviesUseCaseImp(get()) }

    }
}