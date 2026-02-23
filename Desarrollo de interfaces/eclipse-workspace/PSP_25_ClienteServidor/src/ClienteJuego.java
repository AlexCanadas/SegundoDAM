import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJuego {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println(in.readLine()); // OK ...

            while (true) {
                System.out.print("Introduce un numero (0-20): ");
                String intento = sc.nextLine().trim();

                int n;
                try {
                    n = Integer.parseInt(intento);
                } catch (NumberFormatException e) {
                    System.out.println("Eso no es un numero.");
                    continue;
                }

                if (n < 0 || n > 20) {
                    System.out.println("Fuera de rango.");
                    continue;
                }

                out.println(n);

                String resp = in.readLine();
                if (resp == null) {
                    System.out.println("Servidor desconectado.");
                    break;
                }

                if (resp.equalsIgnoreCase("MAYOR")) {
                    System.out.println("El numero secreto es MAYOR.");
                } else if (resp.equalsIgnoreCase("MENOR")) {
                    System.out.println("El numero secreto es MENOR.");
                } else if (resp.equalsIgnoreCase("ACIERTO")) {
                    System.out.println("¡Has acertado!");
                    break;
                } else {
                    System.out.println(resp);
                }
            }

        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}
