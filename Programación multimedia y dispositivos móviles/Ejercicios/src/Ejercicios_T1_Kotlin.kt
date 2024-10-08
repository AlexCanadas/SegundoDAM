import kotlin.math.PI

class Ejercicios_T1_Kotlin {
    // 1. Escribe un programa que pida al usuario dos números enteros y calcule su suma.
    fun ejercicio1 () : Int {
        println("Introduce el primer número entero")
        val op1 = readln().toInt()
        println("Introduce el segundo número entero")
        val op2 = readln().toInt()

        return op1 + op2
    }

    // 2. Escribe un programa que pida el radio de un círculo y calcule su área. Utiliza la fórmula Área = π * radio^2.
    fun ejercicio2 () : Double {
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
    fun ejercicio5 (num1: Int, num2: Int) {
        if (num1 > num2) {
            println("El número $num1 es mayor que $num2")
        } else if (num1 < num2) {
            println("El número $num2 es mayor que $num1")
        } else {
            println("Son el mismo número")
        }
    }

    // 6. Escribe un programa que calcule el factorial de un número ingresado por el usuario.
    fun ejercicio6 (num6: Int): Long {
        var factorial: Long = 1 // Inicializar a 1 ya que 0 es igual a 1
        for (i in 1..num6) {
            factorial *=i
        }
        return factorial
    }

    // 7. Escribe un programa que simule una calculadora básica. El programa debe pedir al usuario dos números y una operación (suma, resta, multiplicación, división) y mostrar el resultado.

    // 8. Escribe un programa que determine si un número ingresado por el usuario es primo

    // 9. Escribe un programa que maneje información de usuarios, donde algunos campos pueden ser opcionales (es decir, pueden ser null). En el programa, ni el apellido ni la edad pueden ser null.
         // Si no se proporciona la edad, debes manejar un valor por defecto. Crear una función que reciba el nombre, apellido y edad y devuelva un mensaje con la información completa del usuario. Maneja de forma segura los posibles nulos
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

}
