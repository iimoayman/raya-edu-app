package com.example.raya_edu.view.MyRaya.Student_service

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class Add_new_statement:Fragment(R.layout.add_new_statement) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backBtn : ImageView = view.findViewById(R.id.back_btn_2)
        backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}