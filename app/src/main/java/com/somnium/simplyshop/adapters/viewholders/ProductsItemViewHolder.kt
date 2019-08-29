package com.somnium.simplyshop.adapters.viewholders

import android.view.View
import android.widget.TextView
import com.somnium.simplyshop.R
import com.somnium.simplyshop.adapters.ClickableViewHolder
import com.somnium.simplyshop.entities.Product
import com.somnium.simplyshop.listeners.OnItemClickListener

class ProductsItemViewHolder(view: View, listener: OnItemClickListener) : ClickableViewHolder(view, listener) {
    var name: TextView = view.findViewById(R.id.product_name)
    var price: TextView = view.findViewById(R.id.product_price)

    fun bind(product: Product) {
        name.text = product.name
        price.text = name.context.getString(R.string.soms_short, product.price.toInt().toString())
    }
}

