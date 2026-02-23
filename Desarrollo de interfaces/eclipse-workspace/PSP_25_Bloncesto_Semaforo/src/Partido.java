public class Partido extends Thread {
    private final Pabellon pabellon;
    private final int duracionMs;

    public Partido(Pabellon pabellon, String nombre, int duracionMs) {
        super(nombre);
        this.pabellon = pabellon;
        this.duracionMs = duracionMs;
    }

    @Override
    public void run() {
        try {
            pabellon.cogerBalon(getName());
            // Simulamos que se juega el partido
            Thread.sleep(duracionMs);
            pabellon.devolverBalon(getName());
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpido.");
        }
    }
}
