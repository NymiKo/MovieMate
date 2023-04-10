package com.easyprog.android.moviemate.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_container)

        binding.bottomNavView.setupWithNavController(navController)
//        val options = NavOptions.Builder()
//            .setLaunchSingleTop(true)
//            .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
//            .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
//            .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
//            .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
//            .setPopUpTo(navController.graph.startDestinationId, false)
//            .build()
//
//        binding.bottomNavView.setOnItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.movieListFragment -> {
//                    navController.navigate(R.id.movieListFragment, null, options)
//                }
//                R.id.searchFragment -> {
//                    navController.navigate(R.id.searchFragment, null, options)
//                }
//            }
//            true
//        }
//        binding.bottomNavView.setOnItemReselectedListener {
//            return@setOnItemReselectedListener
//        }
    }

    fun hideBottomNavView() {
        binding.bottomNavView.visibility = View.GONE
    }

    fun showBottomNavView() {
        binding.bottomNavView.visibility = View.VISIBLE
    }
}