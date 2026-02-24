package com.example.pmdm2025_notafinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val examen = intent.getDoubleExtra("examen", 0.0)
        val sp1 = intent.getIntExtra("sp1", 0)
        val sp2 = intent.getIntExtra("sp2", 0)
        val sp3 = intent.getIntExtra("sp3", 0)
        val notaFinal = intent.getDoubleExtra("final", 0.0)

        val tvRes = findViewById<TextView>(R.id.tvResultado)
        val tvDet = findViewById<TextView>(R.id.tvDetalle)
        val btn = findViewById<Button>(R.id.btnVolver)

        tvRes.text = "Nota final: %.2f".format(notaFinal)
        tvDet.text = """
            Examen: $examen
            Act1: $sp1
            Act2: $sp2
            Act3: $sp3
        """.trimIndent()

        btn.setOnClickListener {
            finish()
        }
    }
}