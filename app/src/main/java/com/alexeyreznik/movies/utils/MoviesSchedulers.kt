package com.alexeyreznik.movies.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface MoviesSchedulers {

    fun io(): Scheduler

    fun mainThread(): Scheduler

    fun newThread(): Scheduler
}

class DefaultSchedulers : MoviesSchedulers {

    override fun io(): Scheduler = Schedulers.io()

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun newThread(): Scheduler = Schedulers.newThread()

}

class TestSchedulers : MoviesSchedulers {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun mainThread(): Scheduler = Schedulers.trampoline()

    override fun newThread(): Scheduler = Schedulers.trampoline()
}