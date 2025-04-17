package com.example.raya_edu.view.MyRaya.Student_service

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class New_card:Fragment(R.layout.new_card) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backBtn : ImageView = view.findViewById(R.id.back_btn_3)
        backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}