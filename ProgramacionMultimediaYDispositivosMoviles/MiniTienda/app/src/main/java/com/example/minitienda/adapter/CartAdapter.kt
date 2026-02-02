package com.example.minitienda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minitienda.databinding.ItemCartBinding
import com.example.minitienda.model.CartItem

/**
 * Adapter del CARRITO:
 * - Muestra nombre y cantidad
 * - Botones + / - / borrar llaman callbacks para modificar CartStore
 */
class CartAdapter(
    private var items: List<CartItem>,
    private val onPlus: (Int) -> Unit,
    private val onMinus: (Int) -> Unit,
    private val onRemove: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.VH>() {

    class VH(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val productId = item.product.id

        holder.binding.txtNombre.text = item.product.title
        holder.binding.txtCantidad.text = "x${item.quantity}"

        holder.binding.btnMas.setOnClickListener { onPlus(productId) }
        holder.binding.btnMenos.setOnClickListener { onMinus(productId) }
        holder.binding.btnBorrar.setOnClickListener { onRemove(productId) }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newList: List<CartItem>) {
        items = newList
        notifyDataSetChanged()
    }
}
