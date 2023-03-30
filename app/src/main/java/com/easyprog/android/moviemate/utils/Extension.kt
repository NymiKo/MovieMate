package com.easyprog.android.moviemate.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.easyprog.android.moviemate.activity.MainActivity
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

fun Fragment.hideKeyboard() {
    val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireView().windowToken, 0)
}

fun Fragment.navigateTo(fragment: Int) {
    findNavController().navigate(fragment)
}

fun Fragment.hideBottomNavView() {
    (requireActivity() as MainActivity).hideBottomNavView()
}

fun Fragment.showBottomNavView() {
    (requireActivity() as MainActivity).showBottomNavView()
}
