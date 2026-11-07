package com.ahona.studentmanagerpronavigation26c.data.local



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ahona.studentmanagerpronavigation26c.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM students ORDER BY studentId DESC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE studentId = :id")
    suspend fun getStudentById(id: Int): Student?

    @Query("""
        SELECT * FROM students
        WHERE name LIKE '%' || :query || '%'
        OR email LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun searchStudents(query: String): Flow<List<Student>>
}
