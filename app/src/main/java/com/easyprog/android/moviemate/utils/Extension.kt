package com.easyprog.android.moviemate.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.activity.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

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

fun Fragment.navigateTo(fragment: Int, bundle: Bundle = bundleOf()) {
    findNavController().navigate(fragment, bundle)
}

fun Fragment.navigateTo(fragment: NavDirections) {
    findNavController().navigate(fragment)
}

fun Fragment.hideBottomNavView() {
    (requireActivity() as MainActivity).hideBottomNavView()
}

fun Fragment.showBottomNavView() {
    (requireActivity() as MainActivity).showBottomNavView()
}

fun EditText.textChangedListener(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

fun String.firstCharUppercase(): CharSequence {
    return this.replaceFirstChar { it.uppercase() }
}
