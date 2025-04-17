package com.example.raya_edu.view.MyRaya.Portal

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.raya_edu.view.MyRaya.Portal.Attendance.Attendance_Fragment
import com.example.raya_edu.view.MyRaya.Portal.EduPortal.EduPortal

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        EduPortal(),
        OnlineSessions(),
        Attendance_Fragment(),
    )

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
