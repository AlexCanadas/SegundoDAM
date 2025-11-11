import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class NumPares extends Thread {
    private String nombreHilo;
    private int tiempo;
    private static int sumaTotal = 0; // Variable estática para la suma total
    private static Semaphore semaforo = new Semaphore(1); // Semáforo limitado a 1

    public NumPares(String nombreHilo, int tiempo) {
        this.nombreHilo = nombreHilo;
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        try {
            int suma = 0;
            System.out.printf("El " +nombreHilo+ " ha comenzado%n");
            for (int i = 2; i <= 10; i += 2) {
                System.out.printf(nombreHilo +"- Número:" +i+ "%n");
                suma += i;
                Thread.sleep(tiempo); // Pausa según el tiempo especificado
            }
            System.out.printf("Suma de números pares en " +nombreHilo+ ":" +suma+ "%n");

            // El uso de semáforos asegura que solo un hilo pueda acceder y modificar la variable compartida sumaTotal a la vez
            semaforo.acquire(); // Adquirir permiso del semáforo
            try {
                sumaTotal += suma;
            } finally {
                semaforo.release(); // Liberar permiso del semáforo
            }

            System.out.printf("%s ha terminado.%n", nombreHilo);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el tiempo de pausa para los hilos de números pares (en milisegundos): ");
        int tiempo = sc.nextInt();

        System.out.println("Ejecutando hilos de números pares :");

        // Crear y lanzar dos hilos de números pares con el tiempo de pausa especificado
        NumPares hilo1Par = new NumPares("Hilo1Par", tiempo);
        NumPares hilo2Par = new NumPares("Hilo2Par", tiempo);

        hilo1Par.start();
        hilo2Par.start();

        try {
            // Esperar a que ambos hilos terminen. El programa principal espera a que terminen antes de mostrar la sumaTotal.
            hilo1Par.join();
            hilo2Par.join();

            // Mostrar la suma total después de que los hilos hayan terminado
            System.out.printf("Suma total de números pares: " +sumaTotal);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
