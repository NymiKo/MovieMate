package com.easyprog.android.moviemate.adapters.carousel_movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemCarouselMovieBinding
import com.easyprog.android.moviemate.utils.loadImage
import com.easyprog.android.moviemate.utils.loadImageWithoutTransformations

class CarouselMovieAdapter(
    private val actionListener: BaseActionListener
): RecyclerView.Adapter<CarouselMovieAdapter.CarouselMovieViewHolder>(), View.OnClickListener {

    var movieList: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemRangeInserted(0, newValue.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselMovieBinding.inflate(layoutInflater, parent, false)

        binding.imageMovieAvatar.setOnClickListener(this)

        return CarouselMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselMovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            imageMovieAvatar.tag = movie.id
            imageMovieAvatar.loadImageWithoutTransformations(movie.image)
        }
    }

    override fun getItemCount(): Int = movieList.size

    class CarouselMovieViewHolder(val binding: ItemCarouselMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val idMovie = v.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}