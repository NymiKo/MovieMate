package com.easyprog.android.moviemate.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuItemCompat
import androidx.core.view.MenuProvider
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.activity.MainActivity
import com.easyprog.android.moviemate.databinding.FragmentSearchBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.showToast

class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var adapter: ArrayAdapter<String>
    private var list = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}