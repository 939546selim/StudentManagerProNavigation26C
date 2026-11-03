package com.ahona.studentmanagerpronavigation26c.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahona.studentmanagerpronavigation26c.ui.screens.StudentDetailsScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.StudentListScreen
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.ahona.studentmanagerpronavigation26c.ui.screens.AddStudentScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.EditStudentScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.SearchStudentsScreen
import com.ahona.studentmanagerpronavigation26c.ui.screens.SettingsScreen

@Composable
fun TypeSafeNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StudentListRoute
    ) {

        composable<StudentListRoute> {

            StudentListScreen(
                onAddStudentClick = {
                    navController.navigate(AddStudentRoute)
                },

                onSearchClick = {
                    navController.navigate(SearchStudentsRoute)
                },

                onSettingsClick = {
                    navController.navigate(SettingsRoute)
                },

                onStudentDetailsClick = {
                    navController.navigate(
                        StudentDetailsRoute(studentId = 101)
                    )
                }
            )
        }



        composable<AddStudentRoute> {

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

        composable<SearchStudentsRoute> {

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


        composable<SettingsRoute> {

            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<StudentDetailsRoute> { backStackEntry ->

            val route = backStackEntry.toRoute<StudentDetailsRoute>()

            val studentId = route.studentId

            StudentDetailsScreen(
                studentId = studentId,
                studentName = "Rahim",
                studentEmail = "rahim@example.com",

                onEditClick = {
                    navController.navigate(
                        EditStudentRoute(studentId = studentId)
                    )
                },

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<EditStudentRoute> { backStackEntry ->

            val route = backStackEntry.toRoute<EditStudentRoute>()

            val studentId = route.studentId

            EditStudentScreen(
                studentId = studentId,
                initialName = "Rahim",
                initialEmail = "rahim@example.com",

                onUpdateClick = { id, name, email ->

                    navController.navigate(StudentListRoute) {
                        popUpTo<StudentListRoute> {
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