package com.easyprog.android.moviemate.adapters.movie_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.adapters.BaseAdapter
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemMovieListBinding
import com.easyprog.android.moviemate.utils.loadImage

class MovieListAdapter(
    private val actionListener: BaseActionListener
) : BaseAdapter<ItemMovieListBinding>(ItemMovieListBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            layoutMovieItem.tag = movie.id
            textMovieName.text = movie.name
            imageMovieAvatar.loadImage(movie.image)
            textRatingMovie.apply {
                text = movie.rating.toString()
                background =
                    ContextCompat.getDrawable(root.context, setColorByRating(movie.rating))
            }
        }
    }

    private fun setColorByRating(rating: Int): Int {
        return when (rating) {
            in 8..10 -> R.drawable.high_rating_background
            in 4..7 -> R.drawable.medium_rating_background
            else -> R.drawable.low_rating_background
        }
    }

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}