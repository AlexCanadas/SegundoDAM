import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorJuego {
    public static void main(String[] args) {
        int puerto = 6000;
        String fichero = "juego.txt";

        int secreto = new Random().nextInt(21); // 0..20
        try (FileWriter fw = new FileWriter(fichero, false)) {
            fw.write(String.valueOf(secreto));
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("No se pudo escribir juego.txt: " + e.getMessage());
            return;
        }

        System.out.println("Servidor Juego: número guardado en " + fichero + ". (No lo muestro)");

        try (ServerSocket server = new ServerSocket(puerto)) {
            System.out.println("Escuchando en puerto " + puerto + "...");
            try (Socket cliente = server.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true)) {

                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                out.println("OK Adivina el numero (0-20)");

                int objetivo = leerNumeroDesdeFichero(fichero);

                while (true) {
                    String linea = in.readLine();
                    if (linea == null) break;

                    int intento;
                    try {
                        intento = Integer.parseInt(linea.trim());
                    } catch (NumberFormatException ex) {
                        out.println("ERROR Envia un numero");
                        continue;
                    }

                    if (intento < objetivo) {
                        out.println("MAYOR");
                    } else if (intento > objetivo) {
                        out.println("MENOR");
                    } else {
                        out.println("ACIERTO");
                        System.out.println("¡Numero acertado! Cerrando servidor.");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error servidor: " + e.getMessage());
        }
    }

    private static int leerNumeroDesdeFichero(String fichero) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            return Integer.parseInt(br.readLine().trim());
        }
    }
}
