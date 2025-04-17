package com.example.raya_edu.view.MyRaya.Portal.Attendance
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.raya_edu.R
import com.example.raya_edu.model.data.AttendanceEntity
import com.example.raya_edu.viewmodel.AttendanceAdapter
import com.example.raya_edu.viewmodel.MyAttendanceViewModel

class MyAttendance : Fragment(R.layout.myattendance) {

    private val viewModel: MyAttendanceViewModel by viewModels()

    private val attendanceList = mutableListOf<AttendanceEntity>()
    private val adapter = AttendanceAdapter(attendanceList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var studentIdInput = view.findViewById<EditText>(R.id.student_id_input)
        var qrCodeInput = view.findViewById<EditText>(R.id.qr_code_input)
        var markAttendanceBtn = view.findViewById<Button>(R.id.mark_attendance_btn)
        var progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        var errorText = view.findViewById<TextView>(R.id.error_attendacne)
        var recyclerView = view.findViewById<RecyclerView>(R.id.my_attendance_list)


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        markAttendanceBtn.setOnClickListener {
            val studentId = studentIdInput.text.toString().toIntOrNull()
            val qrCode = qrCodeInput.text.toString()

            if (studentId == null || qrCode.isEmpty()) {
                errorText.text = "Please enter a valid Student ID and QR Code"
                errorText.visibility = View.VISIBLE
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE
            errorText.visibility = View.GONE
            viewModel.markAttendance(studentId, qrCode)
        }


        viewModel.savedAttendance.observe(viewLifecycleOwner) { savedList ->
            progressBar.visibility = View.GONE
            savedList?.let { adapter.updateList(it) } // ✅ Prevents null crash
        }


        viewModel.loadSavedAttendance()
    }

}
