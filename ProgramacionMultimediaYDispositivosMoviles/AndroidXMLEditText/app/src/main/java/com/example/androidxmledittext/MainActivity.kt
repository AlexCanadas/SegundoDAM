package com.example.androidxmledittext

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edit1 = findViewById<EditText>(R.id.editNumero1)
        val edit2 = findViewById<EditText>(R.id.editNumero2)
        val boton = findViewById<Button>(R.id.botonCalcular)

        boton.setOnClickListener {

            val texto1 = edit1.text.toString().trim()
            val texto2 = edit2.text.toString().trim()

            if (texto1.isEmpty() || texto2.isEmpty()) {
                Snackbar.make(boton, "Por favor introduce todos los datos", Snackbar.LENGTH_LONG).show()
            } else {
                val num1 = texto1.toDouble()
                val num2 = texto2.toDouble()

                val resultado = num1 + num2

                val mensaje = "El resultado de la operación de suma entre $num1 y $num2 es $resultado"

                Snackbar.make(boton, mensaje, Snackbar.LENGTH_LONG).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}