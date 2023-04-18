package com.easyprog.android.moviemate.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.databinding.FragmentMainBinding
import com.easyprog.android.moviemate.fragments.base.BaseFragment
import com.easyprog.android.moviemate.utils.mainNavGraphNavigateTo
import com.easyprog.android.moviemate.utils.navigateTo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
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
        viewModel.positionTab.observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(it)?.select()
        }
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> mainNavGraphNavigateTo(R.id.mainTabFragment)
                    1 -> mainNavGraphNavigateTo(R.id.movieListTabFragment)
                    2 -> mainNavGraphNavigateTo(R.id.serialsListTabFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveTabPosition(binding.tabLayout.selectedTabPosition)
    }
}