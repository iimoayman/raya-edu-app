package com.example.raya_edu.view.MyRaya.Portal.Attendance

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.raya_edu.R
class Attendance_Fragment:Fragment(R.layout.attendance){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val QrBtn = view.findViewById<ConstraintLayout>(R.id.qr_scan)
        val myAttendance:ConstraintLayout = view.findViewById(R.id.my_attendance)
        val schedule :ConstraintLayout = view.findViewById(R.id.schedule)
        QrBtn.setOnClickListener{
            navigateToQr()
        }
        schedule.setOnClickListener{
            navigateToSchedule()
        }
        myAttendance.setOnClickListener {
            navigateToAttendance()
        }
    }
    fun navigateToQr() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, QrScannerFragment())
            .addToBackStack(null)
            .commit()
    }
    fun navigateToSchedule() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MyScheduleFragment())
            .addToBackStack(null)
            .commit()
    }
    fun navigateToAttendance() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MyAttendance())
            .addToBackStack(null)
            .commit()
    }
}