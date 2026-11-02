
package com.ahona.studentmanagerpronavigation26c.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ahona.studentmanagerpronavigation26c.ui.screens.StudentListScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.AddStudentScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.SearchStudentsScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.StudentDetailsScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.EditStudentScreen

fun NavGraphBuilder.studentNavigation(
    navController: NavHostController
) {

    navigation(
        startDestination = Routes.STUDENT_LIST,
        route = "student_graph"
    ) {

        // Student List
        composable(Routes.STUDENT_LIST) {

            StudentListScreen(
                onAddStudentClick = {
                    navController.navigate(Routes.ADD_STUDENT)
                },
                onSearchClick = {
                    navController.navigate(Routes.SEARCH_STUDENTS)
                },
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                },
                onStudentDetailsClick = {
                    navController.navigate(
                        "student_details/101"
                    )
                }
            )
        }

        // Add Student
        composable(Routes.ADD_STUDENT) {

            val context = LocalContext.current

            AddStudentScreen(
                onSaveClick = { name, email ->

                    Toast.makeText(
                        context,
                        "Name: $name\nEmail: $email",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Search Students
        composable(Routes.SEARCH_STUDENTS) {

            val context = LocalContext.current

            SearchStudentsScreen(
                onSearchClick = { query ->

                    Toast.makeText(
                        context,
                        "Searching for: $query",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Student Details
        composable(
            route = Routes.STUDENT_DETAILS,
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val studentId = backStackEntry.arguments
                ?.getInt("studentId") ?: -1

            StudentDetailsScreen(
                studentId = studentId,
                studentName = "Rahim",
                studentEmail = "rahim@example.com",
                onEditClick = {
                    navController.navigate(Routes.EDIT_STUDENT)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Edit Student
        composable(Routes.EDIT_STUDENT) {

            val context = LocalContext.current

            EditStudentScreen(
                studentId = 101,
                initialName = "Rahim",
                initialEmail = "rahim@example.com",
                onUpdateClick = { id, name, email ->

                    Toast.makeText(
                        context,
                        "Student $id updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    navController.navigate(Routes.STUDENT_LIST) {
                        popUpTo(Routes.STUDENT_LIST) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}