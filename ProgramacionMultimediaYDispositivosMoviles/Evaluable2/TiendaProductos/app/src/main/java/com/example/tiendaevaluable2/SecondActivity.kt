package com.example.tiendaevaluable2

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private val carrito = mutableListOf<Product>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var txtTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Configurar la Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Recibir el carrito (con el método de API 33+ si es posible)
        val carritoRecibido: ArrayList<Product>? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra("carrito", Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra("carrito")
        }
        carritoRecibido?.let { carrito.addAll(it) }

        recyclerView = findViewById(R.id.recyclerCarrito)
        txtTotal = findViewById(R.id.txtTotal)

        recyclerView.layoutManager = LinearLayoutManager(this)
        // En esta pantalla se oculta el botón "Añadir"
        recyclerView.adapter = ProductAdapter(carrito, onAddToCart = {}, showAddButton = false)

        updateTotal()
    }

    private fun updateTotal() {
        val total = carrito.sumOf { it.price }
        txtTotal.text = "Total: $$total"
    }

    // Infla el menú definido en res/menu/menu_second.xml
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_second, menu)
        return true
    }

    // Gestiona las acciones de cada ítem del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_confirmar_compra -> {
                val total = carrito.sumOf { it.price }
                Snackbar.make(recyclerView, "Enhorabuena, compra por valor de $$total realizada", Snackbar.LENGTH_LONG).show()
                true
            }
            R.id.menu_vaciar_carrito -> {
                carrito.clear()
                recyclerView.adapter?.notifyDataSetChanged()
                updateTotal()
                Snackbar.make(recyclerView, "Carrito vaciado", Snackbar.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}




