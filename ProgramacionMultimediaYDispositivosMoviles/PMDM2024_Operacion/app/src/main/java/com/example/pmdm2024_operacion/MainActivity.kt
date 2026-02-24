package com.example.pmdm2024_operacion   // ⚠️ si tu package es distinto, deja el tuyo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var etA: EditText
    private lateinit var etB: EditText
    private lateinit var spOp: Spinner
    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root = findViewById(R.id.root)
        etA = findViewById(R.id.etA)
        etB = findViewById(R.id.etB)
        spOp = findViewById(R.id.spOp)
        val btn = findViewById<Button>(R.id.btnCalcular)

        val ops = arrayOf("suma", "resta", "multiplicación", "división")
        spOp.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ops)

        btn.setOnClickListener {
            calcular()
        }
    }

    private fun calcular() {
        val aTxt = etA.text.toString().trim()
        val bTxt = etB.text.toString().trim()

        if (aTxt.isEmpty() || bTxt.isEmpty()) {
            Snackbar.make(root, "Por favor introduce todos los datos", Snackbar.LENGTH_SHORT).show()
            return
        }

        val a = aTxt.toDouble()
        val b = bTxt.toDouble()
        val op = spOp.selectedItem.toString()

        val res = when (op) {
            "resta" -> a - b
            "multiplicación" -> a * b
            "división" -> {
                if (b == 0.0) {
                    Snackbar.make(root, "No se puede dividir entre 0", Snackbar.LENGTH_SHORT).show()
                    return
                }
                a / b
            }
            else -> a + b
        }

        val msg = "El resultado de la operación de $op entre $a y $b es $res"
        Snackbar.make(root, msg, Snackbar.LENGTH_LONG).show()
    }
}