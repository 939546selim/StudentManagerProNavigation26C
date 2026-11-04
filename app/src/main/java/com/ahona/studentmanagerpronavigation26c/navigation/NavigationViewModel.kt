package com.ahona.studentmanagerpronavigation26c.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationViewModel : ViewModel() {

    private val _navigationEvents =
        MutableSharedFlow<NavigationEvent>(
            replay = 0,
            extraBufferCapacity = 1
        )

    val navigationEvents = _navigationEvents.asSharedFlow()

    fun sendNavigationEvent(event: NavigationEvent) {
        _navigationEvents.tryEmit(event)
    }
}