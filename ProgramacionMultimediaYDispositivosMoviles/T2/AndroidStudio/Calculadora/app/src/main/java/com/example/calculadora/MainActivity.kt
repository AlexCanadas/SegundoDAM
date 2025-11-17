package com.example.calculadora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.PI
import kotlin.math.E
import kotlin.math.sqrt
import kotlin.math.*


class MainActivity : AppCompatActivity() {
    private var currentNumber = ""
    private var operator = ""
    private var firstOperand = 0.0
    private var secondOperand = 0.0
    private var result = 0.0

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener { appendNumber("0") }
        binding.btn1.setOnClickListener { appendNumber("1") }
        binding.btn2.setOnClickListener { appendNumber("2") }
        binding.btn3.setOnClickListener { appendNumber("3") }
        binding.btn4.setOnClickListener { appendNumber("4") }
        binding.btn5.setOnClickListener { appendNumber("5") }
        binding.btn6.setOnClickListener { appendNumber("6") }
        binding.btn7.setOnClickListener { appendNumber("7") }
        binding.btn8.setOnClickListener { appendNumber("8") }
        binding.btn9.setOnClickListener { appendNumber("9") }
        binding.btnPi?.setOnClickListener { insertConstant(PI) }
        binding.btnE?.setOnClickListener { insertConstant(E) }

        binding.btnSuma.setOnClickListener { setOperator("+") }
        binding.btnResta.setOnClickListener { setOperator("-") }
        binding.btnMultiple.setOnClickListener { setOperator("X") }
        binding.btnDivide.setOnClickListener { setOperator("/") }
        binding.btnModulo?.setOnClickListener { applyPercentage() }

        binding.btnDecimal.setOnClickListener { appendDecimal() }
        binding.btnBorrar.setOnClickListener { deleteLastCharacter() }
        binding.btnBorrarTotal.setOnClickListener { clearAll() }
        binding.btnTotal.setOnClickListener { calculateResult() }

        binding.btnCos?.setOnClickListener { calculateScientific("cos") }
        binding.btnSin?.setOnClickListener { calculateScientific("sin") }
        binding.btnTan?.setOnClickListener { calculateScientific("tan") }
        binding.btnSqrt?.setOnClickListener { calculateScientific("sqrt") }
    }


    private var resetCurrentNumber = false

    private fun appendNumber(number: String) {
        if (resetCurrentNumber) {
            currentNumber = number
            resetCurrentNumber = false
        } else if (currentNumber == "0" && number != ".") {
            currentNumber = number
        } else {
            currentNumber += number
        }
        binding.tvResult.text = currentNumber
    }

    private fun appendDecimal() {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            binding.tvResult.text = currentNumber
        }
    }

    private fun setOperator(selectedOperator: String) {
        if (currentNumber.isNotEmpty()) {
            firstOperand = currentNumber.toDouble()
            operator = selectedOperator
            currentNumber = ""
        }
    }

    private fun calculateResult() {
        if (currentNumber.isNotEmpty()) {
            secondOperand = currentNumber.toDouble()

            result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "X" -> firstOperand * secondOperand
                "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
                else -> 0.0
            }
            binding.tvResult.text = result.toString()
            currentNumber = result.toString()
            operator = ""
            resetCurrentNumber = true // Indicamos que debe reiniciarse en el próximo input
        }
    }

    private fun deleteLastCharacter() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            binding.tvResult.text = currentNumber
        }
    }

    private fun clearAll() {
        currentNumber = ""
        firstOperand = 0.0
        secondOperand = 0.0
        operator = ""
        result = 0.0
        binding.tvResult.text = "0"
    }

    private fun applyPercentage() {
        if (currentNumber.isNotEmpty()) {
            val percentageValue = currentNumber.toDouble() / 100
            currentNumber = (firstOperand * percentageValue).toString()
            binding.tvResult.text = currentNumber
        }
    }

    private fun calculateScientific(operation: String) {
        if (currentNumber.isNotEmpty()) {
            firstOperand = currentNumber.toDouble()
        }

        // Si presionamos pi o e, multiplicamos por esos valores
        result = when (operation) {
            "sin" -> sin(Math.toRadians(firstOperand))
            "cos" -> cos(Math.toRadians(firstOperand))
            "tan" -> tan(Math.toRadians(firstOperand))
            "sqrt" -> sqrt(firstOperand)
            else -> firstOperand
        }

        binding.tvResult.text = result.toString()
        currentNumber = result.toString()
    }

    private fun insertConstant(constantValue: Double) {
        if (currentNumber.isEmpty()) {
            currentNumber = constantValue.toString()
        } else {
            currentNumber = constantValue.toString()
        }
        binding.tvResult.text = currentNumber
    }
}