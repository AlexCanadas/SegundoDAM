import java.io.*;

public class Cuenta {
    private final File ficheroSaldo;

    public Cuenta(String rutaFichero) throws IOException {
        this.ficheroSaldo = new File(rutaFichero);
        if (!ficheroSaldo.exists()) {
            // saldo inicial 0
            guardarSaldo(0.0);
        }
    }

    // --- Métodos sincronizados para evitar condiciones de carrera ---
    public synchronized double verSaldo() throws IOException {
        return leerSaldo();
    }

    public synchronized double ingresar(double cantidad) throws IOException {
        double saldo = leerSaldo();
        saldo += cantidad;
        guardarSaldo(saldo);
        return saldo;
    }

    public synchronized double retirar(double cantidad) throws IOException {
        double saldo = leerSaldo();
        if (cantidad > saldo) {
            // No se permite retirar más de lo que hay
            return -1.0;
        }
        saldo -= cantidad;
        guardarSaldo(saldo);
        return saldo;
    }

    // --- IO interno ---
    private double leerSaldo() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroSaldo))) {
            String linea = br.readLine();
            if (linea == null || linea.trim().isEmpty()) return 0.0;
            return Double.parseDouble(linea.trim());
        }
    }

    private void guardarSaldo(double saldo) throws IOException {
        try (FileWriter fw = new FileWriter(ficheroSaldo, false)) {
            fw.write(String.valueOf(saldo));
            fw.write(System.lineSeparator());
        }
    }
}
