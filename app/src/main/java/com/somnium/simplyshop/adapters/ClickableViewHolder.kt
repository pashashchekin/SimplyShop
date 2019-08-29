package com.somnium.simplyshop.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.somnium.simplyshop.listeners.OnItemClickListener

abstract class ClickableViewHolder(view: View, private val listener: OnItemClickListener): RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}