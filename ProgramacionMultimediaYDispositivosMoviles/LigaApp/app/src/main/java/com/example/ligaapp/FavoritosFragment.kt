package com.example.ligaapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerFavoritos)
        val tvVacio = view.findViewById<TextView>(R.id.tvSinFavoritos)

        if (FavoritosManager.favoritos.isEmpty()) {
            tvVacio.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        } else {
            tvVacio.visibility = View.GONE
            recycler.visibility = View.VISIBLE
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = EquipoAdapter(FavoritosManager.favoritos)
        }
    }
}
