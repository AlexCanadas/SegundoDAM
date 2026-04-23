package com.example.ligaapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LigasFragment : Fragment(R.layout.fragment_ligas) {

    private lateinit var recyclerLigas: RecyclerView
    private val listaLigas = mutableListOf<Liga>()
    private lateinit var adapter: LeagueAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerLigas = view.findViewById(R.id.recyclerLigas)
        recyclerLigas.layoutManager = LinearLayoutManager(requireContext())

        adapter = LeagueAdapter(listaLigas) { liga ->
            val bundle = Bundle().apply {
                putString("ligaId", liga.idLeague)
                putString("ligaNombre", liga.strLeague)
            }
            findNavController().navigate(R.id.action_ligas_to_equipos, bundle)
        }

        recyclerLigas.adapter = adapter

        cargarLigas()
    }

    private fun cargarLigas() {
        RetrofitClient.instance.getLigas().enqueue(object : Callback<LeagueResponse> {
            override fun onResponse(
                call: Call<LeagueResponse>,
                response: Response<LeagueResponse>
            ) {
                if (response.isSuccessful) {
                    val ligas = response.body()?.leagues ?: emptyList()
                    val ligasFutbol = ligas.filter { it.strSport == "Soccer" }

                    listaLigas.clear()
                    listaLigas.addAll(ligasFutbol)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error al cargar ligas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}