package com.example.ligaapp

object FavoritosManager {
    val favoritos = mutableListOf<Equipo>()

    fun toggleFavorito(equipo: Equipo) {
        if (esFavorito(equipo)) {
            favoritos.removeAll { it.idTeam == equipo.idTeam }
        } else {
            favoritos.add(equipo)
        }
    }

    fun esFavorito(equipo: Equipo): Boolean {
        return favoritos.any { it.idTeam == equipo.idTeam }
    }
}
