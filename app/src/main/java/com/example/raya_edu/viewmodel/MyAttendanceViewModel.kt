package com.example.raya_edu.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.raya_edu.model.data.AttendanceEntity
import com.example.raya_edu.model.repository.AttendanceRepository
import kotlinx.coroutines.launch

class MyAttendanceViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AttendanceRepository(application)

    private val _savedAttendance = MutableLiveData<List<AttendanceEntity>>()
    val savedAttendance: LiveData<List<AttendanceEntity>> get() = _savedAttendance

    private val _errorMessage = MutableLiveData<String?>()  // 🔥 Add this line
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun markAttendance(studentId: Int, qrCode: String) {
        viewModelScope.launch {
            try {
                val response = repository.markAttendance(studentId, qrCode)
                loadSavedAttendance() // ✅ Refresh UI after saving
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Unknown error occurred") // 🔥 Store error
            }
        }
    }

    fun loadSavedAttendance() {
        viewModelScope.launch {
            try {
                _savedAttendance.postValue(repository.getSavedAttendance())
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Error loading attendance data")
            }
        }
    }


}
