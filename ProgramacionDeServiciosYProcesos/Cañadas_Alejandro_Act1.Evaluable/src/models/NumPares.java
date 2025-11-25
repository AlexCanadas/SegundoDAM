package models;

import java.util.concurrent.Semaphore;

public class NumPares extends Thread {
	private String nombreHilo;
	private int tiempo;
	private static int sumaTotal = 0; // Creamos variable estática para la suma total
	private static Semaphore semaforo = new Semaphore(1); // Solo dejamos un hilo

	public NumPares(String nombreHilo, int tiempo) {
		this.nombreHilo = nombreHilo;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		try {
			int suma = 0;
			System.out.println("El " + nombreHilo + " ha comenzado");
			for (int i = 2; i <= 10; i += 2) {
				System.out.println(nombreHilo + " ---> Número: " + i);
				suma += i;
				Thread.sleep(tiempo); // Pausa según el tiempo quew se introduzca por consola
			}
			System.out.println("Suma de números pares en " + nombreHilo + ": " + suma);

			// Creamos semáforo para que solo un hilo pueda modificar la variable sumaTotal
			// en un determinado momento
			semaforo.acquire(); // Pedir permiso del semáforo
			try {
				sumaTotal += suma;
			} finally {
				semaforo.release(); // Liberar permiso del semáforo
			}

			System.out.println(nombreHilo + " ha terminado.");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getSumaTotal() {
		return sumaTotal;
	}

}
