package com.example.tiendaproductos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendaproductos.R
import com.example.tiendaproductos.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val productList: List<Product>,
    private val onAddToCart: (Product) -> Unit,
    private val showAddButton: Boolean = true // parámetro que controla la visibilidad del botón
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.productImage)
        val title: TextView = view.findViewById(R.id.productTitle)
        val price: TextView = view.findViewById(R.id.productPrice)
        val btnAddToCart: Button = view.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.title.text = product.title
        holder.price.text = "$${product.price}"
        Picasso.get().load(product.thumbnail).into(holder.image)
        if (showAddButton) {
            holder.btnAddToCart.visibility = View.VISIBLE
            holder.btnAddToCart.setOnClickListener { onAddToCart(product) }
        } else {
            holder.btnAddToCart.visibility = View.GONE
        }
    }

    override fun getItemCount() = productList.size
}