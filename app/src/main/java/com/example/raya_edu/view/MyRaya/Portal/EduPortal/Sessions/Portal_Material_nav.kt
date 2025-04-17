package com.example.raya_edu.view.MyRaya.Portal.EduPortal.Sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.raya_edu.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Portal_Material_Nav : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.portal_material_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager_1)
        val tabLayout = view.findViewById<TabLayout>(R.id.top_navigation_1)
        val back_btn1 = view.findViewById<ImageView>(R.id.back_btn_1)
        back_btn1.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        val adapter = Nav_Materials(this)
        viewPager.adapter = adapter

        val tabTitles = listOf("المحاضرات والمصادر", "الامتحانات" , "إنهاء الوحدة" )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val customView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item_1, null)
            val text = customView.findViewById<TextView>(R.id.tab_text_1)
            text.text = tabTitles[position]
            tab.customView = customView
        }.attach()
    }
}