package com.ahona.studentmanagerpronavigation26c.navigation



sealed interface NavigationEvent {

    data object StudentSaved : NavigationEvent

    data object NavigateBack : NavigationEvent

    data class OpenStudentDetails(
        val studentId: Int
    ) : NavigationEvent
}