class Controller {
    // Ejercicio 1: Calculadora básica
    fun calculadora() {
        println("Introduce el primer número:")
        val respuesta1 = readLine()
        val num1 = respuesta1?.toDoubleOrNull()
        if (num1 == null) {
            println("Error: El primer número no es válido")
            return
        }

        println("Introduce el segundo número:")
        val respuesta2 = readLine()
        val num2 = respuesta2?.toDoubleOrNull()

        if (num2 == null) {
            println("Error: El primer número no es válido")
            return
        }

        println("Introduce la operación (suma, resta, multiplicacion, division):")
        val operacion = readLine() ?: ""

        var resultado = 0.0

        // When es como el switch en Kotlin para el menú de operaciones
        when (operacion) {
            "suma" -> resultado = num1 + num2
            "resta" -> resultado = num1 - num2
            "multiplicacion" -> resultado = num1 * num2
            "division" -> if (num2 != 0.0) {
                    resultado = num1 / num2
                } else {
                    println("Error: No se puede dividir entre cero")
                }
            else -> println("Operación no válida")
        }

        if (resultado % 1.0 == 0.0) { // Le quitamos los ,0 decimales si los trae
            println("Resultado: ${resultado.toInt()}")
        } else {
            println("Resultado: $resultado")
        }
    }

    // Ejercicio 2: Lista de números pares
    fun listaNumerosPares() {
        val numeros = arrayListOf<Int>() // Creamos el array de enteros a pedir

        // Pedimos 10 números al usuario
        println("Introduce 10 números enteros:")
        for (i in 1..10) {
            var numeroValido = false
            while (!numeroValido) {
                print("Número $i: ")
                val entrada = readLine()
                val numero = entrada?.toIntOrNull()

                if (numero != null) {
                    numeros.add(numero)
                    numeroValido = true
                } else {
                    println("Error: Debes introducir un número entero válido")
                }
            }

            // Mostrar lista original
            println("\nLista original: $numeros")

            // Usar filter para obtener solo los números pares
            val numerosPares = numeros.filter { numero -> numero % 2 == 0 }

            // Mostrar números pares
            println("Números pares: $numerosPares")
        }
    }

    // Ejercicio 3: Conversor de temperaturas
    fun celsiusAFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    fun fahrenheitACelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    fun conversorTemperaturas() {
        print("Introduce la temperatura: ")
        val temperatura = readLine()?.toDoubleOrNull() ?: 0.0

        print("Introduce la unidad (C/F): ")
        val unidad = readLine()?.uppercase() ?: ""

        when (unidad) {
            "C" -> {
                val resultado = celsiusAFahrenheit(celsius = temperatura)
                println("$temperatura°C = $resultado°F")
            }
            "F" -> {
                val resultado = fahrenheitACelsius(fahrenheit = temperatura)
                println("$temperatura°F = $resultado°C")
            }
            else -> println("Unidad no válida")
        }
    }

    // Ejercicio 4: Gestión de nombres
    fun gestionNombres() {
        val nombres = arrayListOf<String>()

        while (true) {
            print("Introduce un nombre (o 'salir' para terminar): ")
            val nombre = readLine() ?: ""

            if (nombre.lowercase() == "salir") {
                break
            }

            nombres.add(nombre)
        }

        val nombresEnMayusculas = nombres.map { nombre -> nombre.uppercase() }
        println("Nombres en mayúsculas: $nombresEnMayusculas")
    }

    // Ejercicio 5: Búsqueda en lista
    fun busquedaEnLista() {
        val ciudades = arrayListOf("Madrid", "Barcelona", "Valencia", "Sevilla", "Bilbao")

        println("Ciudades disponibles: $ciudades")
        print("Introduce una ciudad para buscar: ")
        val ciudadBuscada = readLine() ?: ""

        if (ciudades.contains(ciudadBuscada)) {
            println("✓ La ciudad $ciudadBuscada está en la lista")
        } else {
            println("✗ La ciudad $ciudadBuscada NO está en la lista")
        }
    }

    // Ejercicio 6: Estadísticas de números
    fun estadisticasNumeros(numeros: ArrayList<Int>): Triple<Int, Double, Int> {
        val suma = numeros.sum()
        val promedio = numeros.average()
        val mayor = numeros.maxOrNull() ?: 0

        return Triple(suma, promedio, mayor)
    }

    fun ejercicioEstadisticas() {
        val numeros = arrayListOf(5, 12, 8, 20, 3, 15)

        println("Lista de números: $numeros")

        val (suma, promedio, mayor) = estadisticasNumeros(numeros = numeros)

        println("Suma total: $suma")
        println("Promedio: $promedio")
        println("Número mayor: $mayor")
    }

    // Ejercicio 7: Filtrado de palabras
    fun filtradoPalabras() {
        val palabras = arrayListOf("casa", "ordenador", "sol", "programación", "luz", "tecnología")

        println("Lista de palabras: $palabras")
        print("Introduce el número mínimo de caracteres: ")
        val minCaracteres = readLine()?.toIntOrNull() ?: 0

        val palabrasFiltradas = palabras.filter { palabra -> palabra.length > minCaracteres }.sorted()

        println("Palabras con más de $minCaracteres caracteres (ordenadas): $palabrasFiltradas")
    }

    // Ejercicio 8: Tabla de multiplicar
    fun tablaMultiplicar(numero: Int): ArrayList<String> {
        val tabla = arrayListOf<String>()

        for (i in 1..10) {
            val resultado = numero * i
            tabla.add("$numero x $i = $resultado")
        }

        return tabla
    }

    fun ejercicioTablaMultiplicar() {
        print("Introduce un número para ver su tabla de multiplicar: ")
        val numero = readLine()?.toIntOrNull() ?: 0

        val tabla = tablaMultiplicar(numero = numero)

        println("Tabla del $numero:")
        for (linea in tabla) {
            println(linea)
        }
    }

    // Ejercicio 9: Gestión de calificaciones
    fun calcularPromedio(calificaciones: ArrayList<Double>): Double {
        return calificaciones.average()
    }

    fun contarAprobadas(calificaciones: ArrayList<Double>): Int {
        return calificaciones.count { calificacion -> calificacion >= 5.0 }
    }

    fun encontrarMasAlta(calificaciones: ArrayList<Double>): Double {
        return calificaciones.maxOrNull() ?: 0.0
    }

    fun gestionCalificaciones() {
        val calificaciones = arrayListOf(7.5, 4.2, 8.9, 5.5, 3.8, 9.1, 6.7)

        println("Calificaciones: $calificaciones")

        val promedio = calcularPromedio(calificaciones = calificaciones)
        val aprobadas = contarAprobadas(calificaciones = calificaciones)
        val masAlta = encontrarMasAlta(calificaciones = calificaciones)

        println("Promedio: %.2f".format(promedio))
        println("Aprobadas: $aprobadas")
        println("Calificación más alta: $masAlta")
    }

    // Ejercicio 10: Contador de vocales
    fun contadorVocales(texto: String): Map<Char, Int> {
        val vocales = listOf('a', 'e', 'i', 'o', 'u')
        val conteo = mutableMapOf<Char, Int>()

        for (vocal in vocales) {
            conteo[vocal] = 0
        }

        for (caracter in texto.lowercase()) {
            if (caracter in vocales) {
                conteo[caracter] = conteo[caracter]!! + 1
            }
        }

        return conteo
    }

    fun ejercicioContadorVocales() {
        print("Introduce una frase: ")
        val frase = readLine() ?: ""

        val conteo = contadorVocales(texto = frase)

        println("Conteo de vocales:")
        for ((vocal, cantidad) in conteo) {
            println("$vocal: $cantidad")
        }
    }

}



