////package com.ahona.studentmanagerpronavigation26c.ui.screens
////
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.Column
////import androidx.compose.foundation.layout.Spacer
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.foundation.layout.height
////import androidx.compose.foundation.layout.padding
////import androidx.compose.material3.Button
////import androidx.compose.material3.Card
////import androidx.compose.material3.MaterialTheme
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.unit.dp
////
////@Composable
////fun StudentDetailsScreen(
////    studentId: Int,
////    studentName: String,
////    studentEmail: String,
////    onEditClick: () -> Unit,
////    onBackClick: () -> Unit
////) {
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////            .padding(20.dp),
////        verticalArrangement = Arrangement.Top
////    ) {
////
////        Text(
////            text = "Student Details",
////            style = MaterialTheme.typography.headlineMedium
////        )
////
////        Spacer(modifier = Modifier.height(24.dp))
////
////        Card(
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            Column(
////                modifier = Modifier.padding(16.dp)
////            ) {
////                Text(
////                    text = "Student ID: $studentId",
////                    style = MaterialTheme.typography.titleMedium
////                )
////
////                Spacer(modifier = Modifier.height(12.dp))
////
////                Text(
////                    text = "Name: $studentName"
////                )
////
////                Spacer(modifier = Modifier.height(8.dp))
////
////                Text(
////                    text = "Email: $studentEmail"
////                )
////            }
////        }
////
////        Spacer(modifier = Modifier.height(24.dp))
////
////        Button(
////            onClick = onEditClick,
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            Text("Edit Student")
////        }
////
////        Spacer(modifier = Modifier.height(8.dp))
////
////        Button(
////            onClick = onBackClick,
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            Text("Back")
////        }
////    }
////}
//
//package com.ahona.studentmanagerpronavigation26c.ui.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.ahona.studentmanagerpronavigation26c.data.model.Student
//
//@Composable
//fun StudentDetailsScreen(
//    student: Student?,
//    onEditClick: (Int) -> Unit,
//    onBackClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//
//        Text(
//            text = "Student Details",
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        if (student == null) {
//
//            Text("Student not found.")
//
//        } else {
//
//            Text(
//                text = "Student ID: ${student.studentId}",
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text("Name: ${student.name}")
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text("Email: ${student.email}")
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    onEditClick(student.studentId)
//                }
//            ) {
//                Text("Edit Student")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedButton(
//            onClick = onBackClick
//        ) {
//            Text("Back")
//        }
//    }
//}

package com.ahona.studentmanagerpronavigation26c.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahona.studentmanagerpronavigation26c.data.model.Student
import androidx.compose.runtime.*

@Composable
fun StudentDetailsScreen(
    student: Student?,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Student) -> Unit,
    onBackClick: () -> Unit
) {

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Student Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (student == null) {

            Text("Student not found.")

        } else {

            Text("Student ID: ${student.studentId}")

            Spacer(modifier = Modifier.height(12.dp))

            Text("Name: ${student.name}")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Email: ${student.email}")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onEditClick(student.studentId)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Student")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    showDeleteDialog = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete Student")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onBackClick
        ) {
            Text("Back")
        }

        if (showDeleteDialog && student != null) {

            AlertDialog(
                onDismissRequest = {
                    showDeleteDialog = false
                },

                title = {
                    Text("Delete Student?")
                },

                text = {
                    Text(
                        "Are you sure you want to delete ${student.name}?"
                    )
                },

                confirmButton = {
                    TextButton(
                        onClick = {
                            onDeleteClick(student)
                            showDeleteDialog = false
                        }
                    ) {
                        Text("Delete")
                    }
                },

                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
