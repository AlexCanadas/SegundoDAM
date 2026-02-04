package com.example.tiendaproductos.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tiendaproductos.R
import com.example.tiendaproductos.adapter.ProductAdapter
import com.example.tiendaproductos.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var spinnerCategorias: Spinner
    private lateinit var recyclerProductos: RecyclerView
    private val carrito = mutableListOf<Product>()
    private var allProducts = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener la Toolbar y establecerla como ActionBar para que aparezca el menú
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)

        spinnerCategorias = findViewById(R.id.spinnerCategorias)
        recyclerProductos = findViewById(R.id.recyclerProductos)
        recyclerProductos.layoutManager = LinearLayoutManager(this)

        // Cargar categorías y productos
        cargarCategorias()
        cargarProductos()

        // Configuración Edge-to-Edge (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Inflar el menú en la Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Gestionar la opción "Ver Carrito" del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_ver_carrito -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putParcelableArrayListExtra("carrito", ArrayList(carrito))
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cargarCategorias() {
        val url = "https://dummyjson.com/products/categories"
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                val categorias = mutableListOf<String>()
                // Añadimos "All" para mostrar todos los productos
                categorias.add("All")
                for (i in 0 until response.length()) {
                    // Según tu entorno, se asume que el endpoint devuelve objetos con propiedad "name"
                    val catObj = response.getJSONObject(i)
                    val catName = catObj.getString("name")
                    categorias.add(catName)
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCategorias.adapter = adapter

                spinnerCategorias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                        val selectedCategory = parent.getItemAtPosition(position) as String
                        val filteredProducts = if (selectedCategory.equals("All", ignoreCase = true)) {
                            allProducts
                        } else {
                            allProducts.filter { it.category.equals(selectedCategory, ignoreCase = true) }
                        }
                        recyclerProductos.adapter = ProductAdapter(filteredProducts, { product ->
                            carrito.add(product)
                            Toast.makeText(
                                this@MainActivity,
                                "${product.title} añadido al carrito",
                                Toast.LENGTH_SHORT
                            ).show()
                        }, true)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) { }
                }
            },
            { error -> Log.e("Volley", "Error: ${error.message}") }
        )
        Volley.newRequestQueue(this).add(request)
    }

    private fun cargarProductos() {
        val url = "https://dummyjson.com/products"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val productsArray = response.getJSONArray("products")
                val productList = mutableListOf<Product>()
                for (i in 0 until productsArray.length()) {
                    val obj = productsArray.getJSONObject(i)
                    productList.add(
                        Product(
                            obj.getInt("id"),
                            obj.getString("title") ?: "",
                            obj.getDouble("price"),
                            obj.getString("thumbnail") ?: "",
                            obj.getString("category") ?: ""
                        )
                    )
                }
                allProducts = productList
                // Inicialmente se muestran todos los productos
                recyclerProductos.adapter = ProductAdapter(productList, { product ->
                    carrito.add(product)
                    Toast.makeText(this, "${product.title} añadido al carrito", Toast.LENGTH_SHORT)
                        .show()
                }, true)
            },
            { error -> Log.e("Volley", "Error: ${error.message}") }
        )
        Volley.newRequestQueue(this).add(request)
    }
}