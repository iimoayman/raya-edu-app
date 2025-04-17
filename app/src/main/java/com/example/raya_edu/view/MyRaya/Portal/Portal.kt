package com.example.raya_edu.view.MyRaya.Portal

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.raya_edu.R
import com.example.raya_edu.viewmodel.NotificationViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PortalFragment : Fragment() {
    private lateinit var notificationViewModel: NotificationViewModel
    private lateinit var notificationBadge: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.img_profile)
        imageView.setOnClickListener {
            showImageDialog()
        }
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.top_navigation)
        val notificationBtn: ImageView = view.findViewById(R.id.notification)
        notificationBadge = view.findViewById(R.id.notification_badge)

        notificationViewModel = ViewModelProvider(requireActivity()).get(NotificationViewModel::class.java)

        notificationViewModel.unreadCount.observe(viewLifecycleOwner) { count ->
            if (count > 0) {
                notificationBadge.visibility = View.VISIBLE
                notificationBadge.text = count.toString()
            } else {
                notificationBadge.visibility = View.GONE
            }
        }

        notificationBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, NotificationsFragment())
                .addToBackStack(null)
                .commit()

            notificationViewModel.markAllRead()
        }

        val adapter = TabAdapter(this)
        viewPager.adapter = adapter

        val tabTitles = listOf("المنصة النعليمية","المحاضرات الاونلاين", "الحضور والبيانات "  )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val customView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null)
            val text = customView.findViewById<TextView>(R.id.tab_text)
            text.text = tabTitles[position]
            tab.customView = customView
        }.attach()
    }
    private fun showImageDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.profile_dialog)

        val closeButton = dialog.findViewById<ImageView>(R.id.closeButton)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}
