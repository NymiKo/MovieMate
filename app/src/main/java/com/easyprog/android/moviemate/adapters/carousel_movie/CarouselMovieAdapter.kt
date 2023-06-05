package com.easyprog.android.moviemate.adapters.carousel_movie

import android.view.View
import com.easyprog.android.moviemate.adapters.base.BaseActionListener
import com.easyprog.android.moviemate.adapters.base.BaseAdapter
import com.easyprog.android.moviemate.data.models.MovieCarousel
import com.easyprog.android.moviemate.databinding.ItemCarouselMovieBinding
import com.easyprog.android.moviemate.utils.loadImageWithoutTransformations

class CarouselMovieAdapter(
    private val actionListener: BaseActionListener
) : BaseAdapter<ItemCarouselMovieBinding, MovieCarousel>(ItemCarouselMovieBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            imageMovieAvatar.tag = movie.id
            imageMovieAvatar.loadImageWithoutTransformations(movie.image)
        }
    }

    override fun onClick(v: View) {
        val idMovie = v.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}