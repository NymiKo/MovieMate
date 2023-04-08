package com.easyprog.android.moviemate.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.MoviesByCategoryAdapter.MoviesByCategoryViewHolder
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemCategoryMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class MoviesByCategoryAdapter: RecyclerView.Adapter<MoviesByCategoryViewHolder>() {

    var movieList: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryMovieBinding.inflate(layoutInflater, parent, false)
        return MoviesByCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesByCategoryViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            textElementId.text = position.toString()
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun getItemCount(): Int = movieList.size

    class MoviesByCategoryViewHolder(val binding: ItemCategoryMovieBinding): RecyclerView.ViewHolder(binding.root)
}