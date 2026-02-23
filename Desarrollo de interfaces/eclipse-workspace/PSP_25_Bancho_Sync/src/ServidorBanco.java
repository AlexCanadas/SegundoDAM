import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBanco {
    public static void main(String[] args) {
        int puerto = 5000;
        String fichero = "saldo.txt";

        try {
            Cuenta cuenta = new Cuenta(fichero);
            ServerSocket server = new ServerSocket(puerto);
            System.out.println("Servidor Banco escuchando en puerto " + puerto);

            while (true) {
                Socket cliente = server.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // Un hilo por cliente
                new Thread(() -> manejarCliente(cliente, cuenta)).start();
            }
        } catch (IOException e) {
            System.out.println("Error en servidor: " + e.getMessage());
        }
    }

    private static void manejarCliente(Socket socket, Cuenta cuenta) {
        try (Socket s = socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true)) {

            out.println("OK Bienvenido. Comandos: INGRESAR cantidad | RETIRAR cantidad | SALDO | SALIR");

            String linea;
            while ((linea = in.readLine()) != null) {
                linea = linea.trim();
                if (linea.equalsIgnoreCase("SALIR")) {
                    out.println("OK Adios");
                    break;
                }

                if (linea.equalsIgnoreCase("SALDO")) {
                    double saldo = cuenta.verSaldo();
                    out.println("OK Saldo actual: " + saldo);
                    continue;
                }

                String[] partes = linea.split("\s+");
                if (partes.length == 2 && partes[0].equalsIgnoreCase("INGRESAR")) {
                    double cant = Double.parseDouble(partes[1]);
                    double saldo = cuenta.ingresar(cant);
                    out.println("OK Nuevo saldo: " + saldo);
                } else if (partes.length == 2 && partes[0].equalsIgnoreCase("RETIRAR")) {
                    double cant = Double.parseDouble(partes[1]);
                    double saldo = cuenta.retirar(cant);
                    if (saldo < 0) {
                        out.println("ERROR No hay saldo suficiente");
                    } else {
                        out.println("OK Nuevo saldo: " + saldo);
                    }
                } else {
                    out.println("ERROR Comando no valido");
                }
            }

        } catch (Exception e) {
            System.out.println("Cliente desconectado / error: " + e.getMessage());
        }
    }
}
