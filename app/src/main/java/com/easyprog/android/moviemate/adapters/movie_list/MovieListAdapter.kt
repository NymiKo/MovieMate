package com.easyprog.android.moviemate.adapters.movie_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemMovieListBinding
import com.easyprog.android.moviemate.utils.firstCharUppercase
import com.easyprog.android.moviemate.utils.loadImage

class MovieListAdapter(
    private val actionListener: MovieListActionListener
): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>(), View.OnClickListener {

    var movieList: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieListBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            layoutMovieItem.tag = movie.id
            textMovieName.text = movie.name
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

    override fun onClick(v: View?) {
        val idMovie = v?.tag as String
        actionListener.onMovieClick(idMovie)
    }
}