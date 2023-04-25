package com.easyprog.android.moviemate.adapters.movies_by_category

import android.view.View
import com.easyprog.android.moviemate.adapters.base.BaseActionListener
import com.easyprog.android.moviemate.adapters.base.BaseAdapter
import com.easyprog.android.moviemate.data.model.MovieMainInfo
import com.easyprog.android.moviemate.databinding.ItemMoviesByCategoryBinding
import com.easyprog.android.moviemate.utils.loadImage

class MoviesByCategoryAdapter(
    private val actionListener: BaseActionListener
) : BaseAdapter<ItemMoviesByCategoryBinding, MovieMainInfo>(ItemMoviesByCategoryBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            root.tag = movie.id
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }

}