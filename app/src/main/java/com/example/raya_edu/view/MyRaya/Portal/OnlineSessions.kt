package com.example.raya_edu.view.MyRaya.Portal

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class OnlineSessions:Fragment(R.layout.online_sessions) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numOfSession = 1
        val sessionCountTextView: TextView = view.findViewById(R.id.online_sessions)
        sessionCountTextView.text = "المحاضرات الاونلاين المقبلة : $numOfSession"

    }
}