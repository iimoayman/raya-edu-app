package com.example.raya_edu.model.storage
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raya_edu.model.data.AttendanceEntity
@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    @Query("SELECT * FROM attendance_table ORDER BY scannedAt DESC")
    suspend fun getAllAttendance(): List<AttendanceEntity>
}
