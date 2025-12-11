import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Supermercado {

	protected static double caja1 = 0, caja2 = 0, caja3 = 0;

	protected static Semaphore tienda = new Semaphore(15);
	protected static Semaphore c1 = new Semaphore(1);
	protected static Semaphore c2 = new Semaphore(1);
	protected static Semaphore c3 = new Semaphore(1);

	protected static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {

		int clientes = 0;

		System.out.println("¿Cuántos clientes van a entrar en la tienda?");
		clientes = sc.nextInt();

		ArrayList<Thread> lista = new ArrayList<>();

		for (int i = 1; i <= clientes; i++) {
			Thread hilo = new Cliente(i);
			lista.add(hilo);
			hilo.start();
		}

		for (Thread hilo : lista) {
			hilo.join();
		}

		System.out.println("Total de la caja 1 es: " + caja1);
		System.out.println("Total de la caja 2 es: " + caja2);
		System.out.println("Total de la caja 3 es: " + caja3);
		System.out.println("Total de las cajas es: " + (caja1 + caja2 + caja3));
	}
}
