package com.ahona.studentmanagerpronavigation26c.data.repository


import com.ahona.studentmanagerpronavigation26c.data.local.StudentDao
import com.ahona.studentmanagerpronavigation26c.data.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl(
    private val studentDao: StudentDao
) : StudentRepository {

    override fun getAllStudents(): Flow<List<Student>> {
        return studentDao.getAllStudents()
    }

    override suspend fun getStudentById(id: Int): Student? {
        return studentDao.getStudentById(id)
    }

    override suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    override suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    override suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }

    override fun searchStudents(query: String): Flow<List<Student>> {
        return studentDao.searchStudents(query)
    }
}