package com.example.raya_edu.viewmodel

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.raya_edu.R
import com.example.raya_edu.view.MyRaya.Portal.NotificationItem


class NotificationViewModel : ViewModel() {
    val unreadCount: MutableLiveData<Int> = MutableLiveData(1) // Start with 1 unread notification

    fun markAllRead() {
        unreadCount.postValue(0)
    }

    fun increaseUnread() {
        unreadCount.postValue((unreadCount.value ?: 0) + 1)
    }

    fun decreaseUnread() {
        unreadCount.value?.let {
            if (it > 0) unreadCount.postValue(it - 1)
        }
    }
}


class NotificationAdapter(
    private val notifications: MutableList<NotificationItem>,
    private val viewModel: NotificationViewModel
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]

        holder.description.text = notification.description

        if (notification.isRead) {
            holder.card.setCardBackgroundColor(Color.LTGRAY)
        } else {
            holder.card.setCardBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            if (!notification.isRead) {
                notification.isRead = true
                viewModel.decreaseUnread()
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount() = notifications.size

    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.notification_content)
        val card: CardView = view.findViewById(R.id.item_card)
    }
}