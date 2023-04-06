package com.easyprog.android.moviemate

import com.easyprog.android.moviemate.fragments.base.DispatchersList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class FakeDispatcherList: DispatchersList {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun ui(): CoroutineDispatcher = StandardTestDispatcher()
}