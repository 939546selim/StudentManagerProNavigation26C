package com.ahona.studentmanagerpronavigation26c.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahona.studentmanagerpronavigation26c.data.model.Student
import com.ahona.studentmanagerpronavigation26c.data.repository.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    val students = repository.getAllStudents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addStudent(name: String, email: String) {
        viewModelScope.launch {
            repository.insertStudent(
                Student(
                    name = name,
                    email = email
                )
            )
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.deleteStudent(student)
        }
    }

    fun getStudentById(
        id: Int,
        onResult: (Student?) -> Unit
    ) {
        viewModelScope.launch {
            onResult(repository.getStudentById(id))
        }
    }

    fun searchStudents(query: String) = repository.searchStudents(query)
}