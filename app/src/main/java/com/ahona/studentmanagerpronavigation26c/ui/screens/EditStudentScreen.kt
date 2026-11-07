//

package com.ahona.studentmanagerpronavigation26c.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahona.studentmanagerpronavigation26c.data.model.Student

@Composable
fun EditStudentScreen(
    student: Student?,
    onUpdateClick: (Student) -> Unit,
    onBackClick: () -> Unit
) {
    var name by remember(student?.studentId) {
        mutableStateOf(student?.name ?: "")
    }

    var email by remember(student?.studentId) {
        mutableStateOf(student?.email ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Edit Student",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (student == null) {

            Text("Student not found.")

        } else {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Student Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Student Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.isNotBlank() && email.isNotBlank()) {
                        onUpdateClick(
                            student.copy(
                                name = name.trim(),
                                email = email.trim()
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && email.isNotBlank()
            ) {
                Text("Update Student")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}