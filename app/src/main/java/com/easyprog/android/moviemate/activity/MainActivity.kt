package com.easyprog.android.moviemate.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
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
    }

    fun hideBottomNavView() {
        binding.bottomNavView.visibility = View.GONE
    }

    fun showBottomNavView() {
        binding.bottomNavView.visibility = View.VISIBLE
    }
}