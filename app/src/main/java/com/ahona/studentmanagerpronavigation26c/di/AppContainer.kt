package com.ahona.studentmanagerpronavigation26c.di



import android.content.Context
import com.ahona.studentmanagerpronavigation26c.data.local.StudentDatabase
import com.ahona.studentmanagerpronavigation26c.data.repository.StudentRepository
import com.ahona.studentmanagerpronavigation26c.data.repository.StudentRepositoryImpl

class AppContainer(context: Context) {

    private val database = StudentDatabase.getDatabase(context)

    private val studentDao = database.studentDao()

    val studentRepository: StudentRepository =
        StudentRepositoryImpl(studentDao)
}