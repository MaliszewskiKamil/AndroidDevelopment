package com.example.level4task1.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.level4task1.data.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ShoppingListAdapter(private val products: List<Product>) :
        RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(product: Product){
            itemView.tvProductName.text = product.productText
            itemView.tvProductQuantity.text = product.productQuantity.toString()+"x "
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {

    }
}