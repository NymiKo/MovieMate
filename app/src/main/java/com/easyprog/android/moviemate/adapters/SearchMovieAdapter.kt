package com.easyprog.android.moviemate.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.SearchMovieAdapter.SearchMovieViewHolder
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemSearchMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class SearchMovieAdapter: RecyclerView.Adapter<SearchMovieViewHolder>() {

    var movieList: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchMovieBinding.inflate(layoutInflater, parent, false)
        return SearchMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name.replaceFirstChar { it.uppercase() }
            textMovieRate.text = "8.5"
        }
    }

    override fun getItemCount(): Int = movieList.size

    class SearchMovieViewHolder(val binding: ItemSearchMovieBinding): RecyclerView.ViewHolder(binding.root)
}