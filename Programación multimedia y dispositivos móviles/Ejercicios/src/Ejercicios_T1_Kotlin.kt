import kotlin.math.PI

class Ejercicios_T1_Kotlin {
    // 1. Escribe un programa que pida al usuario dos números enteros y calcule su suma.
    fun ejercicio1(): Int {
        println("Introduce el primer número entero:")
        val op1 = readLine()?.toIntOrNull() ?: 0
        println("Introduce el segundo número entero:")
        val op2 = readLine()?.toIntOrNull() ?: 0
        return op1 + op2
    }

    // 2. Escribe un programa que pida el radio de un círculo y calcule su área. Utiliza la fórmula Área = π * radio^2.
    fun ejercicio2(): Double {
        println("Introduce el radio del círculo:")
        val radio = readLine()?.toDoubleOrNull() ?: 0.0
        return PI * (radio * radio)
    }

    // 3. Escribe un programa que convierta grados Celsius a Fahrenheit. Usa la fórmula: F = C * 9/5 + 32.
    fun ejercicio3() {
        println("Introduce los grados Celsius:")
        val celsius = readLine()?.toDoubleOrNull()
        if (celsius != null) {
            // Convertir a Fahrenheit usando la fórmula del enunciado
            val fahrenheit = celsius * 9 / 5 + 32
            println("$celsius grados Celsius = $fahrenheit grados Fahrenheit.")
        } else {
            println("Número inválido.")
        }
    }

    // 4. Escribe un programa que lea un número entero y determine si es par o impar (el if es igual que en Java)
    fun ejercicio4() {
        println("Introduce un número:")
        val num = readLine()?.toDoubleOrNull()
        if (num != null) {
            if (num % 1 == 0.0) {
                println("$num es un número entero.")
            } else {
                println("$num no es un número entero.")
            }
        } else {
            println("Número inválido.")
        }
    }

    // 5. Escribe un programa que lea dos números enteros y determine cuál es mayor.
    fun ejercicio5() {
        println("Introduce el primer número entero:")
        val num1 = readLine()?.toIntOrNull() ?: 0
        println("Introduce el segundo número entero:")
        val num2 = readLine()?.toIntOrNull() ?: 0

        if (num1 > num2) {
            println("El número $num1 es mayor que $num2")
        } else if (num1 < num2) {
            println("El número $num2 es mayor que $num1")
        } else {
            println("Son el mismo número")
        }
    }

    // 6. Escribe un programa que calcule el factorial de un número ingresado por el usuario.
    fun ejercicio6() {
        println("Introduce un número entero para calcular su factorial:")
        val num6 = readLine()?.toIntOrNull()
        if (num6 != null && num6 >= 0) {
            var factorial: Long = 1 // Inicializar a 1 ya que 0 es igual a 1
            for (i in 1..num6) {
                factorial *= i
            }
            println("El factorial de $num6 es: $factorial")
        } else {
            println("El factorial no está definido para números negativos.")
        }
    }

    /* 7. Escribe un programa que simule una calculadora básica. El programa debe pedir al usuario dos números y una operación
     (suma, resta, multiplicación, división) y mostrar el resultado. */
    fun ejercicio7() {
        // Menú calculadora con while
        while (true) { // Parará solo con la opción 5
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
                "1" -> num1 + num2
                "2" -> num1 - num2
                "3" -> num1 * num2
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
    fun ejercicio8() {
        println("Ingrese un número para verificar si es primo:")
        val num = readLine()?.toIntOrNull()
        if (num != null && num >= 1) {
            if (num == 1) {
                println("El número $num NO es primo")
                return
            }
            for (i in 2 until num) { // Desde 2 hasta num - 1, ej num=3 i=2
                if (num % i == 0) { // Si encuentra un divisor, ej num=4 4%2=0
                    println("El número $num NO es primo")
                    return
                }
            }
            // Si no encontró divisores, es primo
            println("El número $num es primo")
        } else {
            println("Por favor, ingrese un número entero positivo.")
        }
    }

    /* 9. Escribe un programa que maneje información de usuarios, donde algunos campos pueden ser opcionales (es decir, pueden ser null).
        En el programa, ni el apellido ni la edad pueden ser null. Si no se proporciona la edad, debes manejar un valor por defecto.
        Crear una función que reciba el nombre,
        apellido y edad y devuelva un mensaje con la información completa del usuario. Maneja de forma segura los posibles nulos */
    fun ejercicio9() {
        println("Introduce el nombre del usuario:")
        val nombre = readLine() ?: "Unknown" // Usar un valor por defecto si es null

        println("Introduce el apellido del usuario:")
        val apellido = readLine() // El apellido no puede ser null

        if (apellido.isNullOrEmpty()) {
            println("El apellido no puede ser nulo o vacío.")
            return
        }

        println("Introduce la edad del usuario:")
        val edadInput = readLine()
        val edad = edadInput?.toIntOrNull() ?: 18 // Valor por defecto si la edad es null

        // Mostrar la información del usuario
        val mensaje = "Información del usuario: Nombre: $nombre, Apellido: $apellido, Edad: $edad"
        println(mensaje)
    }

}

fun main() {
    val ejercicios = Ejercicios_T1_Kotlin()
    var continuar = true

    while (continuar) {
        println("Menú de Ejercicios:")
        println("1. Suma de dos números enteros")
        println("2. Cálculo del área de un círculo")
        println("3. Conversión de Celsius a Fahrenheit")
        println("4. Verificar si un número es entero")
        println("5. Determinar cuál de dos números es mayor")
        println("6. Calcular el factorial de un número")
        println("7. Calculadora básica")
        println("8. Determinar si un número es primo")
        println("9. Manejo de información de usuarios")
        println("10. Salir")

        print("Ingrese el número de ejercicio que desea ejecutar: ")
        val opcion = readLine()

        when (opcion) {
            "1" -> {
                val resultado = ejercicios.ejercicio1()
                println("La suma es: $resultado")
            }
            "2" -> {
                val resultado = ejercicios.ejercicio2()
                println("El área del círculo es: $resultado")
            }
            "3" -> ejercicios.ejercicio3()
            "4" -> ejercicios.ejercicio4()
            "5" -> ejercicios.ejercicio5()
            "6" -> ejercicios.ejercicio6()
            "7" -> ejercicios.ejercicio7()
            "8" -> ejercicios.ejercicio8()
            "9" -> ejercicios.ejercicio9()
            "10" -> {
                println("Saliendo del programa. ¡Hasta luego!")
                continuar = false
            }
            else -> {
                println("Opción no válida. Por favor, ingrese un número del 1 al 10.")
            }
        }
        println()
    }
}
