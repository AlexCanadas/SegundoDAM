import java.util.concurrent.Semaphore;

public class Pabellon {
    private final Semaphore balones;

    public Pabellon(int balonesIniciales) {
        // "fair" = true para que los que esperan sean atendidos en orden (aprox).
        this.balones = new Semaphore(balonesIniciales, true);
    }

    public void cogerBalon(String quien) throws InterruptedException {
        System.out.println(quien + " va a por un balón...");
        balones.acquire(); // si no hay, se queda esperando
        System.out.println(quien + " ha cogido un balón. (balones libres aprox: " + balones.availablePermits() + ")");
    }

    public void devolverBalon(String quien) {
        // Si hay alguien esperando, el release hará que uno se despierte y lo "coja".
        balones.release();
        System.out.println(quien + " devuelve un balón. (balones libres aprox: " + balones.availablePermits() + ")");
    }
}
