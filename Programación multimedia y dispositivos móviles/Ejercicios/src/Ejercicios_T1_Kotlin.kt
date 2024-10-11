import kotlin.math.PI

class Ejercicios_T1_Kotlin {
    // 1. Escribe un programa que pida al usuario dos números enteros y calcule su suma.
    fun ejercicio1(): Int {
        println("Introduce el primer número entero")
        val op1 = readln().toInt()
        println("Introduce el segundo número entero")
        val op2 = readln().toInt()

        return op1 + op2
    }

    // 2. Escribe un programa que pida el radio de un círculo y calcule su área. Utiliza la fórmula Área = π * radio^2.
    fun ejercicio2(): Double {
        println("Introduce el radio del círculo")
        val radio = readln().toDouble()

        return PI * (radio * radio)
    }

    // 3. Escribe un programa que convierta grados Celsius a Fahrenheit. Usa la fórmula: F = C * 9/5 + 32.
    fun ejercicio3() {
        println("Introduce los grados Celsius:")
        val celsius = readln().toDouble()

        // Convertir a Fahrenheit usando la fórmula del enunciado
        val fahrenheit = celsius * 9 / 5 + 32

        println("$celsius grados Celsius = $fahrenheit grados Fahrenheit.")
    }

    // 4. Escribe un programa que lea un número entero y determine si es par o impar (el if es igual que en Java)
    fun ejercicio4(numeroEntero: Double): Boolean {
        return numeroEntero % 1 == 0.0
    }

    // 5. Escribe un programa que lea dos números enteros y determine cuál es mayor.
    fun ejercicio5(num1: Int, num2: Int) {
        if (num1 > num2) {
            println("El número $num1 es mayor que $num2")
        } else if (num1 < num2) {
            println("El número $num2 es mayor que $num1")
        } else {
            println("Son el mismo número")
        }
    }

    // 6. Escribe un programa que calcule el factorial de un número ingresado por el usuario.
    fun ejercicio6(num6: Int): Long {
        var factorial: Long = 1 // Inicializar a 1 ya que 0 es igual a 1
        for (i in 1..num6) {
            factorial *= i
        }
        return factorial
    }

    // 7. Escribe un programa que simule una calculadora básica. El programa debe pedir al usuario dos números y una operación
    // (suma, resta, multiplicación, división) y mostrar el resultado.
    fun ejercicio7() {
        while (true) { // Parará solo con la opción 5
            // Menú calculadora con while
            println("Seleccione una operación:")
            println("1. Sumar")
            println("2. Restar")
            println("3. Multiplicar")
            println("4. Dividir")
            println("5. Salir")

            // Leer opción elegida del menú
            val opcion = readLine()

            // Salir del bucle si el usuario elige "5" = salir
            if (opcion == "5") {
                println("¡Que tengas buen día!")
                break
            }

            // Pedir dos números
            println("Ingrese el primer número:")
            val num1 = readLine()?.toDoubleOrNull() // Devuelve Null si no puede convertir a double evitando error
            println("Ingrese el segundo número:")
            val num2 = readLine()?.toDoubleOrNull()

            // Verificamos si los números son válidos
            if (num1 == null || num2 == null) {
                println("Por favor, ingrese números válidos.")
                continue // Volver al inicio del bucle y sacar el menú
            }

            // Realizar la operación correspondiente
            val resultado = when (opcion) {
                "1" -> num1 + num2 // Suma
                "2" -> num1 - num2 // Resta
                "3" -> num1 * num2 // Multiplicación
                "4" -> if (num2 != 0.0) num1 / num2 else null // Devuelve null en caso de num2=0 para evitar error
                else -> null // Opción no válida
            }

            // Mostrar el resultado
            if (resultado != null) {
                println("El resultado es: $resultado")
            } else {
                println("Operación inválida o no se puede dividir por cero.")
            }
        }
    }

    // 8. Escribe un programa que determine si un número ingresado por el usuario es primo
    fun ejercicio8(num: Int) {
        // Si el número es 1, no es primo
        if (num == 1) {
            println("El número $num NO es primo")
            return
        }

        // Comprobamos si es primo
        for (i in 2 until num) { // Desde 2 hasta num - 1, ej num=3 i=2
            if (num % i == 0) { // Si encuentra un divisor, ej num=4 4%2=0
                println("El número $num NO es primo")
                return
            }
        }

        // Si no encontró divisores, es primo
        println("El número $num es primo")
    }

    // 9. Escribe un programa que maneje información de usuarios, donde algunos campos pueden ser opcionales (es decir, pueden ser null).
    // En el programa, ni el apellido ni la edad pueden ser null.
    // Si no se proporciona la edad, debes manejar un valor por defecto. Crear una función que reciba el nombre, apellido y edad y devuelva
    // un mensaje con la información completa del usuario. Maneja de forma segura los posibles nulos

}

    fun main() {
        /* println("Ejercicio 1:")
    val ejercicio1 = Ejercicios_T1_Kotlin()
    val resultadoEjercicio1 = ejercicio1.ejercicio1()
    println("La suma es: $resultadoEjercicio1") */

        /* println("Ejercicio 2:")
    val ejercicio2 = Ejercicios_T1_Kotlin()
    val resultadoEjercicio2 = ejercicio2.ejercicio2()
    println("El área del círculo es: $resultadoEjercicio2") */

        /* println("Ejercicio 3:")
    val ejercicio3 = Ejercicios_T1_Kotlin()
    val resultadoEjercicio3 = ejercicio3.ejercicio3() */

        /* println("Ejercicio 4:")
    println("Introduce un número:")
    val num = readln().toDouble()

    val ejercicio4 = Ejercicios_T1_Kotlin()
    val esEntero = ejercicio4.ejercicio4(num)
    if (esEntero) {
        println("$num es un número entero.")
    } else {
        println("$num no es un número entero.")
    } */

        /* println("Ejercicio 5:")
    println("Introduce el primer número entero:")
    val num1 = readln().toInt()
    println("Introduce el segundo número entero:")
    val num2 = readln().toInt()

    val ejercicio5 = Ejercicios_T1_Kotlin()
    ejercicio5.ejercicio5(num1, num2) */

        /* println("Ejercicio 6:")
    println("Introduce un número entero para calcular su factorial:")
    val num6 = readln().toInt()

    if (num6 >= 0) {
        val ejercicio6 = Ejercicios_T1_Kotlin()
        val resultado6 = ejercicio6.ejercicio6(num6)
        println("El factorial de $num6 es: $resultado6")
    } else {
        println("El factorial no está definido para números negativos.")
    } */

        /* println("Ejercicio 7:")
        val ejercicio7 = Ejercicios_T1_Kotlin()
        ejercicio7.ejercicio7() */

        /* println("Ejercicio 8:")
        println("Ingrese un número:")
        val num = readLine()?.toIntOrNull() // ? = operador de llamada segura para propiedad null y no sacar excepción

        // Verificamos si el número es válido
        if (num == null || num < 1) {
            println("Por favor, ingrese un número entero positivo.")
        } else {
            val ejercicio8 = Ejercicios_T1_Kotlin()
            ejercicio8.ejercicio8(num) // Llamar a la función para verificar si es primo
        } */

        println("Ejercicio 8:")

    }
