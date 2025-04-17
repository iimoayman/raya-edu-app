package com.example.raya_edu.view.MyRaya.Portal.EduPortal.Sessions

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class Nav_Materials(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        Fragment_Lectures(),
        Fragment_Exams(),
        Fragment_UnitEnd(),
    )

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

