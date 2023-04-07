package com.easyprog.android.moviemate.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.View
import com.easyprog.android.moviemate.databinding.FragmentCategoryBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("ARGUMENT", requireArguments().getString("category").toString())
    }
}