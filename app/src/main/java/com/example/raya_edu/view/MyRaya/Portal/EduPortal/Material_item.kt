package com.example.raya_edu.view.MyRaya.Portal.EduPortal

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.raya_edu.R
import com.example.raya_edu.view.MyRaya.Portal.EduPortal.Sessions.Portal_Material_Nav

class Material_item:Fragment(R.layout.material_item){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Material_0 :ConstraintLayout = view.findViewById(R.id.material_item0)
        val Material_1 :ConstraintLayout = view.findViewById(R.id.material_item1)
        val Material_2 :ConstraintLayout = view.findViewById(R.id.material_item2)
        val Material_3 :ConstraintLayout = view.findViewById(R.id.material_item3)
        val Material_4 :ConstraintLayout = view.findViewById(R.id.material_item4)
        Material_0.setOnClickListener{ReplaceFragment(Portal_Material_Nav())}
        Material_1.setOnClickListener{ReplaceFragment(Portal_Material_Nav())}
        Material_2.setOnClickListener{ReplaceFragment(Portal_Material_Nav())}
        Material_3.setOnClickListener{ReplaceFragment(Portal_Material_Nav())}
        Material_4.setOnClickListener{ReplaceFragment(Portal_Material_Nav())}
        val backBtn:ImageView = view.findViewById(R.id.back_btn_4)
        backBtn.setOnClickListener{requireActivity().supportFragmentManager.popBackStack()}

    }
    private  fun ReplaceFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}