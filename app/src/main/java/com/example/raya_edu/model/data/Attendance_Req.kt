package com.example.raya_edu.model.data

import com.google.gson.annotations.SerializedName

data class AttendanceRequest(
    @SerializedName("student_id") val studentId: Int,
    @SerializedName("qr_code") val qrCode: String
)
