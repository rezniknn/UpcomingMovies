package com.alexeyreznik.movies.ui.list.adapter

import android.databinding.Observable
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alexeyreznik.movies.data.models.Movie
import com.alexeyreznik.movies.databinding.ItemMovieBinding
import io.reactivex.subjects.PublishSubject


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var onItemClick: PublishSubject<Movie> = PublishSubject.create()

    private var items = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var bindingProperty: ObservableField<List<Movie>>? = null

    fun bind(newBindingProperty: ObservableField<List<Movie>>?) {

        bindingProperty?.removeOnPropertyChangedCallback(onBindingPropertyChangedCallback)

        newBindingProperty?.addOnPropertyChangedCallback(onBindingPropertyChangedCallback)
        bindingProperty = newBindingProperty

        newBindingProperty?.get()?.let { items = it }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    private val onBindingPropertyChangedCallback = object : Observable.OnPropertyChangedCallback() {

        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            bindingProperty?.get()?.let { items = it }
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.viewModel = MovieViewModel.fromItem(item, binding.overview.context)
            binding.card.setOnClickListener { onItemClick.onNext(item) }
            binding.executePendingBindings()
        }

    }
}