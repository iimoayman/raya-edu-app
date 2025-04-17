package com.example.raya_edu.model.data

import com.google.gson.annotations.SerializedName

data class AttendanceResponse(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: AttendanceData?
)

data class AttendanceData(
    @SerializedName("id") val id: Int,
    @SerializedName("student_id") val studentId: Int,
    @SerializedName("session_id") val sessionId: Int,
    @SerializedName("scanned_at") val scannedAt: String,
    @SerializedName("is_valid") val isValid: Boolean,
    @SerializedName("session_name") val session_name: String

    )