package com.example.raya_edu.model.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.raya_edu.model.data.AttendanceEntity
import com.example.raya_edu.model.storage.AttendanceDao

@Database(entities = [AttendanceEntity::class], version = 1, exportSchema = false)
abstract class AttendanceDatabase : RoomDatabase() {
    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AttendanceDatabase? = null

        fun getDatabase(context: Context): AttendanceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceDatabase::class.java,
                    "attendance_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
