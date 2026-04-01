package org.example.testeando;

public class Contador {
    private int puerta1 = 0;
    private int puerta2 = 0;
    private int puerta3 = 0;

    public synchronized void incrementar(int puerta) {
        if (puerta == 1) {
            puerta1++;
        } else if (puerta == 2) {
            puerta2++;
        } else if (puerta == 3) {
            puerta3++;
        }
    }

        public void mostrarResultados() {
            System.out.println("Contador Puerta 1: " + puerta1);
            System.out.println("Contador Puerta 2: " + puerta2);
            System.out.println("Contador Puerta 3: " + puerta3);
            System.out.println("Total: " + (puerta1 + puerta2 + puerta3));
        }
}
