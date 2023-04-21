package com.easyprog.android.moviemate.adapters.carousel_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemCarouselMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class CarouselMovieAdapter: RecyclerView.Adapter<CarouselMovieAdapter.CarouselMovieViewHolder>() {

    var movieList: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemRangeInserted(0, newValue.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselMovieBinding.inflate(layoutInflater, parent, false)
        return CarouselMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselMovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            imageMovieAvatar.loadImage(movie.image)
        }
    }

    override fun getItemCount(): Int = 4

    class CarouselMovieViewHolder(val binding: ItemCarouselMovieBinding): RecyclerView.ViewHolder(binding.root)
}