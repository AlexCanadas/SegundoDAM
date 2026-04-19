package com.example.calcularnotaasignatura

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        val editExamen = findViewById<EditText>(R.id.editExamen)
        val botonCalcular = findViewById<Button>(R.id.botonCalcular)

        val notas = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, notas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter

        botonCalcular.setOnClickListener {
            val textoExamen = editExamen.text.toString().trim()

            if (textoExamen.isEmpty()) {
                Snackbar.make(it, "Por favor introduce todos los datos", Snackbar.LENGTH_LONG).show()
            } else {
                val notaExamen = textoExamen.toDouble()

                if (notaExamen > 10) {
                    Snackbar.make(it, "El número más grande a introducir debe 10", Snackbar.LENGTH_LONG).show()
                } else {
                    val notaSp1 = spinner1.selectedItem.toString().toDouble()
                    val notaSp2 = spinner2.selectedItem.toString().toDouble()
                    val notaSp3 = spinner3.selectedItem.toString().toDouble()

                    val notaFinal = notaExamen * 0.4 +
                            notaSp1 * 0.2 +
                            notaSp2 * 0.2 +
                            notaSp3 * 0.2

                    Snackbar.make(it, "La nota final es $notaFinal", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}