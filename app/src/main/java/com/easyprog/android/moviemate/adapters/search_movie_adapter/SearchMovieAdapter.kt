package com.easyprog.android.moviemate.adapters.search_movie_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.base.BaseActionListener
import com.easyprog.android.moviemate.adapters.search_movie_adapter.SearchMovieAdapter.SearchMovieViewHolder
import com.easyprog.android.moviemate.data.models.MovieFullInfo
import com.easyprog.android.moviemate.databinding.ItemSearchMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class SearchMovieAdapter(
    private val actionListener: BaseActionListener
) : RecyclerView.Adapter<SearchMovieViewHolder>(), View.OnClickListener {

    var movieList: List<MovieFullInfo> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchMovieBinding.inflate(layoutInflater, parent, false)

        binding.root.setOnClickListener(this)

        return SearchMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            root.tag = movie.id
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
            textMovieRate.apply {
                text = movie.rating.toString()
                setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        setColorByRating(movie.rating.toInt())
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = movieList.size

    class SearchMovieViewHolder(val binding: ItemSearchMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun setColorByRating(rating: Int): Int {
        return when (rating) {
            in 8..10 -> R.color.green
            in 4..7 -> R.color.yellow
            else -> R.color.red
        }
    }

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}