package com.ahona.studentmanagerpronavigation26c.data.local



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahona.studentmanagerpronavigation26c.data.model.Student

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}