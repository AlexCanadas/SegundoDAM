package com.example.minitienda.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minitienda.adapter.CartAdapter
import com.example.minitienda.data.CartStore
import com.example.minitienda.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adapter del carrito con callbacks que modifican CartStore
        adapter = CartAdapter(
            items = CartStore.items(),
            onPlus = { productId ->
                CartStore.increase(productId)
                refreshUI()
            },
            onMinus = { productId ->
                CartStore.decrease(productId)
                refreshUI()
            },
            onRemove = { productId ->
                CartStore.remove(productId)
                refreshUI()
            }
        )

        binding.recyclerCarrito.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrito.adapter = adapter

        binding.btnVaciar.setOnClickListener {
            CartStore.clear()
            refreshUI()
        }

        // Pintamos por primera vez
        refreshUI()
    }

    /**
     * Refresca la lista y el total.
     * Esto es simple (notifyDataSetChanged), luego podrías mejorar con DiffUtil.
     */
    private fun refreshUI() {
        adapter.submitList(CartStore.items())
        binding.txtTotal.text = "Total: ${CartStore.total()} €"
    }
}
