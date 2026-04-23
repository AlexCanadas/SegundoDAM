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
    private val equipos: List<Equipo>,
    private val onFavoritoClick: (Equipo) -> Unit
) : RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder>() {

    class EquipoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreEquipo: TextView = itemView.findViewById(R.id.tvNombreEquipo)
        val ivLogoEquipo: ImageView = itemView.findViewById(R.id.ivLogoEquipo)
        val btnFavorito: ImageButton = itemView.findViewById(R.id.btnFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipo, parent, false)
        return EquipoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]

        holder.tvNombreEquipo.text = equipo.strTeam

        Glide.with(holder.itemView.context)
            .load(equipo.strBadge)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery)
            .into(holder.ivLogoEquipo)

        val starRes = if (FavoritosManager.esFavorito(equipo))
            android.R.drawable.btn_star_big_on
        else
            android.R.drawable.btn_star_big_off
        holder.btnFavorito.setImageResource(starRes)

        holder.btnFavorito.setOnClickListener {
            onFavoritoClick(equipo)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = equipos.size
}
