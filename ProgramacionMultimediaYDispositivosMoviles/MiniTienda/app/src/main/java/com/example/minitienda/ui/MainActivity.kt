package com.example.minitienda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.minitienda.adapter.ProductAdapter
import com.example.minitienda.data.CartStore
import com.example.minitienda.databinding.ActivityMainBinding
import com.example.minitienda.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Lista completa traída de internet (para filtrar sin volver a pedir)
    private var allProducts: List<Product> = emptyList()

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) Configurar RecyclerView + adapter
        adapter = ProductAdapter(items = emptyList()) { product ->
            CartStore.add(product)
            Toast.makeText(this, "${product.title} añadido", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerProductos.layoutManager = LinearLayoutManager(this)
        binding.recyclerProductos.adapter = adapter

        // 2) Botón ver carrito (no pasamos listas, leemos CartStore)
        binding.btnVerCarrito.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        // 3) Cargar categorías y productos desde API
        cargarCategorias()
        cargarProductos()
    }

    /**
     * Descarga categorías de:
     * https://dummyjson.com/products/categories
     *
     * Normalmente devuelve un array de Strings:
     * ["smartphones","laptops",...]
     */
    private fun cargarCategorias() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://dummyjson.com/products/categories"

        val req = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val categorias = mutableListOf("All")

                for (i in 0 until response.length()) {
                    val cat = response.getString(i) // aquí es String, no JSONObject
                    categorias.add(cat)
                }

                // Adaptador del Spinner (lista desplegable)
                val spinnerAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    categorias
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerCategorias.adapter = spinnerAdapter

                // Cuando el usuario elige una categoría, filtramos la lista
                binding.spinnerCategorias.setOnItemSelectedListenerSimple { selected ->
                    val filtered = if (selected == "All") {
                        allProducts
                    } else {
                        allProducts.filter { it.category.equals(selected, ignoreCase = true) }
                    }
                    adapter.submitList(filtered)
                }
            },
            { error ->
                Toast.makeText(this, "Error categorías: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(req)
    }

    /**
     * Descarga productos de:
     * https://dummyjson.com/products?limit=100
     *
     * Devuelve un JSON con "products": [ ... ]
     */
    private fun cargarProductos() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://dummyjson.com/products?limit=100"

        val req = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val arr = response.getJSONArray("products")
                val result = mutableListOf<Product>()

                for (i in 0 until arr.length()) {
                    val obj = arr.getJSONObject(i)
                    result.add(
                        Product(
                            id = obj.getInt("id"),
                            title = obj.getString("title"),
                            price = obj.getDouble("price"),
                            category = obj.getString("category"),
                            thumbnail = obj.getString("thumbnail")
                        )
                    )

                }

                allProducts = result

                // Si todavía no han tocado spinner, mostramos todo
                adapter.submitList(allProducts)
            },
            { error ->
                Toast.makeText(this, "Error productos: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(req)
    }
}

/**
 * Helper para no meter un objeto enorme de OnItemSelectedListener.
 * Te deja escribir: setOnItemSelectedListenerSimple { selected -> ... }
 */
private fun android.widget.Spinner.setOnItemSelectedListenerSimple(onSelected: (String) -> Unit) {
    this.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: android.widget.AdapterView<*>?,
            view: android.view.View?,
            position: Int,
            id: Long
        ) {
            val selected = parent?.getItemAtPosition(position) as? String ?: return
            onSelected(selected)
        }

        override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
    }
}
