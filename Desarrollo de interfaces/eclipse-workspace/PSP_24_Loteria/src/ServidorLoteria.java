import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorLoteria {
    public static void main(String[] args) {
        int puerto = 7000;
        String fichero = "loteria.txt";

        // Si no existe, generamos un número ganador simple y lo guardamos (para poder probar)
        asegurarFichero(fichero);

        try (ServerSocket server = new ServerSocket(puerto)) {
            System.out.println("Servidor Lotería escuchando en puerto " + puerto);

            while (true) {
                try (Socket cliente = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true)) {

                    String ganador = leerGanador(fichero);
                    System.out.println("Cliente conectado: " + cliente.getInetAddress());

                    out.println("OK Envia tu numero (ej: 12345) o SALIR");

                    String linea = in.readLine();
                    if (linea == null) continue;

                    linea = linea.trim();
                    if (linea.equalsIgnoreCase("SALIR")) {
                        out.println("OK Adios");
                        continue;
                    }

                    if (linea.equals(ganador)) {
                        out.println("PREMIADO");
                    } else {
                        out.println("NO PREMIADO");
                    }
                } catch (IOException e) {
                    System.out.println("Error con un cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error servidor: " + e.getMessage());
        }
    }

    private static void asegurarFichero(String fichero) {
        File f = new File(fichero);
        if (f.exists()) return;

        String num = String.format("%05d", new Random().nextInt(100000));
        try (FileWriter fw = new FileWriter(f, false)) {
            fw.write(num);
            fw.write(System.lineSeparator());
            System.out.println("Creado " + fichero + " con numero ganador: " + num);
        } catch (IOException e) {
            System.out.println("No se pudo crear " + fichero + ": " + e.getMessage());
        }
    }

    private static String leerGanador(String fichero) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            return br.readLine().trim();
        }
    }
}
