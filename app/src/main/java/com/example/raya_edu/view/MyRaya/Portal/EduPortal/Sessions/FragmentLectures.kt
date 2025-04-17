package com.example.raya_edu.view.MyRaya.Portal.EduPortal.Sessions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class Fragment_Lectures : Fragment(R.layout.fragment_lectures) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionTypesButton: ConstraintLayout = view.findViewById(R.id.sessions_types)
        val sessionTypesButton_0: ConstraintLayout = view.findViewById(R.id.sessions_types0)


        val videoUrl = "https://drive.google.com/file/d/1lmWrKTHr9emHWJQtpNkH8PRv90WIFVB5/view?t=3"
        val pdf = "https://drive.google.com/file/d/1cU6BgPHZzj-pEeytJyVFvOV-7_eEw35l/view"
        var currentUrl = videoUrl
        var currentUrl0 = pdf


        sessionTypesButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl))
            startActivity(intent)
        }
        sessionTypesButton_0.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl0))
            startActivity(intent)
        }
    }
}
