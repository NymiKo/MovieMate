package com.easyprog.android.moviemate.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemMovieListBinding
import com.easyprog.android.moviemate.utils.loadImage

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    var movieList: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieListBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            textMovieName.text = movie.name
            imageMovieAvatar.loadImage(movie.avatar)
        }
    }

    override fun getItemCount(): Int = movieList.size

    class MovieListViewHolder(val binding: ItemMovieListBinding): RecyclerView.ViewHolder(binding.root)
}