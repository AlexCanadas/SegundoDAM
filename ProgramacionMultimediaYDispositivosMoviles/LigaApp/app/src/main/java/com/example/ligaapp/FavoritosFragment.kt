package com.example.ligaapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {

    private lateinit var recycler: RecyclerView
    private lateinit var tvVacio: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.recyclerFavoritos)
        tvVacio = view.findViewById(R.id.tvSinFavoritos)

        actualizarVista()
    }

    private fun actualizarVista() {
        if (FavoritosManager.favoritos.isEmpty()) {
            tvVacio.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        } else {
            tvVacio.visibility = View.GONE
            recycler.visibility = View.VISIBLE
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = EquipoAdapter(FavoritosManager.favoritos) { equipo ->
                FavoritosManager.toggleFavorito(equipo)
                actualizarVista()
            }
        }
    }
}
