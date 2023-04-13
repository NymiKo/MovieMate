package com.easyprog.android.moviemate.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.RecommendedMoviesAdapter.RecommendedMoviesViewHolder
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemRecommendedMovieBinding
import com.easyprog.android.moviemate.utils.firstCharUppercase
import com.easyprog.android.moviemate.utils.loadImage

class RecommendedMoviesAdapter: RecyclerView.Adapter<RecommendedMoviesViewHolder>() {

    var recommendedMoviesList: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecommendedMovieBinding.inflate(layoutInflater, parent, false)
        return RecommendedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedMoviesViewHolder, position: Int) {
        val movie = recommendedMoviesList[position]
        holder.binding.apply {
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun getItemCount(): Int = recommendedMoviesList.size

    class RecommendedMoviesViewHolder(val binding: ItemRecommendedMovieBinding): RecyclerView.ViewHolder(binding.root)
}