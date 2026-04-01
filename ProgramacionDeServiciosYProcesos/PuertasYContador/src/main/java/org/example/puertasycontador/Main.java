package org.example.puertasycontador;

public class Main {
//    Queremos controlar el acceso a al parque de atracciones de Madrid con tres puertas de acceso.
//    Dicho acceso al parque está controlado por un torno independiente, que envía un evento propio a una aplicación
//    de computador que debe contarlas y proporcionar cuando termina la actividad de las puertas el número total
//    de visitantes que entran por cada puerta y el total de visitantes que han entrado en el parque.

    public static void main(String[] args) {
        Contador contador = new Contador();

        Puerta puerta1 = new Puerta(1, contador);
        Puerta puerta2 = new Puerta(2, contador);
        Puerta puerta3 = new Puerta(3, contador);

        // Arrancan los hilos, start crea un hilo nuevo y ejecuta el método run() en paralelo
        puerta1.start();
        puerta2.start();
        puerta3.start();

        try {
            // Join bloquea main hasta que cada hilo termine su ejecución
            puerta1.join();
            puerta2.join();
            puerta3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        contador.mostrarResultados();
    }
}
