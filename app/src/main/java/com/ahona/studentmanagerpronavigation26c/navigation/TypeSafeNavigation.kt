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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahona.studentmanagerpronavigation26c.StudentManagerApplication
import com.ahona.studentmanagerpronavigation26c.data.model.Student
import com.ahona.studentmanagerpronavigation26c.ui.viewmodel.StudentViewModel
import com.ahona.studentmanagerpronavigation26c.ui.viewmodel.StudentViewModelFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TypeSafeNavigation() {

    val navController = rememberNavController()
    val navigationViewModel: NavigationViewModel = viewModel()

    val context = LocalContext.current

    val appContainer = remember {
        (context.applicationContext as StudentManagerApplication)
            .appContainer
    }

    val studentViewModel: StudentViewModel = viewModel(
        factory = StudentViewModelFactory(
            appContainer.studentRepository
        )
    )

    LaunchedEffect(navigationViewModel) {
        navigationViewModel.navigationEvents.collect { event ->

            when (event) {

                NavigationEvent.StudentSaved -> {
                    navController.popBackStack()
                }

                NavigationEvent.NavigateBack -> {
                    navController.popBackStack()
                }

                is NavigationEvent.OpenStudentDetails -> {
                    navController.navigate(
                        StudentDetailsRoute(event.studentId)
                    )
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = StudentListRoute
    ) {

//        composable<StudentListRoute> {
//
//            StudentListScreen(
//                onAddStudentClick = {
//                    navController.navigate(AddStudentRoute)
//                },
//
//                onSearchClick = {
//                    navController.navigate(SearchStudentsRoute)
//                },
//
//                onSettingsClick = {
//                    navController.navigate(SettingsRoute)
//                },
//
//                onStudentDetailsClick = {
//                    navigationViewModel.sendNavigationEvent(
//                        NavigationEvent.OpenStudentDetails( studentId = 101)
//                    )
//
//                }
//            )
//        }

        composable<StudentListRoute> {

            StudentListScreen(
                studentViewModel = studentViewModel,

                onAddStudentClick = {
                    navController.navigate(AddStudentRoute)
                },

                onStudentClick = { studentId ->
                    navigationViewModel.sendNavigationEvent(
                        NavigationEvent.OpenStudentDetails(studentId)
                    )
                },

                onSearchClick = {
                    navController.navigate(SearchStudentsRoute)
                },

                onSettingsClick = {
                    navController.navigate(SettingsRoute)
                }
            )
        }



//        composable<AddStudentRoute> {
//
//            val context = LocalContext.current
//
//            AddStudentScreen(
//                onSaveClick = { name, email ->
//
//                    Toast.makeText(
//                        context,
//                        "Name: $name\nEmail: $email",
//                        Toast.LENGTH_LONG
//                    ).show()
//
//                    navController.popBackStack()
//                },
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )
//        }

//        composable<AddStudentRoute> {
//
//            val context = LocalContext.current
//
//            AddStudentScreen(
//                onSaveClick = { name, email ->
//
//                    Toast.makeText(
//                        context,
//                        "Student saved: $name",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    navigationViewModel.sendNavigationEvent(
//                        NavigationEvent.StudentSaved
//                    )
//                },
//                onBackClick = {
//                    navigationViewModel.sendNavigationEvent(
//                        NavigationEvent.NavigateBack
//                    )
//                }
//            )
//        }

        composable<AddStudentRoute> {

            AddStudentScreen(
                onSaveClick = { name, email ->

                    studentViewModel.addStudent(
                        name = name,
                        email = email
                    )

                    navigationViewModel.sendNavigationEvent(
                        NavigationEvent.StudentSaved
                    )
                },

                onBackClick = {
                    navigationViewModel.sendNavigationEvent(
                        NavigationEvent.NavigateBack
                    )
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

//        composable<StudentDetailsRoute> { backStackEntry ->
//
//            val route = backStackEntry.toRoute<StudentDetailsRoute>()
//
//            val studentId = route.studentId
//
//            StudentDetailsScreen(
//                studentId = studentId,
//                studentName = "Rahim",
//                studentEmail = "rahim@example.com",
//
//                onEditClick = {
//                    navController.navigate(
//                        EditStudentRoute(studentId = studentId)
//                    )
//                },
//
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )
//        }

        composable<StudentDetailsRoute> { backStackEntry ->

            val route = backStackEntry.toRoute<StudentDetailsRoute>()

            var student by remember(route.studentId) {
                mutableStateOf<Student?>(null)
            }

            LaunchedEffect(route.studentId) {
                studentViewModel.getStudentById(route.studentId) {
                    student = it
                }
            }

//            StudentDetailsScreen(
//                student = student,
//
//                onEditClick = { studentId ->
//                    navController.navigate(
//                        EditStudentRoute(studentId)
//                    )
//                },
//
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )

            StudentDetailsScreen(
                student = student,

                onEditClick = { studentId ->
                    navController.navigate(
                        EditStudentRoute(studentId)
                    )
                },

                onDeleteClick = { studentToDelete ->

                    studentViewModel.deleteStudent(studentToDelete)

                    navController.popBackStack(
                        StudentListRoute,
                        inclusive = false
                    )
                },

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

//        composable<EditStudentRoute> { backStackEntry ->
//
//            val route = backStackEntry.toRoute<EditStudentRoute>()
//
//            val studentId = route.studentId
//
//            EditStudentScreen(
//                studentId = studentId,
//                initialName = "Rahim",
//                initialEmail = "rahim@example.com",
//
//                onUpdateClick = { id, name, email ->
//
//                    navController.navigate(StudentListRoute) {
//                        popUpTo<StudentListRoute> {
//                            inclusive = false
//                        }
//                        launchSingleTop = true
//                    }
//                },
//
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )
//        }

        composable<EditStudentRoute> { backStackEntry ->

            val route = backStackEntry.toRoute<EditStudentRoute>()

            var student by remember(route.studentId) {
                mutableStateOf<Student?>(null)
            }

            LaunchedEffect(route.studentId) {
                studentViewModel.getStudentById(route.studentId) {
                    student = it
                }
            }

            EditStudentScreen(
                student = student,

                onUpdateClick = { updatedStudent ->

                    studentViewModel.updateStudent(updatedStudent)

                    navController.popBackStack()
                },

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}