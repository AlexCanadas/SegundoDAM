package org.example.baloncesto;

import java.util.concurrent.Semaphore;

public class Pabellon {
    private Semaphore balones = new Semaphore(8, true); // true para FIFO

    public void cogerBalon(String nombre) {
        try {
            System.out.println(nombre + " intenta coger un balón.");
            balones.acquire(); // coge balón si hay, si no espera
            System.out.println(nombre + " ha cogido un balón.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void devolverBalon(String nombre) {
        System.out.println(nombre + " devuelve un balón.");
        balones.release(); // se devuelve un balón al semáforo
    }
}
