package com.somnium.simplyshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.somnium.simplyshop.R
import com.somnium.simplyshop.adapters.viewholders.ProductsItemViewHolder
import com.somnium.simplyshop.entities.Product

class ProductsAdapter : ClickableRecyclerAdapter<Product,ProductsItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_in_catedory_item, parent, false)
        return ProductsItemViewHolder(view,this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBind(holder: ProductsItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}