package com.alexeyreznik.movies.ui.list


import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexeyreznik.movies.R
import com.alexeyreznik.movies.databinding.FragmentMoviesListBinding
import com.alexeyreznik.movies.ui.list.adapter.MoviesAdapter
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.getViewModel

class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var viewModel: MoviesListViewModel
    private val router by lazy { MoviesListRouterImpl(this) }
    private val disposable by lazy { CompositeDisposable() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel()
        binding.viewModel = viewModel

        val adapter = MoviesAdapter()
        adapter.bind(viewModel.movies)
        disposable.add(
                adapter.onItemClick.subscribe {
                    router.toMovieDetails(it)
                }
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = getLayoutManager(resources.configuration)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        binding.recyclerView.layoutManager = getLayoutManager(newConfig)

    }

    private fun getLayoutManager(configuration: Configuration?): RecyclerView.LayoutManager {
        return when (configuration?.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> GridLayoutManager(context, 2)
            else -> GridLayoutManager(context, 3)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MoviesListFragment()
    }
}
