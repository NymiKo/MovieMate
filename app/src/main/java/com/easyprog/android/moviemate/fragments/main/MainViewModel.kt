package com.easyprog.android.moviemate.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _positionTab = MutableLiveData<Int>()
    val positionTab: LiveData<Int> = _positionTab

    fun saveTabPosition(position: Int) {
        _positionTab.value = position
    }
}