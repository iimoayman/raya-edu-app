package com.example.raya_edu.view.MyRaya.Portal

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.raya_edu.R
import com.example.raya_edu.viewmodel.NotificationAdapter
import com.example.raya_edu.viewmodel.NotificationViewModel

class NotificationsFragment : Fragment(R.layout.notifications) {
    private lateinit var viewModel: NotificationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back_btn = view.findViewById<ImageView>(R.id.back_btn)
        back_btn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        viewModel = ViewModelProvider(requireActivity()).get(NotificationViewModel::class.java)

        viewModel.markAllRead()

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_notifications)

        val notifications = mutableListOf(
            NotificationItem(
                1,
                "اهلا بك",
                "مرحبًا بك، ولاء! \uD83C\uDF89\n" +
                        "يسعدنا انضمامك إلى منصة معهد راية العالي! نحن هنا لدعمك في رحلتك التعليمية ومساعدتك على تحقيق أهدافك الأكاديمية والمهنية.",
                false
            )
        )

        val adapter = NotificationAdapter(notifications, viewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}

data class NotificationItem(
    val id: Int,
    val title: String,
    val description: String,
    var isRead: Boolean
)




