package com.easyprog.android.moviemate.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.view_pager.PagerFragmentAdapter
import com.easyprog.android.moviemate.databinding.FragmentMainBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.showBottomNavView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onStart() {
        super.onStart()
        showBottomNavView()
    }

    private fun setupView() {
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = PagerFragmentAdapter(requireActivity())
            offscreenPageLimit = 3
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabName = listOf(R.string.main, R.string.films, R.string.serials)
            tab.text = getString(tabName[position])
        }.attach()
    }
}