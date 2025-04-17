package com.example.raya_edu.model.repository

import android.content.Context
import com.example.raya_edu.model.data.AttendanceEntity
import com.example.raya_edu.model.data.AttendanceRequest
import com.example.raya_edu.model.data.AttendanceResponse
import com.example.raya_edu.model.database.AttendanceDatabase
import com.example.raya_edu.model.network.AttendanceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AttendanceRepository(context: Context) {
    private val api = RetrofitClient.api
    private val attendanceDao = AttendanceDatabase.getDatabase(context).attendanceDao()

    suspend fun markAttendance(studentId: Int, qrCode: String): AttendanceResponse? {
        val attendanceRequest = AttendanceRequest(studentId, qrCode)
        val response = api.markAttendance(attendanceRequest)

        if (response.isSuccessful && response.body() != null) {
            response.body()?.data?.let { data ->
                val attendanceEntity = AttendanceEntity(
                    studentId = data.studentId,
                    sessionId = data.sessionId,
                    sessionName = data.session_name,
                    scannedAt = data.scannedAt,
                    isValid = data.isValid,
                    message = response.body()?.message ?: "No message"
                )
                attendanceDao.insertAttendance(attendanceEntity) // ✅ Save to Room
            }
            return response.body()
        }
        return null
    }

    suspend fun getSavedAttendance(): List<AttendanceEntity> {
        return attendanceDao.getAllAttendance()
    }
}

object RetrofitClient {
    private const val BASE_URL = "https://c3c7-197-63-190-213.ngrok-free.app"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: AttendanceApi = retrofit.create(AttendanceApi::class.java)
}
