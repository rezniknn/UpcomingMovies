package com.alexeyreznik.movies.ui.list

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.alexeyreznik.movies.data.models.Movie
import com.alexeyreznik.movies.domain.GetUpcomingMoviesUseCase
import com.alexeyreznik.movies.utils.MoviesSchedulers
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel(private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
                          private val moviesSchedulers: MoviesSchedulers) : ViewModel() {

    val movies = ObservableField<List<Movie>>()

    val loading = ObservableField<Boolean>()

    private val disposable = CompositeDisposable()

    init {
        refresh()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        disposable.add(
                getUpcomingMoviesUseCase.execute()
                        .doOnSubscribe { loading.set(true) }
                        .doOnTerminate { loading.set(false) }
                        .subscribeOn(moviesSchedulers.io())
                        .observeOn(moviesSchedulers.mainThread())
                        .subscribe({ list ->

                            movies.set(list)
                        }, { _ ->

                            movies.set(emptyList())
                        })
        )
    }
}