package com.example.minitienda.data

import com.example.minitienda.model.CartItem
import com.example.minitienda.model.Product

/**
 * CartStore = carrito GLOBAL en memoria.
 *
 * ¿Por qué? Para simplificar:
 * - No pasamos listas por Intent
 * - MainActivity añade productos
 * - SecondActivity lee y modifica el mismo carrito
 *
 * Esto dura mientras la app esté viva.
 */
object CartStore {

    // Mapa por id: si añades el mismo producto, aumentamos cantidad
    private val itemsById = linkedMapOf<Int, CartItem>()

    fun add(product: Product) {
        val existing = itemsById[product.id]
        if (existing == null) {
            itemsById[product.id] = CartItem(product, 1)
        } else {
            existing.quantity += 1
        }
    }

    fun increase(productId: Int) {
        itemsById[productId]?.let { it.quantity += 1 }
    }

    fun decrease(productId: Int) {
        val item = itemsById[productId] ?: return
        item.quantity -= 1
        if (item.quantity <= 0) {
            itemsById.remove(productId)
        }
    }

    fun remove(productId: Int) {
        itemsById.remove(productId)
    }

    fun clear() {
        itemsById.clear()
    }

    fun items(): List<CartItem> = itemsById.values.toList()

    fun total(): Double =
        itemsById.values.sumOf { it.product.price * it.quantity }
}
