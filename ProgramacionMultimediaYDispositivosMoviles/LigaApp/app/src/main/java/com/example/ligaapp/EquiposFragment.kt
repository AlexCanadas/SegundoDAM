package com.example.ligaapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EquiposFragment : Fragment(R.layout.fragment_equipos) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ligaId = arguments?.getString("ligaId") ?: return

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerEquipos)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        RetrofitClient.instance.getEquiposPorLiga(ligaId).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    val equipos = response.body()?.teams ?: emptyList()
                    recycler.adapter = EquipoAdapter(equipos) { equipo ->
                        FavoritosManager.toggleFavorito(equipo)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al cargar equipos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
