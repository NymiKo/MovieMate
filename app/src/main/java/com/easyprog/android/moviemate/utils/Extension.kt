package com.easyprog.android.moviemate.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.activity.MainActivity
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun Fragment.showToast(@StringRes message: Int) {
    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showSnackBar(@StringRes message: Int, anchorView: Int? = R.id.bottom_nav_view) {
    val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
    if (anchorView != null) snackbar.setAnchorView(anchorView)
    snackbar.show()
}

fun <T> ImageView.loadImage(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}

fun <T> ImageView.loadImageCollapsingToolbar(image: T) {
    Glide.with(this).load(image).into(this)
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

fun Fragment.getColorFromTheme(color: Int): Int {
    return MaterialColors.getColor(requireContext(), color, Color.WHITE)
}

fun String.fromHtmlToString(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}