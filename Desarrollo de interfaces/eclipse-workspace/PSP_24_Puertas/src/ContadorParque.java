public class ContadorParque {
    private final int[] porPuerta = new int[3];

    // Se llama cada vez que un torno "manda un evento" de entrada
    public synchronized void registrarEntrada(int puertaId) {
        porPuerta[puertaId]++;
    }

    public synchronized int getPorPuerta(int puertaId) {
        return porPuerta[puertaId];
    }

    public synchronized int getTotal() {
        return porPuerta[0] + porPuerta[1] + porPuerta[2];
    }
}
