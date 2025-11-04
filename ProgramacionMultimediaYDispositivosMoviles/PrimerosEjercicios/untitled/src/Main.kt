fun main() {
    val controller = Controller()

    while (true) {
        println("\n=== MENÚ DE EJERCICIOS ===")
        println("1. Calculadora")
        println("2. Lista de números pares")
        println("3. Conversor de temperaturas")
        println("4. Gestión de nombres")
        println("5. Búsqueda en lista")
        println("6. Estadísticas de números")
        println("7. Filtrado de palabras")
        println("8. Tabla de multiplicar")
        println("9. Gestión de calificaciones")
        println("10. Contador de vocales")
        println("0. Salir")
        print("\nElige un ejercicio (0-10): ")

        val opcion = readLine()?.toIntOrNull() ?: -1 // Ponemos -1 para que si pone algo que no sea 0-10 entre en el else

        println()

        when (opcion) {
            1 -> {
                println("=== Ejercicio 1: Calculadora ===")
                controller.calculadora()
            }
            2 -> {
                println("=== Ejercicio 2: Lista de números pares ===")
                controller.listaNumerosPares()
            }
            3 -> {
                println("=== Ejercicio 3: Conversor de temperaturas ===")
                controller.conversorTemperaturas()
            }
            4 -> {
                println("=== Ejercicio 4: Gestión de nombres ===")
                controller.gestionNombres()
            }
            5 -> {
                println("=== Ejercicio 5: Búsqueda en lista ===")
                controller.busquedaEnLista()
            }
            6 -> {
                println("=== Ejercicio 6: Estadísticas de números ===")
                controller.ejercicioEstadisticas()
            }
            7 -> {
                println("=== Ejercicio 7: Filtrado de palabras ===")
                controller.filtradoPalabras()
            }
            8 -> {
                println("=== Ejercicio 8: Tabla de multiplicar ===")
                controller.ejercicioTablaMultiplicar()
            }
            9 -> {
                println("=== Ejercicio 9: Gestión de calificaciones ===")
                controller.gestionCalificaciones()
            }
            10 -> {
                println("=== Ejercicio 10: Contador de vocales ===")
                controller.ejercicioContadorVocales()
            }
            0 -> {
                println("¡Hasta luego!")
                break
            }
            else -> println("Opción no válida. Elige un número entre 0 y 10.")
        }
    }
}