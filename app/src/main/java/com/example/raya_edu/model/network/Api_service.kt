package com.example.raya_edu.model.network

import com.example.raya_edu.model.data.AttendanceRequest
import com.example.raya_edu.model.data.AttendanceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AttendanceApi {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("student/attendance")
    suspend fun markAttendance(@Body attendanceRequest: AttendanceRequest): Response<AttendanceResponse>
}