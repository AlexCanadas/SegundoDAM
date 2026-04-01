package org.example.testeando;

public class Puerta extends Thread {
    private int numeroPuerta;
    private Contador contador;

    public Puerta(int numeroPuerta, Contador contador) {
        this.numeroPuerta = numeroPuerta;
        this.contador = contador;
    }

    @Override
    public void run() { // Cada puerta simula la entrada de 50 personas cuando se corre .start()
        for (int i = 0; i < 50; i++) {
            contador.incrementar(numeroPuerta);

            System.out.println("Persona entra por puerta " + numeroPuerta);

            try {
                Thread.sleep(50); // Simula el tiempo que tarda una persona en entrar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
