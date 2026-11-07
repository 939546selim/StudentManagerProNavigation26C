package com.ahona.studentmanagerpronavigation26c.data.repository



import com.ahona.studentmanagerpronavigation26c.data.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    fun getAllStudents(): Flow<List<Student>>

    suspend fun getStudentById(id: Int): Student?

    suspend fun insertStudent(student: Student)

    suspend fun updateStudent(student: Student)

    suspend fun deleteStudent(student: Student)

    fun searchStudents(query: String): Flow<List<Student>>
}