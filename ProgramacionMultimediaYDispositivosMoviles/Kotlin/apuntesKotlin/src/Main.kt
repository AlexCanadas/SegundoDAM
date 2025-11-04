// ===========================================
// 🧠 APUNTES DE KOTLIN — GUÍA PRÁCTICA COMPLETA
// Archivo: Main.kt
// ===========================================

fun main() {

    // =====================================================
    // VARIABLES Y TIPOS
    // =====================================================
    val nombre = "Juan"
    val edad = 25

    // 🔹 String interpolation:
    // - $variable → cuando solo pones la variable
    // - ${expresión} → cuando hay que evaluar algo más complejo
    println("Hola $nombre")                    // => Hola Juan
    println("El año que viene tendrás ${edad + 1}")  // => El año que viene tendrás 26
    println("Tu nombre tiene ${nombre.length} letras")

    // =====================================================
    // LISTAS Y FOREACH CON FILTER
    // =====================================================
    val palabras = listOf("perro", "gato", "hipopótamo", "sol", "computadora")

    // 🔹 filter → devuelve los elementos que cumplan una condición
    // 🔹 forEach → recorre la lista resultante y ejecuta algo con cada uno
    palabras
        .filter { it.length > 5 }      // "hipopótamo", "computadora"
        .forEach { println("Palabra larga: $it") }

    // Con forEachIndexed puedes obtener el índice también
    palabras.forEachIndexed { index, palabra ->
        println("[$index] = $palabra")
    }

    // =====================================================
    // FUNCIONES DE FLECHA Y LAMBDAS
    // =====================================================

    // Función normal
    fun sumar(a: Int, b: Int): Int {
        return a + b
    }

    // Función de flecha (forma corta)
    fun restar(a: Int, b: Int) = a - b

    println("Suma: ${sumar(3, 4)}")
    println("Resta: ${restar(10, 5)}")

    // Lambda (función anónima asignada a variable)
    val multiplicar = { a: Int, b: Int -> a * b }
    println("Multiplicación: ${multiplicar(3, 5)}")

    // =====================================================
    // NULL SAFETY Y ELVIS OPERATOR
    // =====================================================
    var texto: String? = "Kotlin"
    println("Longitud: ${texto?.length}") // safe call -> evita NullPointerException

    texto = null
    // Elvis ( ?: ) → valor por defecto si es null
    val longitud = texto?.length ?: 0
    println("Longitud segura: $longitud") // imprime 0

    // =====================================================
    // FUNCIONES DE COLECCIONES (find, any, all, map, etc)
    // =====================================================
    val numeros = listOf(10, 20, 30, 40)

    val encontrado = numeros.find { it == 20 }     // busca el primero que cumpla
    println("Encontrado: $encontrado")

    val hayMayor30 = numeros.any { it > 30 }       // true si hay alguno que cumpla
    println("¿Alguno > 30? $hayMayor30")

    val todosPositivos = numeros.all { it > 0 }    // true si todos cumplen
    println("¿Todos positivos? $todosPositivos")

    val duplicados = numeros.map { it * 2 }        // transforma cada elemento
    println("Duplicados: $duplicados")

    val pares = numeros.filter { it % 20 == 0 }    // filtra por condición
    println("Pares: $pares")

    // =====================================================
    // CASTING Y CHECKS DE TIPO
    // =====================================================
    val obj: Any = "Texto genérico"

    if (obj is String) {
        println("La longitud del String es ${obj.length}")
    }

    // Cast seguro con 'as?'
    val castSeguro: String? = obj as? String
    println("Cast seguro: $castSeguro")

    // =====================================================
    // TRY / CATCH / FINALLY
    // =====================================================
    try {
        val resultado = 10 / 0
    } catch (e: Exception) {
        println("Error atrapado: ${e.message}")
    } finally {
        println("Bloque finally ejecutado")
    }

    // =====================================================
    // CLASES Y DATA CLASSES
    // =====================================================
    val persona = Persona("Ana", 30)
    persona.saludar()

    val usuario1 = Usuario("Carlos", 25)
    val usuario2 = usuario1.copy(nombre = "Lucía")

    println("Usuario1: $usuario1")
    println("Usuario2: $usuario2")
    println("¿Iguales? ${usuario1 == usuario2}")
    println("HashCode usuario1: ${usuario1.hashCode()}")
    println("HashCode usuario2: ${usuario2.hashCode()}")
}

// =====================================================
// CLASES
// =====================================================
class Persona(val nombre: String, val edad: Int) {
    fun saludar() = println("Hola, soy $nombre y tengo $edad años.")
    override fun toString() = "Persona(nombre='$nombre', edad=$edad)"
}

data class Usuario(val nombre: String, val edad: Int)
