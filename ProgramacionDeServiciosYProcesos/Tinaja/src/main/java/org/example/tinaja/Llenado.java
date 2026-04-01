package org.example.tinaja;

public class Llenado extends Thread {
    private Tinaja tinaja;
    private int velocidad = 10;

    public Llenado(Tinaja tinaja) {
        this.tinaja = tinaja;
    }

    @Override
    public void run() {
        while (true) {
            int nivel= tinaja.getLitros();

            if (nivel >=1000) {
                velocidad = 0;
            } else if (nivel <=100) {
                velocidad = 5;
            } else if (nivel >= 900) {
                velocidad = 10;
            } else {
                velocidad = 10;
            }

            if (velocidad > 0) { tinaja.llenar(velocidad);}

            try {
                Thread.sleep(1000); // Simula el tiempo entre cada llenado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
