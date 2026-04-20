package com.example.ligaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EquipoAdapter(
    private val equipos: List<Equipo>
) : RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder>() {

    class EquipoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogo: ImageView = itemView.findViewById(R.id.ivLogoEquipo)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreEquipo)
        val btnFavorito: ImageButton = itemView.findViewById(R.id.btnFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipo, parent, false)
        return EquipoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]
        holder.tvNombre.text = equipo.nombre

        if (equipo.imagen.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(equipo.imagen)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivLogo)
        } else {
            holder.ivLogo.setImageResource(R.drawable.ic_launcher_foreground)
        }

        actualizarIconoFavorito(holder, equipo)

        holder.btnFavorito.setOnClickListener {
            FavoritosManager.toggleFavorito(equipo)
            actualizarIconoFavorito(holder, equipo)
        }
    }

    private fun actualizarIconoFavorito(holder: EquipoViewHolder, equipo: Equipo) {
        if (FavoritosManager.esFavorito(equipo)) {
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }

    override fun getItemCount(): Int = equipos.size
}
