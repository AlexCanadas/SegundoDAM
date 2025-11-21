import java.util.Scanner;

public class HilosInterfaz implements Runnable {

	private String nombreGalgo;
	private int tiempo; // lo pediremos en segundos
	private static int numLlegada = 1; // posicion de llegada

	public HilosInterfaz(String nombreGalgo, int tiempo) {
		this.nombreGalgo = nombreGalgo;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		try {
			System.out.println(nombreGalgo + " empieza a correr...");
			Thread.sleep(tiempo * 1000); // simula la carrera pasandolo a ms

			// usamos synchronized para actualizar la posicion de llegada
			synchronized (HilosInterfaz.class) {
				System.out.println(nombreGalgo + " ha llegado en posición: " + numLlegada);
				numLlegada++;
			}

		} catch (InterruptedException e) {
			System.out.println(nombreGalgo + " tuvo un problema durante la carrera.");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("¿Cuántos galgos corren? ");
		int numeroGalgos = sc.nextInt();

		System.out.print("¿Cuántos segundos dura la carrera para cada galgo? ");
		int tiempo = sc.nextInt();

		// similamos salida parando 1 segundo por cada syso
		System.out.println("¡Preparados...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Listos...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("YA!!!");

		// Crear y lanzar cada hilo
		for (int i = 1; i <= numeroGalgos; i++) {
			Thread t = new Thread(new HilosInterfaz("Galgo " + i, tiempo));
			t.start();
		}
	}
}
