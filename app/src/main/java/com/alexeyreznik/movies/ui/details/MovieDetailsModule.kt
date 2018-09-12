package com.alexeyreznik.movies.ui.details

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object MovieDetailsModule {

    fun build() = module {

        viewModel { MovieDetailsViewModel(get()) }
    }
}