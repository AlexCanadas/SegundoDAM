package com.example.ligaapp

object FavoritosManager {
    val favoritos = mutableListOf<Equipo>()

    fun toggleFavorito(equipo: Equipo) {
        if (favoritos.any { it.nombre == equipo.nombre }) {
            favoritos.removeAll { it.nombre == equipo.nombre }
        } else {
            favoritos.add(equipo)
        }
    }

    fun esFavorito(equipo: Equipo): Boolean {
        return favoritos.any { it.nombre == equipo.nombre }
    }
}
