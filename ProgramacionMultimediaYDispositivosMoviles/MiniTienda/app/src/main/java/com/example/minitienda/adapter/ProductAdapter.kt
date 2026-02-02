package com.example.minitienda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minitienda.databinding.ItemProductBinding
import com.example.minitienda.model.Product
import com.squareup.picasso.Picasso

/**
 * Adapter de la TIENDA:
 * - Muestra Product (title + price)
 * - Botón "Añadir" llama a un callback para que MainActivity decida qué hacer
 */
class ProductAdapter(
    private var items: List<Product>,
    private val onAdd: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    class VH(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    // RecyclerView llama aquí cuando necesita crear una fila NUEVA (plantilla)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    // RecyclerView llama aquí para "rellenar" una fila con el item de position
    override fun onBindViewHolder(holder: VH, position: Int) {
        val product = items[position]
        holder.binding.txtNombre.text = product.title
        holder.binding.txtPrecio.text = "${product.price} €"

        holder.binding.btnAdd.setOnClickListener {
            onAdd(product) // el adapter NO conoce el carrito, solo avisa
        }

        Picasso.get()
            .load(product.thumbnail)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_delete)
            .into(holder.binding.imgProducto)

    }

    override fun getItemCount(): Int = items.size

    // Para cambiar la lista cuando filtras por categoría
    fun submitList(newList: List<Product>) {
        items = newList
        notifyDataSetChanged()
    }
}
