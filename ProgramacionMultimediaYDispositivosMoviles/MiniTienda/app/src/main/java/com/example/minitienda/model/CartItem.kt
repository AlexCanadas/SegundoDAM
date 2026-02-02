package com.example.minitienda.model

// Un item del carrito = producto + cantidad
data class CartItem(
    val product: Product,
    var quantity: Int
)
