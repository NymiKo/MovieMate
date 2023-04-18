package com.easyprog.android.moviemate.adapters.view_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.easyprog.android.moviemate.fragments.main_tab.MainTabFragment
import com.easyprog.android.moviemate.fragments.movie_list.MovieListTabFragment
import com.easyprog.android.moviemate.fragments.serials.SerialsListTabFragment

class PagerFragmentAdapter(
    private val fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainTabFragment()
            1 -> MovieListTabFragment()
            2 -> SerialsListTabFragment()
            else -> MainTabFragment()
        }
    }
}