package com.easyprog.android.moviemate.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.adapters.view_pager.PagerFragmentAdapter
import com.easyprog.android.moviemate.databinding.FragmentMainBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.mainNavGraphNavigateTo
import com.easyprog.android.moviemate.utils.navigateTo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.viewPager.adapter = PagerFragmentAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabName = listOf(R.string.main, R.string.films, R.string.serials)
            tab.text = getString(tabName[position])
        }.attach()
        viewModel.positionTab.observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(it)?.select()
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveTabPosition(binding.tabLayout.selectedTabPosition)
    }
}