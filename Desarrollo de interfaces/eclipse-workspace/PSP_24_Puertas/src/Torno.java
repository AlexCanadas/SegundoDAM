import java.util.Random;

public class Torno extends Thread {
    private final int puertaId;
    private final ContadorParque contador;
    private final int visitantesASimular;
    private final Random r = new Random();

    public Torno(int puertaId, ContadorParque contador, int visitantesASimular) {
        super("Puerta-" + (puertaId + 1));
        this.puertaId = puertaId;
        this.contador = contador;
        this.visitantesASimular = visitantesASimular;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= visitantesASimular; i++) {
                // "Evento" de entrada
                contador.registrarEntrada(puertaId);
                System.out.println(getName() + " -> entra visitante " + i);

                // Pequeña espera para simular entradas (100..400ms)
                Thread.sleep(100 + r.nextInt(301));
            }
            System.out.println(getName() + " ha terminado.");
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpida.");
        }
    }
}
