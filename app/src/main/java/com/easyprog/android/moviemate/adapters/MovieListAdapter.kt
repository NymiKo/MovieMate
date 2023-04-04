package com.easyprog.android.moviemate.adapters

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.R
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
            textMovieName.text = movie.name.replaceFirstChar { it.uppercase() }
            imageMovieAvatar.loadImage(movie.image)
            textRatingMovie.apply {
                text = movie.rating
                background = ContextCompat.getDrawable(root.context, setColorByRating(movie.rating.toInt()))
            }
        }
    }

    override fun getItemCount(): Int = movieList.size

    class MovieListViewHolder(val binding: ItemMovieListBinding): RecyclerView.ViewHolder(binding.root)

    private fun setColorByRating(rating: Int): Int {
        return when(rating) {
            in 8..10 -> R.drawable.high_rating_background
            in 4..7 -> R.drawable.medium_rating_background
            else -> R.drawable.low_rating_background
        }
    }
}