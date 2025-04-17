package com.example.raya_edu.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.raya_edu.R
import com.example.raya_edu.model.data.AttendanceEntity

class AttendanceAdapter(private var attendanceList: MutableList<AttendanceEntity>) :
    RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    class AttendanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val status: TextView = view.findViewById(R.id.status_attendance)
        val studentId: TextView = view.findViewById(R.id.student_id)
        val sessionId: TextView = view.findViewById(R.id.prof_id)
        val scannedAt: TextView = view.findViewById(R.id.scanned_at)
        val sessionName: TextView = view.findViewById(R.id.session_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attendance, parent, false)
        return AttendanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val attendance = attendanceList[position]

        holder.status.text = "الحالة : ${attendance.message}"
        holder.studentId.text = "كود الطالب : ${attendance.studentId}"
        holder.sessionId.text = "كود المحاضرة : ${attendance.sessionId}"
        holder.scannedAt.text = "وقت المسح : ${attendance.scannedAt}"
        holder.sessionName.text = "محاضرة : ${attendance.sessionName}"
    }

    override fun getItemCount(): Int = attendanceList.size

    fun updateList(newList: List<AttendanceEntity>) {
        attendanceList.clear()
        attendanceList.addAll(newList)
        notifyDataSetChanged()
    }
}
