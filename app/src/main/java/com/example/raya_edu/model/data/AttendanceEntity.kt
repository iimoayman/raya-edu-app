package com.example.raya_edu.model.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "attendance_table")
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val studentId: Int,
    val sessionId: Int,
    val sessionName: String,
    val scannedAt: String,
    val isValid: Boolean, // ✅ Store isValid field
    val message: String
)
