import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 10 pistas, pero lo importante son los 8 balones disponibles
        Pabellon pabellon = new Pabellon(8);

        Random r = new Random();

        // Creamos 10 partidos intentando jugar a la vez (representantes)
        for (int i = 1; i <= 10; i++) {
            int duracion = 1500 + r.nextInt(2000);
            new Partido(pabellon, "Partido-" + i, duracion).start();
        }
    }
}
