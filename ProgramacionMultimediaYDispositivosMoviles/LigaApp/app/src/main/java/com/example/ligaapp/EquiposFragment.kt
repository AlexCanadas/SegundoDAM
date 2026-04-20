package com.example.ligaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class EquiposFragment : Fragment(R.layout.fragment_equipos) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ligaNombre = arguments?.getString("ligaNombre") ?: return

        val ligas = cargarLigas()
        val liga = ligas.find { it.nombre == ligaNombre } ?: return

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerEquipos)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = EquipoAdapter(liga.equipos)
    }

    private fun cargarLigas(): List<Liga> {
        val inputStream = resources.openRawResource(R.raw.ligas)
        val reader = InputStreamReader(inputStream)
        val tipoLista = object : TypeToken<List<Liga>>() {}.type
        return Gson().fromJson(reader, tipoLista) ?: emptyList()
    }
}
