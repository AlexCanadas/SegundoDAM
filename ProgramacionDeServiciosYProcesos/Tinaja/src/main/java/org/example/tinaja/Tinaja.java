package org.example.tinaja;

public class Tinaja {
    private int litros = 0;

    public synchronized int getLitros() {
        return litros;
    }

    public synchronized void llenar(int cantidad) {
        litros += cantidad;
        System.out.println("Llenando... " + litros + " litros en la tinaja.");
    }

        public synchronized void vaciar(int cantidad) {
            litros -= cantidad;
            if (litros < 0) litros = 0; // Evita que los litros sean negativos
            System.out.println("Vaciando... " + litros + " litros en la tinaja.");
        }

}
