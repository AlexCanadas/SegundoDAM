import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ContadorParque contador = new ContadorParque();
        Random r = new Random();

        // Simulamos que cada puerta recibe un número de visitantes distinto
        Torno p1 = new Torno(0, contador, 8 + r.nextInt(8));  // 8..15
        Torno p2 = new Torno(1, contador, 8 + r.nextInt(8));
        Torno p3 = new Torno(2, contador, 8 + r.nextInt(8));

        p1.start();
        p2.start();
        p3.start();

        // Esperamos a que terminen las 3 puertas
        p1.join();
        p2.join();
        p3.join();

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Puerta 1: " + contador.getPorPuerta(0));
        System.out.println("Puerta 2: " + contador.getPorPuerta(1));
        System.out.println("Puerta 3: " + contador.getPorPuerta(2));
        System.out.println("TOTAL:   " + contador.getTotal());
    }
}
