package com.ahona.studentmanagerpronavigation26c.navigation

import kotlinx.serialization.Serializable

@Serializable
data object StudentListRoute

@Serializable
data object AddStudentRoute

@Serializable
data object SearchStudentsRoute

@Serializable
data object SettingsRoute

@Serializable
data class StudentDetailsRoute(
    val studentId: Int
)

@Serializable
data class EditStudentRoute(
    val studentId: Int
)