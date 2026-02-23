public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tinaja t = new Tinaja();

        System.out.println("Simulando tinaja (1 tick = 1 segundo).");
        System.out.println("Empieza llenado a 10 L/s.");

        // Simulamos un ciclo completo: subir hasta 1000 y bajar a 0 y volver a subir un poco
        for (int segundo = 1; segundo <= 250; segundo++) {
            t.imprimirCambiosSiHay();
            t.tick();

            System.out.printf("t=%3ds -> litros=%4d (llenado=%2d, vaciado=%2d)%n",
                    segundo, t.getLitros(), t.getRitmoLlenado(), t.getRitmoVaciado());

            Thread.sleep(50); // para que no vaya demasiado rápido en pantalla

            // Si ya hemos vuelto a estar llenando a 10 y llevamos algo de litros, paramos
            if (segundo > 50 && t.getRitmoVaciado() == 0 && t.getRitmoLlenado() == 10 && t.getLitros() >= 200) {
                System.out.println("Fin de simulación.");
                break;
            }
        }
    }
}
