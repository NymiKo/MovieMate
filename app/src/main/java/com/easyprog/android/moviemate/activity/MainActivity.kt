package com.easyprog.android.moviemate.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.easyprog.android.moviemate.R
import com.easyprog.android.moviemate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}