package com.example.minitienda.model

// Modelo de producto (lo que viene de la API)
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val thumbnail: String
)

