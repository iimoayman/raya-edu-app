package com.example.raya_edu.view.MyRaya

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.raya_edu.R
import com.example.raya_edu.view.MyRaya.Portal.PortalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class B_NavFragment : Fragment(R.layout.botton_nav_bar) {

    lateinit var bottomNav: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        bottomNav = view.findViewById(R.id.bottom_navigation)

        loadFragment(PortalFragment())

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(PortalFragment())
                    true
                }
                R.id.student_service -> {
                    loadFragment(Student_Service())
                    true
                }
                R.id.exams -> {
                    loadFragment(Exams())
                    true
                }
                R.id.results -> {
                    loadFragment(Results())
                    true
                }
                R.id.records -> {
                    loadFragment(Records())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val currentFragment = childFragmentManager.findFragmentById(R.id.container)

        if (currentFragment?.javaClass == fragment.javaClass) {
            return
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
