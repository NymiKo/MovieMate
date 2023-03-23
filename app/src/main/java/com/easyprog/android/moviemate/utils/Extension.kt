package com.easyprog.android.moviemate.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showToast(@StringRes message: Int) {
    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}
