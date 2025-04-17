package com.example.raya_edu.view.MyRaya.Student_service

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class Proof_of_registration:Fragment(R.layout.proof_of_registration){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backBtn : ImageView = view.findViewById(R.id.back_btn_1)
        backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}