package com.example.raya_edu.view.MyRaya

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.raya_edu.R
import com.example.raya_edu.view.MyRaya.Student_service.Add_new_statement
import com.example.raya_edu.view.MyRaya.Student_service.New_card
import com.example.raya_edu.view.MyRaya.Student_service.Proof_of_registration
import com.example.raya_edu.view.MyRaya.Student_service.Student_Guide

class Student_Service : Fragment(R.layout.table){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Proof_of_registration:ConstraintLayout = view.findViewById(R.id.Proof_of_registration)
        Proof_of_registration.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,Proof_of_registration())
                .addToBackStack(null)
                .commit()
        }
        val guideBtn = view.findViewById<ConstraintLayout>(R.id.Student_Guide)
        guideBtn.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,Student_Guide())
                .addToBackStack(null)
                .commit() 
        }
        val New_card:ConstraintLayout = view.findViewById(R.id.New_card)
        New_card.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,New_card())
                .addToBackStack(null)
                .commit()
        }
        val addNewStatement : ConstraintLayout = view.findViewById(R.id.add_new_statement)
        addNewStatement.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,Add_new_statement())
                .addToBackStack(null)
                .commit()
        }
        val TypeOfSerPlace = view.findViewById<TextView>(R.id.type_ofSer)
        val StatusOfSerPlace = view.findViewById<TextView>(R.id.status_ofSer)
        val DateOfSerPlace = view.findViewById<TextView>(R.id.date_ofSer)
        val GoesToPlace = view.findViewById<TextView>(R.id.goes_to)
        val CommentsPlace = view.findViewById<TextView>(R.id.comments)
        val AdminCommentPlace = view.findViewById<TextView>(R.id.admin_comment)
        var TypeOfSer = "الكارنية"
        var StatusOfSer = "تم التسليم"
        var DateOfSer = "2024-11-10"
        var GoesTo = null
        var Comments = null
        var AdminComment = "أ/محمد الحويحي"
        TypeOfSerPlace.text= "نوع الخدمة : $TypeOfSer"
        StatusOfSerPlace.text= "حالة الخدمة : $StatusOfSer"
        DateOfSerPlace.text= "تاريخ الخدمة : $DateOfSer"
        GoesToPlace.text= "موجه الي : $GoesTo"
        CommentsPlace.text= "ملاحظاتك : $Comments"
        AdminCommentPlace.text= "ملاحظات الادمن : $AdminComment"


    }
}