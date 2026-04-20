package com.example.ligaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeagueAdapter(
    private val ligas: List<Liga>,
    private val onLigaClick: (Liga) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreLiga: TextView = itemView.findViewById(R.id.tvNombreLiga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_liga, parent, false)
        return LeagueViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val liga = ligas[position]
        holder.tvNombreLiga.text = liga.nombre
        holder.itemView.setOnClickListener { onLigaClick(liga) }
    }

    override fun getItemCount(): Int = ligas.size
}
