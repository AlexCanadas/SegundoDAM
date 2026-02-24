package com.example.pmdm2025_notafinal

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var sp1: Spinner
    private lateinit var sp2: Spinner
    private lateinit var sp3: Spinner
    private lateinit var etExamen: EditText
    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root = findViewById(R.id.root)
        sp1 = findViewById(R.id.sp1)
        sp2 = findViewById(R.id.sp2)
        sp3 = findViewById(R.id.sp3)
        etExamen = findViewById(R.id.etExamen)
        val btn = findViewById<Button>(R.id.btnCalcular)

        val notas = arrayOf(1,2,3,4,5,6,7,8,9,10)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, notas)

        sp1.adapter = adapter
        sp2.adapter = adapter
        sp3.adapter = adapter

        btn.setOnClickListener {
            calcular()
        }
    }

    private fun calcular() {

        val examenTxt = etExamen.text.toString().trim()

        if (examenTxt.isEmpty()) {
            Snackbar.make(root, "Introduce la nota del examen", Snackbar.LENGTH_SHORT).show()
            return
        }

        val notaExamen = examenTxt.toDouble()

        if (notaExamen > 10) {
            Snackbar.make(root, "La nota no puede ser mayor de 10", Snackbar.LENGTH_SHORT).show()
            return
        }

        val n1 = sp1.selectedItem as Int
        val n2 = sp2.selectedItem as Int
        val n3 = sp3.selectedItem as Int

        val notaFinal = notaExamen * 0.4 + n1 * 0.2 + n2 * 0.2 + n3 * 0.2

        Snackbar.make(root, "Nota final: %.2f".format(notaFinal), Snackbar.LENGTH_LONG).show()

        val intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra("examen", notaExamen)
        intent.putExtra("sp1", n1)
        intent.putExtra("sp2", n2)
        intent.putExtra("sp3", n3)
        intent.putExtra("final", notaFinal)
        startActivity(intent)
    }
}