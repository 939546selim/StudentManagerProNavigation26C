//package com.ahona.studentmanagerpronavigation26c.ui.screens
//
//
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun StudentListScreen(
//    onAddStudentClick: () -> Unit,
//    onSearchClick: () -> Unit,
//    onSettingsClick: () -> Unit,
//    onStudentDetailsClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text(
//            text = "Student Manager Pro",
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            text = "Manage your students",
//            style = MaterialTheme.typography.bodyLarge
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Card(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(
//                    text = "Student List",
//                    style = MaterialTheme.typography.titleLarge
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "No students added yet."
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Button(
//            onClick = onAddStudentClick,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Add Student")
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedButton(
//            onClick = onStudentDetailsClick,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("View Student Details")
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedButton(
//            onClick = onSearchClick,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Search Students")
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedButton(
//            onClick = onSettingsClick,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Settings")
//        }
//    }
//}

package com.ahona.studentmanagerpronavigation26c.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahona.studentmanagerpronavigation26c.data.model.Student
import com.ahona.studentmanagerpronavigation26c.ui.viewmodel.StudentViewModel

@Composable
fun StudentListScreen(
    studentViewModel: StudentViewModel,
    onAddStudentClick: () -> Unit,
    onStudentClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {

    val students by studentViewModel.students.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Student Manager",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddStudentClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Student")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search Students")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Student List (${students.size})",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (students.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No students added yet.")
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    items = students,
                    key = { it.studentId }
                ) { student ->

                    StudentItem(
                        student = student,
                        onClick = {
                            onStudentClick(student.studentId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StudentItem(
    student: Student,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = student.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = student.email,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "ID: ${student.studentId}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}