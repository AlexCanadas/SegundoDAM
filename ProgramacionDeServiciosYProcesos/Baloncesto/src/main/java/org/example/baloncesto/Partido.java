package org.example.baloncesto;

public class Partido extends Thread{

    private Pabellon pabellon;

    public Partido(String nombre, Pabellon pabellon) {
        super(nombre); // nombre al hilo
        this.pabellon = pabellon;
    }

    @Override
    public void run() {
        pabellon.cogerBalon(getName()); // partido 1, 2, 3... intentan coger balón

        try {
            System.out.println(getName() + " está jugando con el balón.");
            Thread.sleep(2000); // Simula el tiempo que el jugador está usando el balón
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pabellon.devolverBalon(getName());
    }
}
