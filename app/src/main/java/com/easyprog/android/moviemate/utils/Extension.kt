package com.easyprog.android.moviemate.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun Fragment.showToast(@StringRes message: Int) {
    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showSnackBar(@StringRes message: Int) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun <T> ImageView.loadImage(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}
