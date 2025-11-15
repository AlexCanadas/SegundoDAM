import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class hilo extends Thread {

	private String nombreHilo;
	private int tiempo;
	private static int sumaTotal = 0;
	private static Semaphore semaforo = new Semaphore(1);

	public hilo(String nombreHilo, int tiempo) {
		this.nombreHilo = nombreHilo;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		try {
			int suma = 0;
			System.out.println("el " + nombreHilo + " ha comenzado");
			for (int i = 2; i <= 10; i += 2) {
				System.out.println(nombreHilo + " Número: " + i);
				suma += i;
				Thread.sleep(tiempo);
			}
			System.out.println("suma de los pares en " + nombreHilo + ": " + suma);

			semaforo.acquire();
			try {
				sumaTotal += suma;
			} finally {
				semaforo.release();
			}
			System.out.println(nombreHilo + " ha terminado");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el tiempo de pausa para los hilos de números pares en ms:");
		int tiempo = sc.nextInt();

		System.out.println("ejecutando hilos de números pares:");

		hilo hilo1Par = new hilo("Hilo1Par", tiempo);
		hilo hilo2Par = new hilo("Hilo2Par", tiempo);

		hilo1Par.start();
		hilo2Par.start();

		try {
			hilo1Par.join();
			hilo2Par.join();

			System.out.println("suma total de pares: " + sumaTotal);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
