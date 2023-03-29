package com.easyprog.android.moviemate.fragments.search

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.lifecycleScope
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.activity.MainActivity
import com.easyprog.android.moviemate.databinding.FragmentSearchBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.showToast
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textLayoutSearch.setEndIconOnClickListener {
            showToast(R.string.search)
        }
        binding.textLayoutSearch.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textLayoutSearch.startIconDrawable = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_back)
                binding.textLayoutSearch.setStartIconOnClickListener {
                    binding.textLayoutSearch.editText?.clearFocus()
                    view.apply {
                        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
                    }
                }
            } else {
                binding.textLayoutSearch.startIconDrawable = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_search)
                binding.textLayoutSearch.setStartIconOnClickListener {
                    showToast(R.string.search)
                }
            }
        }
    }

}