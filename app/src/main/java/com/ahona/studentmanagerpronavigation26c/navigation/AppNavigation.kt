package com.ahona.studentmanagerpronavigation26c.navigation



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.STUDENT_LIST
    ) {

        composable(Routes.STUDENT_LIST) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Student List Screen")

                Button(
                    onClick = {
                        navController.navigate(Routes.ADD_STUDENT)
                    }
                ) {
                    Text("Add Student")
                }

                Button(
                    onClick = {
                        navController.navigate(Routes.SETTINGS)
                    }
                ) {
                    Text("Settings")
                }
            }
        }

        composable(Routes.ADD_STUDENT) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Add Student Screen")

                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back to Student List")
                }
            }
        }

        composable(Routes.SETTINGS) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Settings Screen")

                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back to Student List")
                }
            }
        }
    }
}