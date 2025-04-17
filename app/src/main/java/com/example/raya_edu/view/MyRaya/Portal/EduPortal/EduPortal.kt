package com.example.raya_edu.view.MyRaya.Portal.EduPortal

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class EduPortal : Fragment(R.layout.edu_portal) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val openSession0:ConstraintLayout = view.findViewById(R.id.open_session_0)
        val openSession1:ConstraintLayout = view.findViewById(R.id.open_session_1)
        val openSession2:ConstraintLayout = view.findViewById(R.id.open_session_2)
        val openSession3:ConstraintLayout = view.findViewById(R.id.open_session_3)


        openSession0.setOnClickListener{replaceFragment(Material_item())}
        openSession1.setOnClickListener{replaceFragment(Material_item())}
        openSession2.setOnClickListener{replaceFragment(Material_item())}
        openSession3.setOnClickListener{replaceFragment(Material_item())}




    }
    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
