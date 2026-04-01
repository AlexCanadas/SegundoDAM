package org.example.testeando;

public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Puerta puerta1 = new Puerta(1, contador);
        Puerta puerta2 = new Puerta(2, contador);
        Puerta puerta3 = new Puerta(3, contador);

        puerta1.start();
        puerta2.start();
        puerta3.start();

        try {
            puerta1.join();
            puerta2.join();
            puerta3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        contador.mostrarResultados();
    }
}
