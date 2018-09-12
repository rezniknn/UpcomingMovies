package com.alexeyreznik.movies.ui.upcoming

import com.alexeyreznik.movies.data.models.Movie
import com.alexeyreznik.movies.domain.GetUpcomingMoviesUseCase
import com.alexeyreznik.movies.ui.list.MoviesListViewModel
import com.alexeyreznik.movies.utils.TestSchedulers
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import uk.co.jemos.podam.api.PodamFactoryImpl
import org.mockito.Mockito.`when` as whenever


class UpcomingViewModelTest {

    @Mock
    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    private lateinit var viewModel: MoviesListViewModel

    private val useCaseResponse by lazy { listOf(PodamFactoryImpl().manufacturePojo(Movie::class.java)) }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_useCaseExecutedOnInit() {

        whenever(getUpcomingMoviesUseCase.execute()).thenReturn(Observable.just(useCaseResponse))


        viewModel = MoviesListViewModel(getUpcomingMoviesUseCase, TestSchedulers())


        verify(getUpcomingMoviesUseCase).execute()
        assertEquals(useCaseResponse, viewModel.movies.get())
    }

    @Test
    fun test_useCaseExecutedOnRefresh() {

        whenever(getUpcomingMoviesUseCase.execute()).thenReturn(Observable.just(useCaseResponse))


        viewModel = MoviesListViewModel(getUpcomingMoviesUseCase, TestSchedulers())
        viewModel.refresh()


        verify(getUpcomingMoviesUseCase, times(2)).execute()
        assertEquals(useCaseResponse, viewModel.movies.get())
    }

    @Test
    fun test_loadingIndicator() {

        val delayer = PublishSubject.create<Boolean>()
        whenever(getUpcomingMoviesUseCase.execute())
                .thenReturn(
                        Observable.just(useCaseResponse).delaySubscription(delayer)
                )


        viewModel = MoviesListViewModel(getUpcomingMoviesUseCase, TestSchedulers())


        assertEquals(true, viewModel.loading.get())
        delayer.onComplete()
        assertEquals(false, viewModel.loading.get())

    }
}