import java.util.Scanner;

public class HilosHerencia extends Thread {

	private String nombreGalgo;
	private int tiempo; // lo pediremos en segundos
	private static int numLlegada = 1; // posicion de llegada

	public HilosHerencia(String nombreGalgo, int tiempo) {
		this.nombreGalgo = nombreGalgo;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		try {
			System.out.println(nombreGalgo + " empieza a correr...");
			Thread.sleep(tiempo * 1000); // simula la carrera pasandolo a ms

			// usamos synchronized para actualizar la posicion de llegada e
			// HilosInterfaz.class como candado unico de la clase
			synchronized (HilosHerencia.class) {
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
		int n = sc.nextInt();

		System.out.print("¿Cuántos segundos dura la carrera para cada galgo? ");
		int tiempo = sc.nextInt();

		System.out.println("\n¡Preparados... Listos... YA!");

		// crear y lanzar cada hilo
		for (int i = 1; i <= n; i++) {
			HilosHerencia galgo = new HilosHerencia("Galgo " + i, tiempo);
			galgo.start(); // no necesitamos un Thread extra
		}
	}
}
