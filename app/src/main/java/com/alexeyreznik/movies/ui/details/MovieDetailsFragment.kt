package com.alexeyreznik.movies.ui.details

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexeyreznik.movies.R
import com.alexeyreznik.movies.data.models.Movie
import com.alexeyreznik.movies.databinding.FragmentMovieDetailsBinding
import com.alexeyreznik.movies.ui.list.MoviesListRouterImpl
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.getViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var movie: Movie
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = arguments?.getSerializable(EXTRA_MOVIE_OVERVIEW) as Movie

        viewModel = getViewModel()
        viewModel.setMovie(movie)
        binding.viewModel = viewModel
    }

    companion object {
        const val EXTRA_MOVIE_OVERVIEW = "movie_overview"

        @JvmStatic
        fun newInstance(movie: Movie) = MovieDetailsFragment()
                .apply {
                    val args = Bundle()
                    args.putSerializable(EXTRA_MOVIE_OVERVIEW, movie)
                    arguments = args
                }
    }
}
