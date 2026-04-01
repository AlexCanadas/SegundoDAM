package org.example.tinaja;

public class Vaciado extends Thread {
    private Tinaja tinaja;
    private int velocidad = 0;

    public Vaciado(Tinaja tinaja) {
        this.tinaja = tinaja;
    }

    @Override
    public void run() {
        while (true) {
            int nivel = tinaja.getLitros();

            if (nivel >= 1000) {
                velocidad = 10;
            } else if (nivel >= 900) {
                velocidad = 5;
            } else if (nivel <= 0) {
                velocidad = 0;
            } else {
                velocidad = 0;
            }

            if (velocidad > 0) {
                tinaja.vaciar(velocidad);
            }

            try {
                Thread.sleep(1000); // Simula el tiempo entre cada vaciado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
