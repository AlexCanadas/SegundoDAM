package com.example.ligaapp

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class LigasFragment : Fragment(R.layout.fragment_ligas) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerLigas)
        val ligas = cargarLigas()

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = LeagueAdapter(ligas) { liga ->
            val bundle = bundleOf("ligaNombre" to liga.nombre)
            findNavController().navigate(R.id.action_ligas_to_equipos, bundle)
        }
    }

    private fun cargarLigas(): List<Liga> {
        val inputStream = resources.openRawResource(R.raw.ligas)
        val reader = InputStreamReader(inputStream)
        val tipoLista = object : TypeToken<List<Liga>>() {}.type
        return Gson().fromJson(reader, tipoLista) ?: emptyList()
    }
}
