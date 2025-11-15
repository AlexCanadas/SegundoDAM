import java.util.Scanner;
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el tiempo que quieres que se pausen los hilos de números pares (en ms): ");
		int tiempo = sc.nextInt();

		System.out.println("Ejecutando hilos de números pares:");

		// Crear y lanzar dos hilos de números pares con el tiempo de pausa especificado
		NumPares hilo1Par = new NumPares("Hilo1Par", tiempo);
		NumPares hilo2Par = new NumPares("Hilo2Par", tiempo);

		hilo1Par.start();
		hilo2Par.start();

		try {
			// Esperar a que ambos hilos terminen
			hilo1Par.join();
			hilo2Par.join();

			// Mostrar la suma total después de que los hilos hayan terminado
			System.out.println("Suma total de números pares: " + sumaTotal);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
