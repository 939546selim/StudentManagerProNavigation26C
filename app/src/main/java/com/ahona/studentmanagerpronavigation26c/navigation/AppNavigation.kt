//
//package com.ahona.studentmanagerpronavigation26c.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.ahona.studentmanagerpronavigation26c.ui.screens.SettingsScreen
//
//@Composable
//fun AppNavigation() {
//
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "student_graph"
//    ) {
//
//        // Student Nested Navigation Graph
//        studentNavigation(
//            navController = navController
//        )
//
//        // Settings Destination
//        composable(Routes.SETTINGS) {
//
//            SettingsScreen(
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )
//        }
//    }
//}