package com.somnium.simplyshop.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.somnium.simplyshop.listeners.OnAdapterBottomReachedListener
import com.somnium.simplyshop.listeners.OnAdapterItemClickListener
import com.somnium.simplyshop.listeners.OnItemClickListener


abstract class ClickableRecyclerAdapter<ItemType, VH:ClickableViewHolder>: RecyclerView.Adapter<VH>(), OnItemClickListener {
    var items: List<ItemType> = listOf()
    var emptyElementsView: View? = null
    var progressView: View? = null
    var recyclerView: RecyclerView? = null

    var listener: OnAdapterItemClickListener<ItemType> = object: OnAdapterItemClickListener<ItemType> {
        override fun onAdapterItemClick(item: ItemType) {

        }
    }

    var onBottomReachedListener: OnAdapterBottomReachedListener = object: OnAdapterBottomReachedListener{
        override fun onReachedBottom() {

        }
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBind(holder, position)
        if(position >= items.size - 1){
            onBottomReachedListener.onReachedBottom()
        }
    }

    abstract fun onBind(holder: VH, position: Int)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onItemClick(position: Int) {
        if(position != RecyclerView.NO_POSITION && items.size > position) {
            listener.onAdapterItemClick(items[position])
        }
    }

    open fun notifyItemsChanged(){
        emptyElementsView?.visibility = if(items.isEmpty()) View.VISIBLE else View.GONE
        recyclerView?.visibility = if(items.isEmpty()) View.GONE else View.VISIBLE
        progressView?.visibility = View.GONE
        notifyDataSetChanged()
    }
}
